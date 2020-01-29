/*
 * Course Name: CST8284_303
 * Student Name: Amanjot Singh
 * Class name: Activity
 * Date: 28 November 2019
 */

package cst8284.asgmt4.scheduler;

import java.io.Serializable;

/**
 * This class helps to make an Activity Object that stores two fields - category of Appointment and
 * description of Activity to be performed.
 *
 * @author Amanjot Singh
 * @version 1.0
 */
public class Activity implements Serializable {

    /**
     * The variable used to store the description passed down by the setters and constructors.
     */
    private String descriptionOfWork;
    /**
     * The variable used to store the category passed down by the setters and constructors.
     */
    private String category;
    public static final long serialVersionUID = 1L;


    /**
     * Constructor calls the two setters to set the parameters passed into constructor. It receives the two
     * string values as the description of the type of activity user wants.
     *
     * @param description The description of the activity.
     * @param category Predefined string passed by makeAppointmentFromUserInput method from Scheduler class.
     */
    public Activity(String description, String category){
        setDescription(description);
        setCategory(category);
    }

    /**
     * Returns the description of work stored.
     *
     * @return The description of work.
     */
    public String getDescription() {
        return descriptionOfWork;
    }

    /**
     * Sets the passed parameter.
     *
     * @param s description of the type of work to be performed.
     */
    public void setDescription(String s) {
        this.descriptionOfWork = s;
    }

    /**
     * Returns the category.
     *
     * @return category string. It is pre-written in the {@link cst8284.asgmt4.employee.Employee } class.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the passed category.
     *
     * @param s Returns current category
     */
    public void setCategory(String s) {
        this.category = s;
    }

    /**
     * Prints the Activities to console
     * @return Category and description of work in the string format, combined together, to be used by
     * the {@link Appointment} class' toString method.
     */
    public String toString(){
        return getCategory() + "\n" + getDescription();

    }
}
