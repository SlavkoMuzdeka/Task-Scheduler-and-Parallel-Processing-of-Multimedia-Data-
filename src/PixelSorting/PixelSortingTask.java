package PixelSorting;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import GUI.Frame;
import Rasporedjivac_zadataka.Task;

// ALGORITHM NAME: Constructing a new image by sorting pixels by column or type according to some criterion

public class PixelSortingTask extends Task {

	private ArrayDeque<File> fileList;
	private File inputFile;

	public PixelSortingTask() {
		super();
	}

	public PixelSortingTask(Long totalTime, Date dueTime, Integer coreUsed, String name, Integer priority)
			throws ParseException {
		super(totalTime, dueTime, coreUsed, name, priority);
		this.inputFile = new File(Frame.inputImagePath);
		fileList = new ArrayDeque<>();
		addImageToList(inputFile, fileList);
	}

	private void addImageToList(File inputFile, ArrayDeque<File> fileList) {
		File[] files = inputFile.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].toString().endsWith(".png")) {
				fileList.add(files[i]);
			}
		}
	}

	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		writeOnFrame(getName1() + " has began to perform...\n");
		ArrayList<Runnable> threadList = new ArrayList<>();
		ExecutorService executor = Executors.newFixedThreadPool(getCoreUsed());
		int counter = fileList.size();
		int count = 0;
		while (counter > 0) {
			for (int pom = 0; pom < getCoreUsed() && counter > 0; pom++) {
				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						try {
							File newFile = fileList.pollLast();
							BufferedImage inputImage = ImageIO.read(newFile);
							ArrayList<Integer> pixelList = new ArrayList<>();
							for (int j = 0; j < inputImage.getWidth(); j++) {
								for (int k = 0; k < inputImage.getHeight(); k++) {
									int p = inputImage.getRGB(j, k);
									pixelList.add(p);
								}
							}
							Collections.sort(pixelList, Collections.reverseOrder());
							BufferedImage outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(),
									BufferedImage.TYPE_INT_ARGB);
							int l = 0;
							for (int j = 0; j < inputImage.getWidth(); j++) {
								for (int k = 0; k < inputImage.getHeight(); k++) {
									outputImage.setRGB(j, k, pixelList.get(l++));
								}
							}
							ImageIO.write(outputImage, "png",
									new File(Frame.outputImagePath + "\\" + newFile.getName()));
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				};
				threadList.add(runnable);
				counter--;
				count++;
			}
			for (int temp = 0; temp < count; temp++) {
				executor.execute(threadList.get(temp));
			}
			count = 0;
		}
		executor.shutdown();
		try {
			executor.awaitTermination(200, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		if (((endTime - startTime) <= getTotalTime() || new Date().after(getDueTime()))) {
			writeOnFrame(getName1() + " is finished." + "Duration: " + ((endTime - startTime) / 1000) + "."
					+ ((endTime - startTime) % 1000) + "\n");
		} else {
			writeOnFrame(getName1() + " is not over because the time is up.\n");
		}
	}

	private void writeOnFrame(String str) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Frame.area.append(str);
			}
		});
	}

	public void setFileList(ArrayDeque<File> fileList) {
		this.fileList = fileList;
	}

	public ArrayDeque<File> getFileList() {
		return fileList;
	}

}
