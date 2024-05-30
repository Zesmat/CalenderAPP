package CalendarMain;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class EventDetailsDialog extends JDialog {

    public EventDetailsDialog(Frame parent, List<Event> events) {
        super(parent, "Event Details", true);
        initialize(events);
    }

    private void initialize(List<Event> events) {
        setLayout(new BorderLayout());
        setSize(400, 300);

        JPanel eventPanel = new JPanel();
        eventPanel.setLayout(new BoxLayout(eventPanel, BoxLayout.Y_AXIS));
        eventPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (Event event : events) {
            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.setBorder(BorderFactory.createTitledBorder(event.getTitle()));
            panel.add(new JLabel("Description: " + event.getDescription()));
            panel.add(new JLabel("Time: " + event.getTime()));
            panel.add(new JLabel("Location: " + event.getLocation()));
            if (event instanceof Birthday) {
                panel.add(new JLabel("Person: " + ((Birthday) event).getPersonName()));
            } else if (event instanceof Appointment) {
                panel.add(new JLabel("Doctor: " + ((Appointment) event).getDoctor()));
            } else if (event instanceof Meeting) {
                List<User> participants = ((Meeting) event).getParticipants();
                String participantNames = participants.stream()
                        .map(User::getName)
                        .collect(Collectors.joining(", "));
                panel.add(new JLabel("Participants: " + participantNames));
            }
            eventPanel.add(panel);
        }

        JScrollPane scrollPane = new JScrollPane(eventPanel);
        add(scrollPane, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);

        setLocationRelativeTo(getParent());
    }
}
