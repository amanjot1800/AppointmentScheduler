/*
 * Course Name: CST8284_303
 * Student Name: Amanjot Singh
 * Class name: Dentist
 * Date: 28 November 2019
 */

package cst8284.asgmt4.employee;

import java.util.ArrayList;

/**
 * This class extends from the Employee class and helps to make a Dentist object that stores the full
 * name of the Employee.
 *
 * @author Amanjot Singh
 * @version 1.0
 */
public class Dentist extends Employee {

    /**
     * No-arg constructor.
     */
    public Dentist(){}

    /**
     * The 1-arg constructor for the Dentist class. Takes in the fullname passed and sets it to
     * the superclass constructor.
     *
     * @param fullname the name passed tio the constructor and then it is passed to the {@link Employee} class.
     */
    public Dentist(String fullname){
        super(fullname);
    }

    /**
     * First prints the menu options for the activity type and then asks for the user input. Then it runs a series
     * of case statements to return a string based ob the user input. This string is used to set the category type of
     * {@link cst8284.asgmt4.scheduler.Activity} class.
     *
     * @return a activity type string that determines the type of activity user wants. Its pre defined, but is only
     * returned when a user submits a valid choice.
     */
    @Override
    public String[] getActivityType(){
        String[] activity = {"Assessment", "Filling", "Crown", "Cosmetic Repairs"};

        return activity;
        }
    }
