package taskScheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import MyException.MyException;

public class TaskScheduler {

	private Integer numOfProcessorCores; // Number of processor cores
	private Integer maxNumberOfTasks; // Maximum number of tasks that are performed competitive
	private ArrayList<Task> tasks = new ArrayList<>();
	public static Integer count = 0;

	public TaskScheduler() {
		super();
	}

	public TaskScheduler(Integer numOfProcessorCores, Integer maxNumberOfTasks, ArrayList<Task> tasks) {
		super();
		this.numOfProcessorCores = numOfProcessorCores;
		this.maxNumberOfTasks = maxNumberOfTasks;
		this.tasks = tasks;
	}

	public TaskScheduler(Integer numOfProcessorCores, Integer maxNumberOfTasks) {
		super();
		this.numOfProcessorCores = numOfProcessorCores;
		this.maxNumberOfTasks = maxNumberOfTasks;
	}

	public void add(Task task) {
		tasks.add(task);
		tasks.sort(
				(task1, task2) -> Integer.valueOf(task1.getPriority()).compareTo(Integer.valueOf(task2.getPriority())));
		count++;
	}

	public void remove(Task task) {
		for (int i = 0; i < tasks.size(); i++) {
			if (task.getName().equals(tasks.get(i).getName())) {
				tasks.remove(i);
				count--;
			}
		}
	}

	public void removeAll() {
		tasks.clear();
		count = 0;
	}

	public void execute(Task task) throws MyException{
		for (int i = 0; i < tasks.size(); i++) {
			if (task.getName().equals(tasks.get(i).getName())) {
				if (new Date().before(tasks.get(i).getDueTime())) {
					tasks.get(i).start();
					remove(task);
					count--;
				} else {
					throw new MyException("The deadline has expired.");
				}
			}
		}
	}

	public void executeAll(ExecutorService executor) throws MyException {
		Integer numOfTasks = Math.min(numOfProcessorCores, tasks.size());
		for (int i = 0; i < numOfTasks; i++) {
			if (new Date().before(tasks.get(i).getDueTime())) {
				executor.execute(tasks.get(i));
				count--;
			} else {
				throw new MyException("The deadline has expired.");
			}
		}
		removeAll();
	}

	public void printTasks() {
		for (Task task : tasks) {
			System.out.println(task);
		}
	}

	public Integer getNumOfProcessorCores() {
		return numOfProcessorCores;
	}

	public void setNumOfProcessorCores(Integer numOfProcessorCores) {
		this.numOfProcessorCores = numOfProcessorCores;
	}

	public Integer getMaxNumberOfTasks() {
		return maxNumberOfTasks;
	}

	public void setMaxNumberOfTasks(Integer maxNumberOfTasks) {
		this.maxNumberOfTasks = maxNumberOfTasks;
	}

	public ArrayList<Task> getTasks() {
		return tasks;
	}

	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public String toString() {
		return "TaskScheduler [numOfProcessorCores=" + numOfProcessorCores + ", maxNumberOfTasks=" + maxNumberOfTasks
				+ ", tasks=" + tasks + "]";
	}
}
