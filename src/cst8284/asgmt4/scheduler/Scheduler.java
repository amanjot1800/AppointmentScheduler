/*
 * Course Name: CST8284_303
 * Class name: Scheduler
 * Date: 28 November 2019

 * References:
   https://www.youtube.com/watch?v=_jhCvy8_lGE
   https://stackoverflow.com/questions/2294551/java-io-writeabortedexception-writing-aborted-java-io-notserializableexception
   http://tutorials.jenkov.com/java-exception-handling/catching-multiple-exceptions.html
   Lecture notes.
  */


package cst8284.asgmt4.scheduler;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import cst8284.asgmt4.employee.Employee;
import javax.swing.*;
import static javax.swing.JOptionPane.showMessageDialog;

/**
 * This is the class where everything happens - making appointments, saving appointments, changing the appointments,
 * saving the appointments to file, retrieving the appointments from file, entering the information about
 * appointments, making objects and instances.
 *
 * @author Amanjot Singh 
 * @version 1.0
 */
public class Scheduler {

    /**
     * The variable which stores the daily schedule and the appointment data to be used by the GUI
     */
    public static String outString="";
    /**
     * ArrayList that stores all the Appointments.
     */
    private static ArrayList<Appointment> appointments = new ArrayList<>();
    /**
     * A declaration of a Employee Object to hold the current employee name.
      */
    private static Employee employee;
    /**
     * The DateFormat is used to check the correct date format for the input date
     */
    private static DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");



    /**
     * This constructor sets the new employee object, and at the same time calls the
     * loadAppointmentsFromFile method to add appointments to ArrayList, if they exists.
     *
     * @param employee The instantiated employee object, then set to the current employee object.
     */
    public Scheduler(Employee employee){
        setEmployee(employee);
        loadAppointmentsFromFile("CurrentAppointments.apts", getAppointments());
    }


    /**
     * The launch method maintains a while loop that keep the program running. It also catches the exceptions thrown
     * by the execution of the program. This method calls two other methods displayMenu() and executeMenuItem() to
     * complete its functions. It exits the program if user selects O option.
     */
    public void launch() {

        try{
            SchedulerViewer.launchGUI();
        }
        catch (Exception ex){
            showMessageDialog(SchedulerViewer.frame, ex.getMessage());
        }
    }

    /**
     * This method takes a string value and concatenates it to a static string variable which is then
     * used to output messages to the JScrollPanel in the the Scheduler Viewer Class.
     * It is used to show daily string and appointment information.
     *
     * @param msg The string used to append the static string above
     */
    public static void output(String msg){
    outString+=msg;
    SchedulerViewer.scrollText.setText(outString);
    }


    /**
     * This is the setter for the name of Employee object used in the class.
     *
     * @param emp sets the current employee object to the passed employee object.
     */
    private void setEmployee(Employee emp){
        employee = emp;
    }

    /**
     * The getter for the current employee object.
     *
     * @return current employee object (name of the employee).
     */
    public static Employee getEmployee(){
        return employee;
    }

    /**
     * Saves the appointment to the ArrayList, but only after making sure that
     * it does not already exits in the ArrayList. Also sorts the ArrayList after saving the appointment by
     * using sort method.
     *
     * @param apt A complete appointment object returned by the makeAppointmentFromUserInput method. if null
     *            is received, it means the appointment does not exists.
     * @return boolean whether the appointment save was successful or not.
     *         <code> true </code> if yes, <code>false</code>
     *         if appointment save is not successful.
     */
    public static boolean saveAppointment(Appointment apt){
        if (findAppointment(apt.getAptDate())==null){
            getAppointments().add(apt);
            Collections.sort(getAppointments(), new SortAppointmentByCalendar());
            return true;
        }
        else return false;
    }

    /**
     * Finds the appointment in the ArrayList by using the Collections class' sort method. It passes three arguments to this
     * method, first - the ArrayList, second - a dummy appointment made by just using calendar object to help
     * in the searching, third - a new instance of the the {@link SortAppointmentByCalendar} class that extends
     * {@link Comparable}.
     *
     * @param cal helps in searching of appointment by date and time. It helps to create a dummy appointment that plays a
     *            role to search appointments.
     * @return appointment object - if present in the array; otherwise returns <code>null</code>.
     */
    private static Appointment findAppointment(Calendar cal) {
        // references: https://www.concretepage.com/java/example-collections-binarysearch-java
        int index = Collections.binarySearch(getAppointments(), new Appointment(cal,null,null,null,null) , new SortAppointmentByCalendar() );
        if (index<0) return null;
        else return getAppointments().get(index);
    }


    /**
     * Deletes the appointment based on the date and time received as a calendar object. First it finds the appointment
     * by using the findAppointment method and then displays it. Then prompts the user to confirm the delete and then
     * deletes the appointment from the ArrayList.
     *
     * @param cal the calendar object by using which appointments will be searched.
     * @return true - if delete was successful
     *         false - if delete was not successful.
     */
    public static boolean deleteAppointment(Calendar cal){
        if(findAppointment(cal)!=null){

            int j = JOptionPane.showConfirmDialog(null, findAppointment(cal).toString(), "Do you want to delete?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (j==0){
                getAppointments().remove(findAppointment(cal));
                return true;
            }
        }
        return false;
    }


