/*
 * Course Name: CST8284_303
 * Student Name: Amanjot Singh
 * Class name: Appointment
 * Date: 28 November 2019
 * references: https://www.geeksforgeeks.org/split-string-java-examples/
 */

package cst8284.asgmt4.scheduler;

import java.io.Serializable;
import java.util.Calendar;

/**
 * This class helps to make the Appointment object that stores the user's name, phone number, appointment date
 * and time, and type of activity to be done.
 *
 * @author Amanjot Singh
 * @version 1.0
 */
public class Appointment implements Serializable {

    /**
     * The calendar abject that stores the appointment date and time.
     */
    private Calendar aptDate;
    /**
     * Stores the first name of the customer
     */
    private String firstName;
    /**
     * Stores the last name of the customer
     */
    private String lastName;
    /**
     * Stores the phone number of the customer
     */
    private TelephoneNumber phone;
    /**
     * The Activity object that stores the info about type of Activity.
     */
    private Activity activity;
    public static final long serialVersionUID = 1L;

    /**
     * This constructor chains to the another constructor which takes two different strings for the first
     * and last names. It splits the full name into two parts in the constructor call
     *
     * @param cal Calendar object
     * @param fullName full name of the person
     * @param phone TelephoneNumber object
     * @param activity Activity object
     */
    public Appointment(Calendar cal, String fullName, TelephoneNumber phone, Activity activity){
        this(cal, fullName.split(" ")[0], fullName.split(" ")[1], phone, activity);
    }

    // references: https://www.geeksforgeeks.org/split-string-java-examples/
    /**
     * The second constructor for the Appointment class. Calls all the setters and sets the passed
     * values to the class fields.
     *
     * @param cal the Calendar object
     * @param firstName first name string
     * @param lastName last name string
     * @param phone TelephoneNumber object
     * @param activity Activity object
     */
    public Appointment(Calendar cal, String firstName, String lastName, TelephoneNumber phone, Activity activity){
        setAptDate(cal);
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setActivity(activity);
    }

    /**
     *
     * @return current {@link Calendar} object
     */
    public Calendar getAptDate() {
        return aptDate;
    }

    /**
     * sets the current aptDate field to passed parameter
     * @param aptDate Calendar object sent by the {@link Scheduler} class
     */
    public void setAptDate(Calendar aptDate) {
        this.aptDate = aptDate;
    }

    /**
     * 
     * @return the current firstName field
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * sets the current firstName field to the passed value
     * @param firstName made by splitting the fullName into two parts
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * 
     * @return current lastName field
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * sets the current lastName field to the passed value
     * @param lastName made by splitting the fullName into two parts
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * 
     * @return the current TelephoneNumber Object
     */
    public TelephoneNumber getPhone() {
        return phone;
    }

    /**
     * sets the current TelephoneNumber Object to passed value
     * @param phone Object sent by the {@link Scheduler} class
     */
    public void setPhone(TelephoneNumber phone) {
        this.phone = phone;
    }

    /**
     * 
     * @return the current Activity object
     */
    public Activity getActivity() {
        return activity;
    }

    /**
     * sets the current Activity object to the passed value
     * @param activity Activity object sent by the {@link Scheduler} class
     */
    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * Returns the Complete Appointment information, formatted, and by using <code> toString </code> methods from 
     * {@link Calendar}, {@link TelephoneNumber}, and {@link Activity} classes.
     * 
     * @return complete formatted output (appointment information)
     */
    public String toString(){
       return  getAptDate().getTime() + "\n" + getFirstName() + " " + getLastName() + "\n" + getPhone().toString() + "\n" + getActivity();
    }
}
