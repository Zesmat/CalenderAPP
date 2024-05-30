package CalendarMain;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;

public class CalendarMain {
        public static void main(String[] args) {
                // Create a user
                User user1 = new User("1", "Alice");
                User user2 = new User("2", "Alex");
                User user3 = new User("3", "Alex");
                List<Event> events = new ArrayList<>();

                // Create a calendar
                Calendar calendar = new Calendar("1", "Personal Calendar", user3);

                Event meeting = new Meeting("E1", "Team Meeting", "Discuss project status", new Date(), "10:00 AM",
                                "Conference Room", null);
                ((Meeting) meeting).addParticipant(user1);
                ((Meeting) meeting).addParticipant(user2);
                events.add(meeting);

                Event appointment = new Appointment("E2", "Doctor Appointment", "Annual checkup", new Date(), "2:00 PM",
                                "Clinic", "Dr. Smith");
                events.add(appointment);

                Event birthday = new Birthday("E3", "John's Birthday", "Celebrate John's birthday", new Date(),
                                "6:00 PM", "John's House", "John");
                events.add(birthday);

                // Process the events using the CalendarApp
                for (Event event : events) {
                        System.out.println("Processing event: " + event.getTitle());
                        event.addReminder(new Reminder("R1", event.geteventDate(), "Reminder for " + event.getTitle()));

                        // Polymorphic behavior: determine the specific type of event
                        if (event instanceof Meeting) {
                                Meeting specificMeeting = (Meeting) event;
                                System.out.println("This is a meeting with participants: "
                                                + specificMeeting.getParticipants());
                        } else if (event instanceof Appointment) {
                                Appointment specificAppointment = (Appointment) event;
                                System.out.println("This is an appointment with doctor: "
                                                + specificAppointment.getDoctor());
                        } else if (event instanceof Birthday) {
                                Birthday specificBirthday = (Birthday) event;
                                System.out.println("This is a birthday for: " + specificBirthday.getPersonName());
                        }
                }
                // Add events to the calendar
                calendar.addEvent(meeting);
                calendar.addEvent(appointment);
                calendar.addEvent(birthday);

                // Add calendar to the user
                user3.addCalendar(calendar);

                // Send notifications for the user's events
                for (Event event : calendar.getEvents()) {
                        for (Reminder reminder : event.getReminders()) {
                                user3.sendNotification(reminder.getMessage());
                        }
                }
        }
}