    /**
     * Changes the existing appointment's date and time to a new date and time.
     * First it finds the appointment by using the findAppointment method and if Appointment is found, displays it
     * and asks for confirmation, and then makes a new calendar object for the new sate and time. it then sets the
     * new calendar for the existing appointment.
     *
     * @param cal the calendar object used for searching the appointments.
     * @return true - if change rime was successful
     *         false - if changing time failed due to some reason.
     */
    public static boolean changeAppointment(Calendar cal){
        if(findAppointment(cal)!=null){

            int j = JOptionPane.showConfirmDialog(null, findAppointment(cal).toString(), "Do you want to change?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

            if (j==0){

                AppointmentDialog.date.setText(JOptionPane.showInputDialog(null,"Enter new date"));
                AppointmentDialog.time.setText(JOptionPane.showInputDialog(null,"Enter new time"));


                Calendar newCal = makeCalendarFromUserInput(false);
                if(findAppointment(newCal)==null){
                    findAppointment(cal).setAptDate(newCal);
                    return true;
                } else showMessageDialog(null,"Appointment Does not exitst");
            }
        }
        return false;
    }


    /**
     * Displays a single appointment based on the user entered date and time.
     * First, it calls the findAppointment method to search for the appointment and if no appointment is found, displays
     * a message that appointment does not exists. If the appointment is found, prints it by using the {@link Appointment}
     * class' toString method.
     *
     * @param cal the calendar object used to find the appointments. This method passed the the calendar parameter
     *            to the findAppointment method.
     */
    public static void displayAppointment (Calendar cal){
        if (findAppointment(cal)==null){
            output("No appointments found between " + cal.get(Calendar.HOUR_OF_DAY) + ":00 and " + (cal.get(Calendar.HOUR_OF_DAY)+1) + ":00\n");
        }
        else {
            output("\n" + findAppointment(cal).toString() + "\n\n");
        }
    }


    /**
     * Displays the day schedule by using a for loop to loop between 8 AM - 4 PM and calling displayAppointment method
     * in each iteration.
     *
     * @param cal the calendar object used to set the HOUR_OF_DAY to find all appointments on a given date, which is eventually
     *            passed down to the findAppointment Class.
     */
    public static void displayDaySchedule (Calendar cal) {
        for (int i = 8; i <= 16; i++) {
            cal.set(Calendar.HOUR_OF_DAY,i);
            displayAppointment(cal);
        }

    }


    // reference:  https://www.youtube.com/watch?v=_jhCvy8_lGE
    /**
     * Saves the appointments in memory to a file on the disk. It uses FileOutputStream and ObjectOutputStream to
     * write the objects to file by using a enhanced for loop through the Appointments Array.
     *
     * @param apts The appointments array used to save the appointments declared at top of the class
     * @param saveFile the actual name of the file to be saved on the disk.
     * @return true - if save appointments to file was successful
     *         false - if save appointments to file failed or an exception was thrown.
     */
    public static boolean saveAppointmentsToFile(ArrayList<Appointment> apts, String saveFile ){
        File file = new File(saveFile);
        try {
            FileOutputStream fout = new FileOutputStream(file);
            ObjectOutputStream output = new ObjectOutputStream(fout);
            for (Appointment apt: apts){
                output.writeObject(apt);
            }
            output.close();
            fout.close();
            return true;
        } catch (IOException exp){
            showMessageDialog(null,exp.getMessage());
        }
        return false;
    }


    // reference:  https://www.youtube.com/watch?v=_jhCvy8_lGE
    /**
     * Does exactly opposite of saveAppointmentsToFile method by loading appointments from file
     * and adding them to array one by one. It first clears the ArrayList to remove all appointments that are
     * in the memory. Then uses FileInputStream and ObjectInputStream to read the objects from file.
     *
     * @param sourceFile the name of the file on the disk.
     * @param apts the actual appointments ArrayList used to save appointments.
     * @return whether the loading appointments was successful or not - true if yes; false if an error occurred or
     *         an exception was thrown.
     */
    public static boolean loadAppointmentsFromFile(String sourceFile, ArrayList<Appointment> apts){
        apts.clear();
        File file = new File(sourceFile);
        try(
        		 FileInputStream fin = new FileInputStream(file);
                ObjectInputStream input = new ObjectInputStream(fin);
        		){
            while (true){
                Appointment apt = (Appointment)input.readObject();
                apts.add(apt);
            }
        }
        catch (EOFException exp1){
            return true;
        }catch (FileNotFoundException exp2){
            return false;
        }catch (IOException | ClassNotFoundException exp3){
            showMessageDialog(SchedulerViewer.frame, exp3.getMessage());
        }
        return false;
    }


    /**
     * Makes a new Appointment object by making several other objects such as TelephoneNumber and Activity;
     * takes in various information needed to make a Appointment. It also checks if the entered name has any illegal
     * characters or not. Makes a new Appointment by using the information and returns it.
     *
     * @return a new Appointment Object
     * @throws BadAppointmentDataException if the name contains illegal characters or the name is greater
     * than the specified length, or if user only enters the single name.
     */
    public static Appointment makeAppointmentFromUserInput(){

        String name = AppointmentDialog.name.getText();

        // references: https://stackoverflow.com/questions/2385701/regular-expression-for-first-and-last-name
        if(name.isEmpty())
            throw new BadAppointmentDataException("Must enter a value","Empty or null value entered");
        else if (!name.matches("^[-\\ .'a-zA-z]*$"))
            throw new BadAppointmentDataException("Name cannot include characters other than alphabetic " +
                    "characters, the dash (-), the period (.), and the apostrophe (‘)","Illegal characters in name");
        else if (name.length()>30)
            throw new BadAppointmentDataException("Name cannot exceed 30 characters","Name exceeds maximum length");
        try{
             String aa = name.split(" ")[1];
        }
        catch (ArrayIndexOutOfBoundsException ex){
            throw new BadAppointmentDataException("Name is not entered correctly","Name must contain two Strings separated by a single space");
        }

        String phone = AppointmentDialog.phone.getText();
        TelephoneNumber phoneNo = new TelephoneNumber(phone);

        Calendar cal = makeCalendarFromUserInput(false);
        String act = AppointmentDialog.activity.getText();
        if(act.isEmpty())
            throw new BadAppointmentDataException("Must enter a value","Empty or null value entered");
        String des = (String)AppointmentDialog.des.getSelectedItem();
        Activity activity = new Activity(act, des);

        return new Appointment(cal,name,phoneNo,activity);

    }


    /**
     * Makes a new Calendar Object by taking the user input. it calls another method to set the date of the
     * calendar instance. Sets the Calendar.setLenient to false so that user is not able to set the bad date for the
     * calendar.
     *
     * @param suppressHour used if user wants to take the date input only. Then it does not prompt the user for the
     *                     time input.
     * @return the new calendar object made by the method after taking dates and times from the user.
     */
    public static Calendar makeCalendarFromUserInput(boolean suppressHour){
            Calendar cal = Calendar.getInstance();
            cal.clear();
            cal.setLenient(false);

            String date = AppointmentDialog.date.getText();
            if (date.isEmpty())
                throw new BadAppointmentDataException("Must enter a value","Empty or null value entered");

            setAppointmentDate(date, cal);

            if (!suppressHour) {
                    String time = AppointmentDialog.time.getText();
                    if (time.isEmpty())
                        throw new BadAppointmentDataException("Must enter a value","Empty or null value entered");
                    cal.set(Calendar.HOUR_OF_DAY, processTimeString(time));
                }
                return cal;
    }


    /**
     * This is the sub method used to set the appointment date as well as check for any bad date formats. 
     * It uses a pre defined date format and matches all input date formats with the given format. In case 
     * of a mismatch, it throws a new BadAppointmentDataException.
     * 
     * @param date the string value to be parsed and matched with pre defined date format
     * @param cal the calendar object created in previous method; used to set date.
     * @throws BadAppointmentDataException if the date format is not as specified or user enters bad date.
     */
    private static void setAppointmentDate(String date, Calendar cal)  {

        dateFormat.setLenient(false);
        try {
            Date date2 = dateFormat.parse(date);
            cal.setTime(date2);
        }
        catch (RuntimeException exp){
            throw new BadAppointmentDataException("General runtime exception thrown setting start date","");
        }catch (ParseException exp){
            throw new BadAppointmentDataException("Bad calendar date entered; format is DDMMYYYY","Bad calendar format");
        }

    }

    /**
     * Takes in the string value entered by the user and process whether it is am or pm and then convert it to 
     * 13-hour format.
     * 
     * @param t the string sent by the makeCalendarFromUserInput method to convert to 13-hour.
     * @return 13-hour format time.
     */
    private static int processTimeString(String t){

        try {
            String t2 = t.replace(":", "").replace("00", "").replace(" ", "");
            if (t2.contains("a.m.") || t2.contains("am") || t2.contains("a m")) {
                return Integer.parseInt(t2.replace("a.m.", "").replace("am", "").replace("a m", ""));
            } else if (t2.contains("p.m.") || t2.contains("pm") || t2.contains("p m")) {
                return (Integer.parseInt(t2.replace("p.m.", "").replace("pm", "").replace("p m", "")) + 12);
            } else if (Integer.parseInt(t2) >= 1 && Integer.parseInt(t2) <= 4) {
                return (Integer.parseInt(t2) + 12);
            } else return Integer.parseInt(t2);
        }
        catch (Exception ex){
            throw new BadAppointmentDataException("Please enter a valid time","Bad time entered");
        }
    }

    /**
     * The getter for the appointments ArrayList.
     *
     * @return appointments stored in the ArrayList.
     */
    public static ArrayList<Appointment> getAppointments(){
        return appointments;
    }
}


// https://www.youtube.com/watch?v=_jhCvy8_lGE
// https://stackoverflow.com/questions/2294551/java-io-writeabortedexception-writing-aborted-java-io-notserializableexception
// http://tutorials.jenkov.com/java-exception-handling/catching-multiple-exceptions.html
//