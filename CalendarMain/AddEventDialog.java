package CalendarMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.GregorianCalendar;

public class AddEventDialog extends JDialog {
    private User user;
    private Calendar calendar;
    private int year;
    private int month;
    private int day;

    // Constructor for AddEventDialog
    public AddEventDialog(Frame parent, User user, Calendar calendar, int year, int month, int day) {
        super(parent, "Add Event", true); // Call the JDialog constructor to set the dialog's title and modality
        this.user = user;
        this.calendar = calendar;
        this.year = year;
        this.month = month;
        this.day = day;
        initialize(); // Initialize the dialog components
    }

    private void initialize() {
        setLayout(new BorderLayout()); // Set the layout manager for the dialog

        // Create form fields for user input
        JTextField titleField = new JTextField(20);
        JTextField descriptionField = new JTextField(20);
        JTextField timeField = new JTextField(20);
        JTextField locationField = new JTextField(20);
        JTextField extraField = new JTextField(20); // Field for doctor, participants, or person based on event type

        // Create a combo box for selecting the event type
        JComboBox<String> eventTypeComboBox = new JComboBox<>(new String[] { "Meeting", "Appointment", "Birthday" });

        // Create a panel to hold form fields and labels
        JPanel panel = new JPanel(new GridLayout(7, 2));
        panel.add(new JLabel("Title:"));
        panel.add(titleField);
        panel.add(new JLabel("Description:"));
        panel.add(descriptionField);
        panel.add(new JLabel("Time (HH:MM):"));
        panel.add(timeField);
        panel.add(new JLabel("Location:"));
        panel.add(locationField);
        panel.add(new JLabel("Event Type:"));
        panel.add(eventTypeComboBox);
        panel.add(new JLabel("Extra Info:")); // Label for extra field, will be updated based on event type
        panel.add(extraField);

        add(panel, BorderLayout.CENTER); // Add the panel to the center of the dialog

        // Add an action listener to the event type combo box
        eventTypeComboBox.addActionListener(e -> {
            String selectedEventType = (String) eventTypeComboBox.getSelectedItem();
            JLabel extraLabel = (JLabel) panel.getComponent(10); // Get the label for the extra field
            // Update the label based on the selected event type
            if (selectedEventType.equals("Meeting")) {
                extraLabel.setText("Participants:");
            } else if (selectedEventType.equals("Appointment")) {
                extraLabel.setText("Doctor:");
            } else if (selectedEventType.equals("Birthday")) {
                extraLabel.setText("Person:");
            }
        });

        // Create and add the "Add" button to the dialog
        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the values from the form fields
                String title = titleField.getText();
                String description = descriptionField.getText();
                String time = timeField.getText();
                String location = locationField.getText();
                String extra = extraField.getText();
                String eventType = (String) eventTypeComboBox.getSelectedItem();

                // Create a GregorianCalendar object for the event date
                java.util.Calendar cal = new GregorianCalendar(year, month, day);
                Date date = cal.getTime(); // Get the date from the calendar

                // Create an Event object based on the selected event type
                Event event = null;
                if (eventType.equals("Meeting")) {
                    event = new Meeting("1", title, description, date, time, location);
                } else if (eventType.equals("Appointment")) {
                    event = new Appointment("2", title, description, date, time, location, extra);
                } else if (eventType.equals("Birthday")) {
                    event = new Birthday("3", title, description, date, time, location, extra);
                }

                // Add the event to the calendar if it is not null
                if (event != null) {
                    calendar.addEvent(event);
                }

                dispose(); // Close the dialog
            }
        });
        add(addButton, BorderLayout.SOUTH); // Add the button to the bottom of the dialog

        pack(); // Size the dialog to fit its components
        setLocationRelativeTo(getParent()); // Center the dialog relative to its parent frame
    }
}
