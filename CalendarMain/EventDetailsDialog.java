package CalendarMain;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class EventDetailsDialog extends JDialog {

    // Constructor for the EventDetailsDialog class
    public EventDetailsDialog(Frame parent, List<Event> events) {
        super(parent, "Event Details", true); // Call the JDialog constructor to set the dialog's title and modality
        initialize(events); // Initialize the dialog components with the list of events
    }

    // Method to initialize the dialog components
    private void initialize(List<Event> events) {
        setLayout(new BorderLayout()); // Set the layout manager for the dialog
        setSize(400, 300); // Set the size of the dialog

        // Create a panel to hold event details
        JPanel eventPanel = new JPanel();
        eventPanel.setLayout(new BoxLayout(eventPanel, BoxLayout.Y_AXIS)); // Use BoxLayout for vertical alignment
        eventPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the panel

        // Loop through the list of events and create a panel for each event
        for (Event event : events) {
            JPanel panel = new JPanel(new GridLayout(0, 1)); // Use GridLayout to arrange components in a single column
            panel.setBorder(BorderFactory.createTitledBorder(event.getTitle())); // Set the panel's border with the
                                                                                 // event title

            // Add event details to the panel
            panel.add(new JLabel("Description: " + event.getDescription()));
            panel.add(new JLabel("Time: " + event.getTime()));
            panel.add(new JLabel("Location: " + event.getLocation()));

            // Add additional details based on the event type
            if (event instanceof Birthday) {
                panel.add(new JLabel("Person: " + ((Birthday) event).getPersonName())); // Add person name for Birthday
                                                                                        // events
            } else if (event instanceof Appointment) {
                panel.add(new JLabel("Doctor: " + ((Appointment) event).getDoctor())); // Add doctor name for
                                                                                       // Appointment events
            } else if (event instanceof Meeting) {
                List<User> participants = ((Meeting) event).getParticipants(); // Get the list of participants for
                                                                               // Meeting events
                String participantNames = participants.stream()
                        .map(User::getName)
                        .collect(Collectors.joining(", ")); // Join participant names into a single string
                panel.add(new JLabel("Participants: " + participantNames)); // Add participant names to the panel
            }

            eventPanel.add(panel); // Add the event panel to the main event panel
        }

        // Create a scroll pane to allow scrolling through the events
        JScrollPane scrollPane = new JScrollPane(eventPanel);
        add(scrollPane, BorderLayout.CENTER); // Add the scroll pane to the center of the dialog

        // Create and add a "Close" button to the dialog
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose()); // Close the dialog when the button is clicked
        add(closeButton, BorderLayout.SOUTH); // Add the button to the bottom of the dialog

        setLocationRelativeTo(getParent()); // Center the dialog relative to its parent frame
    }
}
