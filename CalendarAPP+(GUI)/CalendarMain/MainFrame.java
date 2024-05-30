package CalendarMain;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;
import java.util.List;

public class MainFrame extends JPanel {
    private User user;
    private Calendar calendar;
    private JComboBox<Integer> yearComboBox;
    private JComboBox<String> monthComboBox;
    private JPanel monthPanel;
    private JButton[] dayButtons; // Array to store day buttons

    public MainFrame(User user, Calendar calendar) {
        this.user = user;
        this.calendar = calendar;
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());

        JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new BorderLayout());
        navigationPanel.setBackground(new Color(66, 133, 244)); // Google Calendar blue

        // Year ComboBox
        yearComboBox = new JComboBox<>();
        for (int i = 1900; i <= 2100; i++) {
            yearComboBox.addItem(i);
        }
        yearComboBox.setSelectedItem(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR));
        yearComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        navigationPanel.add(yearComboBox, BorderLayout.WEST);

        // Month ComboBox
        monthComboBox = new JComboBox<>();
        String[] months = new String[] { "January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December" };
        for (String month : months) {
            monthComboBox.addItem(month);
        }
        monthComboBox.setSelectedItem(months[java.util.Calendar.getInstance().get(java.util.Calendar.MONTH)]);
        monthComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        navigationPanel.add(monthComboBox, BorderLayout.CENTER);

        // Add action listeners to update the calendar view
        yearComboBox.addActionListener(e -> updateCalendarView());
        monthComboBox.addActionListener(e -> updateCalendarView());

        add(navigationPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        monthPanel = new JPanel();
        monthPanel.setLayout(new GridLayout(0, 7)); // Changed layout to have flexible rows
        mainPanel.add(monthPanel, BorderLayout.CENTER);

        // Create an array to store day buttons
        dayButtons = new JButton[31]; // Assuming maximum 31 days in a month

        // Create day buttons and add them to the month panel
        for (int i = 0; i < 31; i++) {
            JButton dayButton = new JButton(String.valueOf(i + 1));
            dayButton.setFont(new Font("Arial", Font.PLAIN, 14));
            dayButton.setFocusPainted(false);
            dayButton.setBackground(Color.WHITE);
            int finalI = i; // Use final variable for lambda expression
            dayButton.addActionListener(e -> showEventDialog((int) yearComboBox.getSelectedItem(),
                    monthComboBox.getSelectedIndex(), finalI + 1)); // Use finalI + 1 for the day
            monthPanel.add(dayButton);
            dayButtons[i] = dayButton; // Store the button in the array
        }

        updateCalendarView(); // Update the calendar view initially

        add(mainPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Event");
        addButton.addActionListener(new AddEventListener());
        add(addButton, BorderLayout.SOUTH);
    }

    // Method to update the calendar view
    private void updateCalendarView() {
        int year = (int) yearComboBox.getSelectedItem();
        int month = monthComboBox.getSelectedIndex();

        // Clear all day button backgrounds
        for (JButton dayButton : dayButtons) {
            dayButton.setBackground(Color.WHITE);
        }

        java.util.Calendar cal = new GregorianCalendar(year, month, 1);
        int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);

        // Iterate through the days of the month
        for (int day = 1; day <= daysInMonth; day++) {
            List<Event> events = calendar.getEvents(year, month, day);
            JButton dayButton = dayButtons[day - 1]; // Adjust for array index
            if (!events.isEmpty()) {
                Color backgroundColor = Color.WHITE; // Default color
                for (Event event : events) {
                    if (event instanceof Birthday) {
                        backgroundColor = Color.YELLOW; // Example color for birthday events
                    } else if (event instanceof Appointment) {
                        backgroundColor = Color.GREEN; // Example color for appointment events
                    } else if (event instanceof Meeting) {
                        backgroundColor = Color.BLUE; // Example color for meeting events
                    }
                }
                dayButton.setBackground(backgroundColor);
            }
        }

        revalidate();
        repaint();
    }

    // Method to show the AddEventDialog
    private void showEventDialog(int year, int month, int day) {
        AddEventDialog dialog = new AddEventDialog((Frame) SwingUtilities.getWindowAncestor(this), user, calendar, year,
                month, day);
        dialog.setVisible(true);
        updateCalendarView(); // Update the calendar view after adding an event
    }

    public static void main(String[] args) {
        User user = new User("1", "Alice");
        Calendar calendar = new Calendar("1", "Personal Calendar", user);
        JFrame frame = new JFrame("Calendar Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        MainFrame mainPanel = new MainFrame(user, calendar);
        frame.add(mainPanel, BorderLayout.CENTER);
        JButton addButton = new JButton("Add Event");
        addButton.addActionListener(mainPanel.new AddEventListener());
        frame.add(addButton, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    // ActionListener to handle adding new events
    private class AddEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            showEventDialog((int) yearComboBox.getSelectedItem(), monthComboBox.getSelectedIndex(), -1);
        }
    }
}
