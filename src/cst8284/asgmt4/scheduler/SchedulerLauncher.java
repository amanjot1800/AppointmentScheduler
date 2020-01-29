/*
 * Course Name: CST8284_303
 * Student Name: Amanjot Singh
 * Class name: SchedulerLauncher
 * Date: 28 November 2019
 */

package cst8284.asgmt4.scheduler;

import cst8284.asgmt4.employee.Dentist;
import cst8284.asgmt4.employee.Employee;

/**
 * The entry point of the Assignment and contains a main method.
 *
 * @author Amanjot Singh
 * @version 1.0
 */
public class SchedulerLauncher {

	/**
	 * Main  method of the program. It instantiates a new Employee object of type Dentist and instantiates a new
	 * Scheduler object by passing made dentist as parameter.
	 *
	 * @param args default string
	 */
	public static void main(String[] args){
		javax.swing.SwingUtilities.invokeLater
				(() -> new Scheduler(new Dentist("Dr. Andrews")).launch());

	}

}
