package cst8284.asgmt4.scheduler;


import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

import static javax.swing.JOptionPane.showMessageDialog;

/* Adapted, with considerable modification, from 
 * http://www.java2s.com/Code/Java/Swing-JFC/TextAcceleratorExample.htm,
 *
 */

public class AppointmentDialog {

	private static final GridBagConstraints textConstants = new GridBagConstraints(
			0, GridBagConstraints.RELATIVE, 1, 1, 1, 1,  // gridx, gridy, gridwidth, gridheight, weightx, weighty
			GridBagConstraints.EAST, 0, new Insets(2, 2, 2, 2), 1, 1); // anchor, fill, insets, ipadx, ipady

	private static final GridBagConstraints labelConstants = new GridBagConstraints(
			1, GridBagConstraints.RELATIVE, 1, 1, 1.0, 0,
			GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);

	private static final GridBagConstraints buttonConstant = new GridBagConstraints(
			1, GridBagConstraints.RELATIVE, 1, 1, 1.0, 0,
			GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(2, 2, 2, 2), 0, 0);


	private static Container cp;
	private static final int labelWidth = 35;
	private static final Font defaultFont = new Font("SansSerif", Font.PLAIN, 16);

	public static JComboBox<String> des;
	public static JTextField name, phone, date, time, activity;


	public static void showAppointmentDialog(){
	    JFrame f = new JFrame("Save an Appointment");
	    cp = f.getContentPane();
	    cp.setLayout(new GridBagLayout());

		name = setRow("Enter Client Name (as FirstName LastName):", 'n');
	    phone = setRow("Phone Number (e.g. 613-555-1212):", 'p');
	    date = setRow("Appointment Date (entered as DDMMYYYY):", 'd');
	    time = setRow("Appointment Time:", 't');
	    activity = setRow("Activity Description", 'a');

	    des = new JComboBox<String>(Scheduler.getEmployee().getActivityType());
		cp.add(new JLabel("Activity Type: ", SwingConstants.RIGHT), textConstants);
	    cp.add(des, labelConstants);

	    setBtns("Save",
				e -> {
					try {
						if (Scheduler.saveAppointment(Scheduler.makeAppointmentFromUserInput())) {
							showMessageDialog(null, "Appointment Saved!");
						} else showMessageDialog(null, "Cannot save, an appointment at that time already exist.");
					}
					catch (BadAppointmentDataException ex){
						showMessageDialog(null, ex.getMessage(), ex.getDescription(), JOptionPane.ERROR_MESSAGE);
					}
				}
		);
	    f.pack();
	    f.setLocationRelativeTo(null);
	    f.addWindowListener(new WindowAdapter() {
	    public void windowClosing(WindowEvent evt) {
	    	f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	      }
	    });
	    f.setVisible(true);
	  }


	public static void showCalendarDialog(){
		JFrame f = new JFrame("Get, set, change or delete an appointment");
		cp = f.getContentPane();
		cp.setLayout(new GridBagLayout());

		date = setRow("Appointment Date (entered as DDMMYYYY): ", 'd');
		time = setRow("Appointment Time: ", 't');

		setBtns("Get Appointment",
				e -> {
			try {
				Scheduler.displayAppointment(Scheduler.makeCalendarFromUserInput(false));
				f.dispose();
			}
			catch (BadAppointmentDataException ex){
				showMessageDialog(null, ex.getMessage(), ex.getDescription(), JOptionPane.ERROR_MESSAGE);
			}
				});

		setBtns("Delete",
				e -> {
			try {
				if (Scheduler.deleteAppointment(Scheduler.makeCalendarFromUserInput(false))) {
					showMessageDialog(null, "Appointment Deleted");
				} else showMessageDialog(null, "No appointments found at given time.");
				f.dispose();
			}
			catch (BadAppointmentDataException ex){
				showMessageDialog(null, ex.getMessage(), ex.getDescription(), JOptionPane.ERROR_MESSAGE);
			}
				});

		setBtns("Change Time",
				e -> {
			try {
				if (Scheduler.changeAppointment(Scheduler.makeCalendarFromUserInput(false))) {
					showMessageDialog(null, "Appointment re-booked");
				} else showMessageDialog(null, "No appointments found at given time.");
				f.dispose();
			}
			catch (BadAppointmentDataException ex){
				showMessageDialog(null, ex.getMessage(), ex.getDescription(), JOptionPane.ERROR_MESSAGE);
			}
				});

		f.pack();
		f.setLocationRelativeTo(null);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
		});
		f.setVisible(true);
	}

	/**
	 * GUI for the daily schedule viewer.
	 */
	public static void showDaySchedule(){
		JFrame f = new JFrame("Display Day Schedule");
		cp = f.getContentPane();
		cp.setLayout(new GridBagLayout());

		date = setRow("Appointment Date (entered as DDMMYYYY): ", 'd');

		setBtns("Get Day Schedule",
				e -> {
			try {
				Scheduler.displayDaySchedule(Scheduler.makeCalendarFromUserInput(true));
				f.dispose();
			}
			catch (BadAppointmentDataException ex){
				showMessageDialog(null, ex.getMessage(), ex.getDescription(), JOptionPane.ERROR_MESSAGE);
			}
				}  );
		f.pack();
		f.setLocationRelativeTo(null);
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			}
		});
		f.setVisible(true);
	}



	
	private static JTextField setRow(String label, char keyboardShortcut) {
		JLabel l; JTextField t;
		cp.add(l = new JLabel(label, SwingConstants.RIGHT), textConstants);
		l.setFont(defaultFont);
		l.setDisplayedMnemonic(keyboardShortcut);
	    cp.add(t = new JTextField(labelWidth), labelConstants);
	    t.setFocusAccelerator(keyboardShortcut);
	    return t;
	}

	private static JButton setBtns(String btnLabel, ActionListener act) {
		final Font font = new Font("SansSerif", Font.PLAIN, 20);
		JButton btn = new JButton(btnLabel);
		btn.setFont(font);
		btn.addActionListener(act);
		cp.add(btn, buttonConstant);
		return btn;
	}
	  
}
