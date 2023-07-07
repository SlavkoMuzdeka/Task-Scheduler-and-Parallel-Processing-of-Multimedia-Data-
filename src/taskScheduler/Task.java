package taskScheduler;

import java.text.ParseException;
import java.util.Date;

public class Task extends Thread {
	private Long totalTime; // Total execution Time
	private Date dueTime; // The deadline by which the task must be completed or terminated
	private Integer coreUsed; // Allowed number of processor cores used
	private String name; // The name of the task
	private Integer priority; // Task priority

	public static Integer count;
	public static Boolean pom;

	public Task() {
		super();
	}

	public Task(Long totalTime, Date dueTime, Integer coreUsed, String name, Integer priority) throws ParseException {
		super();
		this.totalTime = totalTime;
		this.dueTime = dueTime;
		this.coreUsed = coreUsed;
		this.name = name;
		this.priority = priority;
	}

	public void setTotalTime(Long totalTime) {
		this.totalTime = totalTime;
	}

	public Long getTotalTime() {
		return totalTime;
	}

	public void setDueTime(Date dueTime) {
		this.dueTime = dueTime;
	}

	public Date getDueTime() {
		return dueTime;
	}

	public void setCoreUsed(Integer coreUsed) {
		this.coreUsed = coreUsed;
	}

	public Integer getCoreUsed() {
		return coreUsed;
	}

	public void setName1(String name) {
		this.name = name;
	}

	public String getName1() {
		return name;
	}

	public void setPriority1(Integer priority) {
		this.priority = priority;
	}

	public Integer getPriority1() {
		return priority;
	}

	@Override
	public String toString() {
		String n = "The name of the task: " + name;
		String t = n + "\n" + "Allowed total execution time: " + totalTime / 1000 + "." + totalTime % 1000
				+ "[s]";
		String str = t + "\n" + "The deadline: " + dueTime + "\n";
		str = str + "==================================\n";
		return str;
	}

}
