/*
 * Course Name: CST8284_303
 * Student Name: Amanjot Singh
 * Class name: TelephoneNumber
 * Date: 28 November 2019
 * References: https://www.journaldev.com/807/java-string-substring
 */


package cst8284.asgmt4.scheduler;

import java.io.Serializable;

/**
 * The class helps to make the Telephone number object that validates, parses and processes the phone number.
 * It stores the phone number in three different fields.
 *
 * @author Amanjot Singh
 * @version 1.0
 */
public class TelephoneNumber implements Serializable {

    /**
     * The field used to store the 3 digit area code.
     */
    private int areaCode;
    /**
     * The field used to store the 3 digit number prefix.
     */
    private int prefix;
    /**
     * The field used to store the line number of the phone number.
     */
    private int lineNumber;
    public static final long serialVersionUID = 1L;


// references: https://www.journaldev.com/807/java-string-substring
    /**
     * Constructor receives the phone number string from the Scheduler class and processes it into different fields -
     * area code, prefix, and line number.
     * Makes sures that the phone number meets the specified formats and it does not contain any
     * illegal characters.
     * Then, extracts the substrings from the passed string and assign them to the various fields by converting them
     * to integer values.
     * Also makes sure that area code does not start with 0 or 1
     *
     * @param phoneNumber phone number passed by the {@link Scheduler} Class.
     * @throws BadAppointmentDataException if the phone number does not meet the specified formats - that is -
     *         contains illegal characters, has bad format, or the area code starts with 0 or 1.
     */
    public TelephoneNumber(String phoneNumber){

// regex idea:  http://txt2re.com/index-csharp.php3?s=121-255-4545&4&-19&5&-20&2
//              https://regexr.com/
        if (phoneNumber.isEmpty())
            throw new BadAppointmentDataException("Must enter a value","Empty or null value entered");
        else if (!phoneNumber.matches("^(.{3})(-)(.{3})(-)(.{4})$"))
                throw new BadAppointmentDataException("Missing digit(s); correct format is AAA-PPP-NNNN, where AAA" +
                        " is the area code and PPP-NNNN is the local number","Incorrect format");
            else if (!phoneNumber.matches("(\\d+).(\\d+).(\\d+)"))
                throw new BadAppointmentDataException("Telephone numbers can only contain numbers or the\n" +
                        "character ‘-‘","Bad character(s) in input string");

            String area = phoneNumber.substring(0, 3);
            String prefix = phoneNumber.substring(4, 7);
            String line = phoneNumber.substring(8, 12);

            if (area.charAt(0)=='0' || area.charAt(0)=='1')
                throw new BadAppointmentDataException("Area code can’t start with a ‘0’ or a ‘1’","Invalid number");

            setAreaCode(Integer.parseInt(area));
            setPrefix(Integer.parseInt(prefix));
            setLineNumber(Integer.parseInt(line));

        }

    /**
     *
     * @return the area code integer
     */
    public int getAreaCode() {
        return areaCode;
    }

    /**
     * sets the area code to the passed value.
     *
     * @param areaCode area code passed by the constructor
     */
    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    /**
     *
     * @return the prefix integer
     */
    public int getPrefix() {
        return prefix;
    }

    /**
     * sets the prefix to the passed int value.
     *
     * @param prefix prefix integer made by the constructor
     */
    public void setPrefix(int prefix) {
        this.prefix = prefix;
    }

    /**
     *
     * @return the line number stored in the above field.
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * sets the line number to the passed integer value.
     *
     * @param lineNumber int sent by the constructor
     */
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    /**
     * Gets the area code, prefix, and line number using getters and forms a telephone number string with a
     * specific format.
     *
     * @return the complete phone number string to be used by {@link Appointment} class.
     */
    public String toString(){
        return "(" + getAreaCode() + ") " + getPrefix() + "-" + getLineNumber();
    }
}



//"^(\\d{3})(-)(\\d{3})(-)(\\d{4})$"