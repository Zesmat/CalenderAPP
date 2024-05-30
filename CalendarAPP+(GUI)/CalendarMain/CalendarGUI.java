package CalendarMain;

import javax.swing.*;
import java.awt.*;
import java.util.GregorianCalendar;
import java.util.List;

public class CalendarGUI {
    private User user;
    private JFrame frame;
    private JComboBox<Integer> yearComboBox;
    private JComboBox<String> monthComboBox;
    private JPanel monthPanel;
    private Calendar calendar;

    public CalendarGUI(User user) {
        this.user = user;
        this.calendar = new Calendar("1", "Personal Calendar", user);
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Calendar Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(new BorderLayout());

        JPanel navigationPanel = new JPanel();
        navigationPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        navigationPanel.setBackground(new Color(66, 133, 244));

        // Year ComboBox
        yearComboBox = new JComboBox<>();
        for (int i = 1900; i <= 2100; i++) {
            yearComboBox.addItem(i);
        }
        yearComboBox.setSelectedItem(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR));
        yearComboBox.setFont(new Font("Roboto", Font.PLAIN, 16));
        yearComboBox.setPreferredSize(new Dimension(100, 30));
        navigationPanel.add(yearComboBox);

        // Month ComboBox
        monthComboBox = new JComboBox<>();
        String[] months = new String[] { "January", "February", "March", "April", "May", "June", "July", "August",
                "September", "October", "November", "December" };
        for (String month : months) {
            monthComboBox.addItem(month);
        }
        monthComboBox.setSelectedItem(months[java.util.Calendar.getInstance().get(java.util.Calendar.MONTH)]);
        monthComboBox.setFont(new Font("Roboto", Font.PLAIN, 16));
        monthComboBox.setPreferredSize(new Dimension(150, 30));
        navigationPanel.add(monthComboBox);

        // Add action listeners to update the calendar view
        yearComboBox.addActionListener(e -> updateCalendarView());
        monthComboBox.addActionListener(e -> updateCalendarView());

        frame.add(navigationPanel, BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        monthPanel = new JPanel();
        monthPanel.setLayout(new GridLayout(0, 7, 5, 5)); // Add gaps between cells
        monthPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        mainPanel.add(monthPanel, BorderLayout.CENTER);

        updateCalendarView();

        frame.add(mainPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("Add Event");
        addButton.setFont(new Font("Roboto", Font.PLAIN, 16));
        addButton.setBackground(new Color(66, 133, 244));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setPreferredSize(new Dimension(120, 40));
        addButton.addActionListener(
                e -> showEventDialog(frame, (int) yearComboBox.getSelectedItem(), monthComboBox.getSelectedIndex(),
                        -1));
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(addButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void updateCalendarView() {
        int year = (int) yearComboBox.getSelectedItem();
        int month = monthComboBox.getSelectedIndex();
        Component component = frame;
        monthPanel.removeAll();

        String[] daysOfWeek = { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        for (String day : daysOfWeek) {
            JLabel label = new JLabel(day, SwingConstants.CENTER);
            label.setFont(new Font("Roboto", Font.BOLD, 16));
            monthPanel.add(label);
        }

        java.util.Calendar cal = new GregorianCalendar(year, month, 1);
        int daysInMonth = cal.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        int startDay = cal.get(java.util.Calendar.DAY_OF_WEEK) - 1;

        for (int i = 0; i < startDay; i++) {
            monthPanel.add(new JLabel(""));
        }

        for (int day = 1; day <= daysInMonth; day++) {
            final int currentDay = day; // Declare day as final
            JButton dayButton = new JButton(String.valueOf(currentDay));
            dayButton.setFont(new Font("Roboto", Font.PLAIN, 14));
            dayButton.setFocusPainted(false);

            // Set background color based on event type
            Color backgroundColor = Color.WHITE; // Default color
            List<Event> events = calendar.getEvents(year, month, currentDay);
            for (Event event : events) {
                if (event instanceof Birthday) {
                    backgroundColor = Color.YELLOW; // Example color for birthday events
                } else if (event instanceof Appointment) {
                    backgroundColor = Color.GREEN; // Example color for appointment events
                } else if (event instanceof Meeting) {
                    backgroundColor = Color.CYAN; // Example color for meeting events
                }
            }
            dayButton.setBackground(backgroundColor);

            dayButton.addActionListener(e -> {
                if (!events.isEmpty()) {
                    showEventDetailsDialog(component, events);
                } else {
                    showEventDialog(component, year, month, currentDay);
                }
            });

            monthPanel.add(dayButton);
        }

        frame.revalidate();
        frame.repaint();
    }

    private void showEventDialog(Component component, int year, int month, int day) {
        AddEventDialog dialog = new AddEventDialog((Frame) SwingUtilities.getWindowAncestor(component), user, calendar,
                year, month, day);
        dialog.setVisible(true);
        updateCalendarView();
    }

    private void showEventDetailsDialog(Component component, List<Event> events) {
        EventDetailsDialog dialog = new EventDetailsDialog((Frame) SwingUtilities.getWindowAncestor(component), events);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        User user = new User("1", "Alice");
        SwingUtilities.invokeLater(() -> new CalendarGUI(user));
    }
}
