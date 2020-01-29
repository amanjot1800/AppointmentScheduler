/*
 * Course Name: CST8284_303
 * Student Name: Amanjot Singh
 * Class name: Employee
 * Date: 28 November 2019
 */
package cst8284.asgmt4.employee;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * The employee abstract class that have the abstract methods and fields to store employee information.
 *
 * @author Amanjot Singh
 * @version 1.0
 */
public abstract class Employee {
	/**
	 * The field used to store the current full name of the Employee.
	 */
	private String fullName;
	/**
	 * scanner object used to take user input.
	 */
	protected static Scanner scan = new Scanner(System.in);


	/**
	 * Constructor chains to the next 1-arg constructor and passes a default value.
	 */
	protected Employee() {this("unknown");}

	/**
	 * Calls the setter of fullname and passes it to be set by it.
	 * @param fullName full name passed for the employee
	 */
	protected Employee(String fullName) {setName(fullName);}

	/**
	 * Sets the passed name value to the fullName field.
	 * @param fullName value passed by the constructor
	 */
	public void setName(String fullName) {this.fullName = fullName;}

	/**
	 * Getter for fullName
	 * @return the fullname field
	 */
	public String getName() {return fullName;}

	/**
	 *  Abstract method to be overridden by subclasses.
	 * @return String depicting the type of
	 */
	public abstract String[] getActivityType();

	/**
	 * Overridden <code> toString </code> method
	 * @return the current Employee name by using a getter
	 */
	@Override
	public String toString() {return getName();}
	
}