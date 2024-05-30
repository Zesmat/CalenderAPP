package CalendarMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class AddEventDialog extends JDialog {
    private User user;
    private Calendar calendar;
    private int year;
    private int month;
    private int day;

    public AddEventDialog(Frame parent, User user, Calendar calendar, int year, int month, int day) {
        super(parent, "Add Event", true);
        this.user = user;
        this.calendar = calendar;
        this.year = year;
        this.month = month;
        this.day = day;
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());

        // Create form fields
        JTextField titleField = new JTextField(20);
        JTextField descriptionField = new JTextField(20);
        JTextField timeField = new JTextField(20);
        JTextField locationField = new JTextField(20);
        JTextField extraField = new JTextField(20); // For doctor, participants, person

        JComboBox<String> eventTypeComboBox = new JComboBox<>(new String[] { "Meeting", "Appointment", "Birthday" });

        // Create panel and add form fields
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
        panel.add(new JLabel("Extra Info:"));
        panel.add(extraField);

        add(panel, BorderLayout.CENTER);

        eventTypeComboBox.addActionListener(e -> {
            String selectedEventType = (String) eventTypeComboBox.getSelectedItem();
            JLabel extraLabel = (JLabel) panel.getComponent(10);
            if (selectedEventType.equals("Meeting")) {
                extraLabel.setText("Participants:");
            } else if (selectedEventType.equals("Appointment")) {
                extraLabel.setText("Doctor:");
            } else if (selectedEventType.equals("Birthday")) {
                extraLabel.setText("Person:");
            }
        });

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String description = descriptionField.getText();
                String time = timeField.getText();
                String location = locationField.getText();
                String extra = extraField.getText();
                String eventType = (String) eventTypeComboBox.getSelectedItem();

                java.util.Calendar cal = new GregorianCalendar(year, month, day);
                Date date = cal.getTime();

                Event event = null;
                if (eventType.equals("Meeting")) {
                    event = new Meeting("1", title, description, date, time, location, Arrays.asList(extra.split(",")));
                } else if (eventType.equals("Appointment")) {
                    event = new Appointment("2", title, description, date, time, location, extra);
                } else if (eventType.equals("Birthday")) {
                    event = new Birthday("3", title, description, date, time, location, extra);
                }

                if (event != null) {
                    calendar.addEvent(event);
                }

                dispose();
            }
        });
        add(addButton, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(getParent());
    }
}
