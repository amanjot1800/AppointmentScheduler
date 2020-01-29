/*
 * Course Name: CST8284_303
 * Student Name: Amanjot Singh
 * Class name: SortAppointmentByCalendar
 * Date: 28 November 2019
 */

package cst8284.asgmt4.scheduler;

import java.util.Comparator;

/**
 * The class overwrites the compare method in the the Comparator interface and it helps to sort the
 * appointments in the ArrayList by returning a number based on comparison result.
 *
 * @author Amanjot Singh
 * @version 1.0
 */
public class SortAppointmentByCalendar implements Comparator<Appointment> {


    /**
     * Overrides the compare method from the Comparator Interface and uses the <code>Calendar.before </code>
     * and <code>Calendar.after </code> and returns a number based on the result.
     *
     * @param apt1 First appointment passed to be compared, passed in the method call
     * @param apt2 Second appointment to be compared, passed in the method call
     * @return 1 if apt2 is after apt1,
     *        -1 if apt2 is before apt1,
     *         0 if no case applies.
     */
    @Override
    public int compare(Appointment apt1, Appointment apt2) {

        if ( apt1.getAptDate().before(apt2.getAptDate()) ) return -1;
        else if (apt1.getAptDate().after(apt2.getAptDate()) ) return 1;
        else return 0;

        // source: http://www.java2s.com/Code/Java/Development-Class/CalendarComparator.htm


    }

}
