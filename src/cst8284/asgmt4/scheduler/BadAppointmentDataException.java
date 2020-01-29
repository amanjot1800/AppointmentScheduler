/*
 * Course Name: CST8284_303
 * Student Name: Amanjot Singh
 * Class name: BadAppointmentDataException
 * Date: 28 November 2019
 */

package cst8284.asgmt4.scheduler;

/**
 * The Exception is thrown whenever there is an inconsistency in the data entered by the user. It extends
 * RuntimeException.
 *
 * @author Amanjot Singh
 * @version 1.0
 */
public class BadAppointmentDataException extends java.lang.RuntimeException {
    private static final long serialVersionUID = 1L;
    /**
     * The variable used to store the more details of the error or exception thrown.
     */
    private String description;


    /**
     * It chains to the two-string constructor by passing a preset message.
     */
    public BadAppointmentDataException(){
        this("Please try again", "Bad data entered");
    }

    /**
     * Takes the two parameters and passes one of them to the superclass of Exception and stores the other
     * in the local field.
     *
     * @param error the actual error message passed in the {@link BadAppointmentDataException}, then passed to the
     *              superclass constructor.
     * @param description the detailed description of the exception thrown, passed in
     *                    the {@link BadAppointmentDataException} exception.
     */
    public BadAppointmentDataException(String error, String description){
        super(error);
        setDescription(description);
    }

    /**
     * Sets the description to the passed value
     * @param description the description message passed by constructor
     */
    public void setDescription(String description){
        this.description = description;
    }

    /**
     *
     * @return description stored in the field above.
     */
    public String getDescription() {
        return this.description;
    }




}
