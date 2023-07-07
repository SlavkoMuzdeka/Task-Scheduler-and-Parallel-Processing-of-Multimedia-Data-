package algorithms;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
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

public class ParallelPixelSortingTask extends Task {

	private File inputFile;
	private File outputFile;
	private BufferedImage inputImage;
	private BufferedImage outputImage;
	private ExecutorService executor;
	private int height, width;

	public ParallelPixelSortingTask() {
		super();
	}

	public ParallelPixelSortingTask(Long totalTime, Date dueTime, Integer coreUsed, String name, Integer priority)
			throws ParseException, IOException {
		super(totalTime, dueTime, coreUsed, name, priority);
		inputFile = new File(Frame.inputImagePath);
		outputFile = new File(Frame.outputImagePath);
		inputImage = ImageIO.read(inputFile);
		outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
		executor = Executors.newFixedThreadPool(getCoreUsed());
		this.height = (int) (inputImage.getHeight() / getCoreUsed());
		this.width = inputImage.getWidth();
	}

	@Override
	public void run() {
		try {
			long startTime = System.currentTimeMillis();
			writeOnFrame(getName1() + " has began to perform...\n");
			ArrayList<Runnable> threadList = new ArrayList<>();
			ArrayList<ArrayList<Integer>> list = new ArrayList<>();
			for (int i = 0; i < getCoreUsed(); i++) {
				final int pom = i;
				Runnable runnable = new Runnable() {
					@Override
					public void run() {
						int pom1 = pom;
						ArrayList<Integer> pixelList = new ArrayList<>();
						for (int k = 0; k < width; k++) {
							for (int j = (0 + (pom1 * height)); j < (height + (pom1 * height)); j++) {
								int p = inputImage.getRGB(k, j);
								pixelList.add(p);
							}
						}
						Collections.sort(pixelList, Collections.reverseOrder());
						list.add(pixelList);
					}
				};
				threadList.add(runnable);
			}
			for (int i = 0; i < threadList.size(); i++) {
				executor.execute(threadList.get(i));
			}
			executor.shutdown();
			executor.awaitTermination(50, TimeUnit.SECONDS);
			ArrayList<Integer> newList = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				newList.addAll(list.get(i));
			}
			Collections.sort(newList, Collections.reverseOrder());
			int l = 0;
			for (int j = 0; j < inputImage.getWidth(); j++) {
				for (int k = 0; k < inputImage.getHeight(); k++) {
					outputImage.setRGB(j, k, newList.get(l++));
				}
			}
			long endTime = System.currentTimeMillis();
			if ((endTime - startTime) <= getTotalTime() || new Date().after(getDueTime())) {
				ImageIO.write(outputImage, "png",
						new File(Frame.outputImagePath + "\\" + "sortiranaSlika_" + inputFile.getName()));
				writeOnFrame(getName1() + " is finished." + "Duration: " + ((endTime - startTime) / 1000) + "."
						+ ((endTime - startTime) % 1000) + "\n");
			} else {
				interrupt();
				writeOnFrame(getName1() + " is not over because the time is up.\\n");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
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

	public void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}

	public File getInputFile() {
		return inputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

	public File getOutputFile() {
		return outputFile;
	}

}
