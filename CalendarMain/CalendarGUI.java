package CalendarMain;

import javax.swing.*;
import java.awt.*;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarGUI {
    private User user; // User associated with the calendar
    private JFrame frame; // Main frame for the GUI
    private JComboBox<Integer> yearComboBox; // Dropdown for selecting year
    private JComboBox<String> monthComboBox; // Dropdown for selecting month
    private JPanel monthPanel; // Panel to display days of the month
    private Calendar calendar; // Calendar object to manage events

    public CalendarGUI(User user) {
        this.user = user; // Initialize user
        this.calendar = new Calendar("1", "Personal Calendar", user); // Initialize calendar
        initialize(); // Call method to set up GUI
    }

    private void initialize() {
        frame = new JFrame("Calendar Application"); // Create main frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application on frame close
        frame.setSize(1000, 700); // Set frame size
        frame.setLayout(new BorderLayout()); // Set layout manager

        JPanel navigationPanel = new JPanel(); // Create panel for navigation controls
        navigationPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Set layout for navigation panel
        navigationPanel.setBackground(new Color(66, 133, 244)); // Set background color

        // Year ComboBox
        yearComboBox = new JComboBox<>(); // Create ComboBox for years
        for (int i = 1900; i <= 2100; i++) { // Add years from 1900 to 2100
            yearComboBox.addItem(i);
        }
        yearComboBox.setSelectedItem(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR)); // Set current year
                                                                                                     // as selected
        yearComboBox.setFont(new Font("Roboto", Font.PLAIN, 16)); // Set font
        yearComboBox.setPreferredSize(new Dimension(100, 30)); // Set size
        navigationPanel.add(yearComboBox); // Add ComboBox to navigation panel

        // Month ComboBox
        monthComboBox = new JComboBox<>(); // Create ComboBox for months
        String[] months = new String[] { "January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December" }; // Month names
        for (String month : months) { // Add month names to ComboBox
            monthComboBox.addItem(month);
        }
        monthComboBox.setSelectedItem(months[java.util.Calendar.getInstance().get(java.util.Calendar.MONTH)]); // Set
                                                                                                               // current
                                                                                                               // month
                                                                                                               // as
                                                                                                               // selected
        monthComboBox.setFont(new Font("Roboto", Font.PLAIN, 16)); // Set font
        monthComboBox.setPreferredSize(new Dimension(150, 30)); // Set size
        navigationPanel.add(monthComboBox); // Add ComboBox to navigation panel

        // Add action listeners to update the calendar view when a year or month is
        // selected
        yearComboBox.addActionListener(e -> updateCalendarView());
        monthComboBox.addActionListener(e -> updateCalendarView());

        frame.add(navigationPanel, BorderLayout.NORTH); // Add navigation panel to the top of the frame

        JPanel mainPanel = new JPanel(); // Create main panel for calendar
        mainPanel.setLayout(new BorderLayout()); // Set layout manager
        mainPanel.setBackground(Color.WHITE); // Set background color

        monthPanel = new JPanel(); // Create panel to display days of the month
        monthPanel.setLayout(new GridLayout(0, 7, 5, 5)); // Set layout to a grid with 7 columns and gaps between cells
        monthPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the panel
        mainPanel.add(monthPanel, BorderLayout.CENTER); // Add month panel to the center of the main panel

        updateCalendarView(); // Initialize the calendar view

        frame.add(mainPanel, BorderLayout.CENTER); // Add main panel to the center of the frame

        // Button to add new events

        frame.setVisible(true); // Make the frame visible
    }

    private void updateCalendarView() {
        int year = (int) yearComboBox.getSelectedItem(); // Get selected year
        int month = monthComboBox.getSelectedIndex(); // Get selected month
        Component component = frame; // Reference to the main frame
        monthPanel.removeAll(); // Clear all components from the month panel

        // Days of the week headers
        String[] daysOfWeek = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        for (String day : daysOfWeek) {
            JLabel label = new JLabel(day, SwingConstants.CENTER); // Create label for each day
            label.setFont(new Font("Roboto", Font.BOLD, 16)); // Set font
            monthPanel.add(label); // Add label to the month panel
        }

        // Create calendar instance for the selected year and month
        java.util.Calendar cal = new GregorianCalendar(year, month, 1);
        int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH); // Get number of days in month
        int startDay = cal.get(java.util.Calendar.DAY_OF_WEEK) - 1; // Get the day of the week of the first day of the
                                                                    // month

        // Add empty labels for days before the first day of the month
        for (int i = 0; i < startDay; i++) {
            monthPanel.add(new JLabel(""));
        }

        // Add buttons for each day of the month
        for (int day = 1; day <= daysInMonth; day++) {
            final int currentDay = day; // Declare day as final
            JButton dayButton = new JButton(String.valueOf(currentDay)); // Create button for the day
            dayButton.setFont(new Font("Roboto", Font.PLAIN, 14)); // Set font
            dayButton.setFocusPainted(false); // Remove focus painting

            // Set background color based on event type
            Color backgroundColor = Color.WHITE; // Default color
            List<Event> events = calendar.getEvents(year, month, currentDay); // Get events for the day
            for (Event event : events) {
                if (event instanceof Birthday) {
                    backgroundColor = Color.YELLOW; // Example color for birthday events
                } else if (event instanceof Appointment) {
                    backgroundColor = Color.GREEN; // Example color for appointment events
                } else if (event instanceof Meeting) {
                    backgroundColor = Color.CYAN; // Example color for meeting events
                }
            }
            dayButton.setBackground(backgroundColor); // Set background color

            dayButton.addActionListener(e -> {
                if (!events.isEmpty()) {
                    showEventDetailsDialog(component, events); // Show event details dialog if there are events
                } else {
                    showEventDialog(component, year, month, currentDay); // Show add event dialog if no events
                }
            });

            monthPanel.add(dayButton); // Add button to the month panel
        }

        frame.revalidate(); // Revalidate the frame to refresh the layout
        frame.repaint(); // Repaint the frame to apply changes
    }

    private void showEventDialog(Component component, int year, int month, int day) {
        // Display dialog to add a new event
        AddEventDialog dialog = new AddEventDialog((Frame) SwingUtilities.getWindowAncestor(component), user, calendar,
                year, month, day);
        dialog.setVisible(true); // Make the dialog visible
        updateCalendarView(); // Update the calendar view after adding the event
    }

    private void showEventDetailsDialog(Component component, List<Event> events) {
        // Display dialog to show details of existing events
        EventDetailsDialog dialog = new EventDetailsDialog((Frame) SwingUtilities.getWindowAncestor(component), events);
        dialog.setVisible(true); // Make the dialog visible
    }

    public static void main(String[] args) {
        User user = new User("1", "Alice"); // Create a new user
        SwingUtilities.invokeLater(() -> new CalendarGUI(user)); // Run the GUI on the event dispatch thread
    }
}
