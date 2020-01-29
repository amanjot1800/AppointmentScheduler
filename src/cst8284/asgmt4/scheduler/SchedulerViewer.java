package cst8284.asgmt4.scheduler;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class SchedulerViewer {

    private static final Toolkit tk = Toolkit.getDefaultToolkit();
    private static final Dimension screenSize = tk.getScreenSize();
    public static final JTextArea scrollText = new JTextArea();
    public static JFrame frame;



    public static void launchGUI(){
        frame = new JFrame();
        frame.setTitle("Scheduling Appointments for " + Scheduler.getEmployee());
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int screenX = (int) screenSize.getWidth() / 2;
        int screenY = (int) (7 * screenSize.getHeight() / 8);

        frame.add(getWestPanel(), BorderLayout.WEST);
        frame.add(getCenterPanel(scrollText, screenY), BorderLayout.CENTER);
        frame.setPreferredSize(new Dimension(screenX, screenY));

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public static JPanel getCenterPanel(JTextArea jta, int height) {
        JScrollPane centerPane = new JScrollPane(jta);
        centerPane.setPreferredSize(new Dimension(400, 7 * height / 8));
        JPanel jp = new JPanel();
        jp.add(centerPane);
        return jp;
    }

    private static JButton setWestPanelBtns(String btnLabel, ActionListener act) {
        final Font font = new Font("SansSerif", Font.PLAIN, 20);
        JButton btn = new JButton(btnLabel);
        btn.setFont(font);
        btn.addActionListener(act);
        return btn;
    }

    public static JPanel getWestPanel() {
        JPanel controlPanel = new JPanel(new GridLayout(6, 1));
        JPanel westPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1;

            controlPanel.add(setWestPanelBtns("  Save Appointment  ",
                    e -> AppointmentDialog.showAppointmentDialog()));

            controlPanel.add(setWestPanelBtns(" Display Schedule ",
                    e -> {
                        Scheduler.outString = "";
                        AppointmentDialog.showDaySchedule();

                    }));

            controlPanel.add(setWestPanelBtns("  Display Appointment  ",
                    e -> {
                        Scheduler.outString = "";
                        AppointmentDialog.showCalendarDialog();
                    }));

            controlPanel.add(setWestPanelBtns("Save Appointment to file",
                    e -> {
                        if (Scheduler.saveAppointmentsToFile(Scheduler.getAppointments(), "CurrentAppointments.apts")) {
                            showMessageDialog(controlPanel, "Appointment data Saved to file");
                        }

                    }));

            controlPanel.add(setWestPanelBtns("Load Appointments from file",
                    e -> {
                        if (Scheduler.loadAppointmentsFromFile("CurrentAppointments.apts", Scheduler.getAppointments())) {
                            showMessageDialog(controlPanel, "Appointments successfully loaded from file");
                        }
                    }));

            controlPanel.add(setWestPanelBtns("Exit",
                    e -> {
                        Scheduler.loadAppointmentsFromFile("CurrentAppointments.apts", Scheduler.getAppointments());
                        frame.dispose();
                    }
            ));

        westPanel.add(controlPanel, gbc);
        return westPanel;
    }

}
