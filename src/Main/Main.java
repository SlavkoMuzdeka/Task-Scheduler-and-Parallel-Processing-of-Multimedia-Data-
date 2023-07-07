package main;

import GUI.Frame;
import Rasporedjivac_zadataka.TaskScheduler;

public class Main {
	public static void main(String[] args) {
		TaskScheduler scheduler = new TaskScheduler(Runtime.getRuntime().availableProcessors(), 5);
		new Frame(scheduler);
	}
}
