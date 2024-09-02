package CalendarMain;

//import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class CalendarMain {
        public static void main(String[] args) {
                // Create users
                User user1 = new User("1", "Alice");
                User user2 = new User("2", "Alex");
                User user3 = new User("3", "Kayla");
                List<Event> events = new ArrayList<>();

                // Create a calendar
                Calendar calendar = new Calendar("1", "Personal Calendar", user3);

                // Create events
                Event meeting = new Meeting("E1", "Team Meeting", "Discuss project status", new Date(), "10:00 AM",
                                "Conference Room");
                ((Meeting) meeting).addParticipant(user1);
                ((Meeting) meeting).addParticipant(user2);
                events.add(meeting);

                Event appointment = new Appointment("E2", "Doctor Appointment", "Annual checkup", new Date(), "2:00 PM",
                                "Clinic", "Dr. Smith");
                events.add(appointment);

                Event birthday = new Birthday("E3", "John's Birthday", "Celebrate John's birthday", new Date(),
                                "6:00 PM", "John's House", "John");
                events.add(birthday);

                // Process the events
                System.out.println("Processing events:");
                for (Event event : events) {
                        System.out.println("\nEvent: " + event.getTitle());
                        System.out.println("Description: " + event.getDescription());
                        System.out.println("Date: " + event.geteventDate());
                        System.out.println("Time: " + event.getTime());
                        System.out.println("Location: " + event.getLocation());
                        event.addReminder(new Reminder("R1", event.geteventDate(), "Reminder for " + event.getTitle()));

                        // Polymorphic behavior
                        if (event instanceof Meeting) {
                                Meeting specificMeeting = (Meeting) event;
                                System.out.println("Participants: " + specificMeeting.getParticipants());
                        } else if (event instanceof Appointment) {
                                Appointment specificAppointment = (Appointment) event;
                                System.out.println("Doctor: " + specificAppointment.getDoctor());
                        } else if (event instanceof Birthday) {
                                Birthday specificBirthday = (Birthday) event;
                                System.out.println("Person: " + specificBirthday.getPersonName());
                        }
                }

                // Add events to the calendar
                calendar.addEvent(meeting);
                calendar.addEvent(appointment);
                calendar.addEvent(birthday);

                // Add calendar to the user
                user3.addCalendar(calendar);

                // Send notifications for the user's events
                System.out.println("\nSending notifications:");
                for (Event event : calendar.getEvents()) {
                        for (Reminder reminder : event.getReminders()) {
                                System.out.println(
                                                "Notification for " + user3.getName() + ": " + reminder.getMessage());
                        }
                }
        }
}
