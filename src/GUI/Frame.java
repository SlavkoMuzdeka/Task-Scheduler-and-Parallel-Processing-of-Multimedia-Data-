package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import MyException.MyException;
import PixelSorting.ParallelPixelSortingTask;
import PixelSorting.PixelSortingTask;
import Rasporedjivac_zadataka.TaskScheduler;

public class Frame implements ActionListener {
	private JFrame frame, frame1, frame2;
	private JButton btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn10, btn11;
	private JLabel label, label1, label2, label3, label4, label5, label6;
	private JScrollPane pane;
	private JTextField text1, text2, text3, text4, text5;
	private TaskScheduler scheduler;
	private JComboBox<String> comboBox;
	
	public static JTextArea area, area1;
	public static String textFromCombo = "";
	public static String inputImagePath;
	public static String outputImagePath;

	public Frame(TaskScheduler scheduler) {
		this.scheduler = scheduler;
		createFrame();
	}
	
	private void createFrame() {
		frame = new JFrame("Working with scheduler");
		frame.setSize(1000, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLayout(null);
		frame.setLocation(400, 200);

		label = new JLabel("Select a function:");
		label.setBounds(60, 10, 200, 30);
		label.setFont(new Font("Verdana", Font.PLAIN, 15));
		frame.add(label);

		btn1 = new JButton("Add task");
		btn1.addActionListener(this);
		btn1.setFocusable(false);
		btn1.setBounds(40, 50, 200, 30);
		frame.add(btn1);

		btn2 = new JButton("Remove task");
		btn2.addActionListener(this);
		btn2.setFocusable(false);
		btn2.setBounds(40, 100, 200, 30);
		frame.add(btn2);

		btn3 = new JButton("Remove all tasks");
		btn3.addActionListener(this);
		btn3.setFocusable(false);
		btn3.setBounds(40, 150, 200, 30);
		frame.add(btn3);

		area = new JTextArea();
		area.setSize(200, 50);
		area.setBounds(280, 30, 350, 150);
		area.setEditable(false);
		frame.add(area);

		btn4 = new JButton("List all tasks");
		btn4.addActionListener(this);
		btn4.setFocusable(false);
		btn4.setBounds(670, 50, 200, 30);
		frame.add(btn4);

		btn5 = new JButton("Execute task");
		btn5.addActionListener(this);
		btn5.setFocusable(false);
		btn5.setBounds(670, 100, 200, 30);
		frame.add(btn5);

		btn6 = new JButton("Execute all tasks");
		btn6.addActionListener(this);
		btn6.setFocusable(false);
		btn6.setBounds(670, 150, 200, 30);
		frame.add(btn6);

		btn9 = new JButton("Stop task");
		btn9.addActionListener(this);
		btn9.setFocusable(false);
		btn9.setBounds(40, 200, 200, 30);
		frame.add(btn9);

		btn10 = new JButton("Pause task");
		btn10.addActionListener(this);
		btn10.setFocusable(false);
		btn10.setBounds(670, 200, 200, 30);
		frame.add(btn10);

		btn11 = new JButton("Continue execution");
		btn11.addActionListener(this);
		btn11.setFocusable(false);
		btn11.setBounds(280, 200, 350, 30);
		frame.add(btn11);

		frame.setVisible(true);
	}

	public void addTask() {
		frame1 = new JFrame("Adding new task");
		frame1.setSize(750, 400);
		frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame1.setResizable(false);
		frame1.setLayout(null);
		frame1.setLocation(400, 200);

		label1 = new JLabel("Total execution time[ms]:");
		label1.setBounds(50, 20, 300, 30);
		label1.setFont(new Font("Verdana", Font.PLAIN, 15));
		frame1.add(label1);

		text1 = new JTextField();
		text1.setBounds(400, 20, 230, 30);
		frame1.add(text1);

		label2 = new JLabel("Due date[dd-MM-yyyy HH:mm:ss]:");
		label2.setBounds(50, 70, 300, 30);
		label2.setFont(new Font("Verdana", Font.PLAIN, 15));
		frame1.add(label2);

		text2 = new JTextField();
		text2.setBounds(400, 70, 230, 30);
		frame1.add(text2);

		label3 = new JLabel("Allowable level of parallelism:");
		label3.setBounds(50, 120, 300, 30);
		label3.setFont(new Font("Verdana", Font.PLAIN, 15));
		frame1.add(label3);

		text3 = new JTextField();
		text3.setBounds(400, 120, 230, 30);
		frame1.add(text3);

		label4 = new JLabel("Task priority: ");
		label4.setBounds(50, 170, 300, 30);
		label4.setFont(new Font("Verdana", Font.PLAIN, 15));
		frame1.add(label4);

		text4 = new JTextField();
		text4.setBounds(400, 170, 230, 30);
		frame1.add(text4);

		label5 = new JLabel("The name of the task: ");
		label5.setBounds(50, 220, 300, 30);
		label5.setFont(new Font("Verdana", Font.PLAIN, 15));
		frame1.add(label5);

		text5 = new JTextField();
		text5.setBounds(400, 220, 230, 30);
		frame1.add(text5);

		label6 = new JLabel("Task type: ");
		label6.setBounds(50, 270, 300, 30);
		label6.setFont(new Font("Verdana", Font.PLAIN, 15));
		frame1.add(label6);

		String[] taskType = { "Sorting Pixel", "Parallel image processing" };
		comboBox = new JComboBox<String>(taskType);
		comboBox.setBounds(400, 270, 230, 30);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textFromCombo = comboBox.getSelectedItem().toString();
			}
		});
		frame1.add(comboBox);

		btn7 = new JButton("Create");
		btn7.addActionListener(this);
		btn7.setFocusable(false);
		btn7.setBounds(230, 320, 100, 30);
		frame1.add(btn7);

		btn8 = new JButton("Cancel");
		btn8.addActionListener(this);
		btn8.setFocusable(false);
		btn8.setBounds(370, 320, 100, 30);
		frame1.add(btn8);

		frame1.setVisible(true);
	}

	private void showTasks() {
		if (scheduler.getTasks().size() == 0) {
			JOptionPane.showInternalMessageDialog(null, "There is no task in the list of tasks", "Overview of tasks",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			frame2 = new JFrame("Show all tasks");
			frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame2.setBounds(1100, 200, 350, 400);

			area1 = new JTextArea();
			area1.setEditable(false);

			for (int i = 0; i < scheduler.getTasks().size(); i++) {
				area1.append(scheduler.getTasks().get(i).toString());
			}

			pane = new JScrollPane(area1);
			frame2.add(pane);
			frame2.setVisible(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {
			area.setText("");
			addTask();
		} else if (e.getSource() == btn2) {
			executeOrRemove("remove");
		} else if (e.getSource() == btn3) {
			if (scheduler.getTasks().size() == 0) {
				JOptionPane.showInternalMessageDialog(null, "There is no task in the list of tasks.",
						"First, add the task.", JOptionPane.INFORMATION_MESSAGE);
			} else {
				area.setText("");
				scheduler.removeAll();
				JOptionPane.showInternalMessageDialog(null, "All tasks have been deleted", "Deleted",
						JOptionPane.INFORMATION_MESSAGE);
			}
		} else if (e.getSource() == btn4) {
			showTasks();
		} else if (e.getSource() == btn5) {
			executeOrRemove("execute");
		} else if (e.getSource() == btn6) {
			try {
				if (scheduler.getTasks().size() == 0) {
					JOptionPane.showInternalMessageDialog(null, "There is no task in the list of tasks.",
							"First, add the task.", JOptionPane.INFORMATION_MESSAGE);
				} else {
					area.setText("");
					ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
					scheduler.executeAll(executor);
					executor.shutdown();
				}
			} catch (MyException ex) {
				ex.printStackTrace();
			}
		} else if (e.getSource() == btn7) {
			try {
				SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
				if (checkValidation()) {
					inputImagePath = JOptionPane.showInputDialog("Enter input path: ");
					outputImagePath = JOptionPane.showInputDialog("Enter output path: ");
					createTask(textFromCombo, Long.parseLong(text1.getText()), format.parse(text2.getText()),
							Integer.parseInt(text3.getText()), text5.getText(), Integer.parseInt(text4.getText()));
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else if (e.getSource() == btn8) {
			frame1.dispose();
		} else if (e.getSource() == btn9) {
			// TODO IMPLEMENT WHEN TASK IS STOPPED
		} else if (e.getSource() == btn10) {
			// TODO IMPLEMENT WHEN TASK IS PAUSED
		} else if (e.getSource() == btn11) {
			// TODO IMPLEMENT WHEN TASK CONTINOUS TO WORK
		}
	}

	private boolean checkNameOfTask(String name) {
		for (int i = 0; i < scheduler.getTasks().size(); i++) {
			if (scheduler.getTasks().get(i).getName().equals(name)) {
				return false;
			}
		}
		return true;
	}

	private void executeOrRemove(String str) {
		try {
			area.setText("");
			boolean pom = false;
			String taskName = JOptionPane.showInputDialog("Enter task name: ");
			for (int i = 0; i < scheduler.getTasks().size(); i++) {
				if (str.equals("remove")) {
					if (taskName != null && taskName.equals(scheduler.getTasks().get(i).getName1())) {
						scheduler.remove(scheduler.getTasks().get(i));
						pom = true;
						JOptionPane.showInternalMessageDialog(null, "Task has been successfully deleted.", "Deleted",
								JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					if (taskName != null && taskName.equals(scheduler.getTasks().get(i).getName1())) {
						pom = true;
						scheduler.execute(scheduler.getTasks().get(i));
					}
				}
			}
			if (taskName != null && pom == false) {
				JOptionPane.showInternalMessageDialog(null, "The task with that name does not exist.", "Try again",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (MyException ex) {
			ex.printStackTrace();
		}
	}

	private void createTask(String taskType, long totalTime, Date dueTime, int coreUsed, String name, int priority)
			throws MyException, ParseException, IOException {
		if (taskType.equals("Parallel image processing")) {
			scheduler.add(new ParallelPixelSortingTask(totalTime, dueTime, coreUsed, name, priority));
		} else if (taskType.equals("Sorting Pixel")) {
			scheduler.add(new PixelSortingTask(totalTime, dueTime, coreUsed, name, priority));
		}
		JOptionPane.showInternalMessageDialog(null, "Task has been successfully created.", "Successfully created",
				JOptionPane.INFORMATION_MESSAGE);
		frame1.dispose();
	}

	private boolean checkValidation() {
		if (text1.getText().equals("") || text2.getText().equals("") || text3.getText().equals("")
				|| text4.getText().equals("") || text5.getText().equals("")) {
			JOptionPane.showInternalMessageDialog(null, "All fields must be filled in.", "Try again",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (Integer.parseInt(text3.getText()) > scheduler.getNumOfProcessorCores()) {
			JOptionPane.showInternalMessageDialog(null,
					"The number of parallelism must be less (equal to) the number of nuclei assigned to the scheduler.",
					"Try again", JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!checkNameOfTask(text5.getText())) {
			JOptionPane.showInternalMessageDialog(null, "There's a task with that name.", "Try again",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if ("".equals(textFromCombo)) {
			JOptionPane.showInternalMessageDialog(null, "Task type not selected.", "Try again",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

}
