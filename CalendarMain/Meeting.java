package CalendarMain;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

class Meeting extends Event {
    private List<User> participants; // list of users participating in the event

    public Meeting(String eventId, String title, String description, Date eventDate, String time, String location) {
        super(eventId, title, description, eventDate, time, location);
        this.participants = new ArrayList<>();
    }

    @Override
    public void addReminder(Reminder reminder) {
        reminders.add(reminder);
    }

    // add participant to the meeting
    public void addParticipant(User user) {
        participants.add(user);
    }

    @Override
    public String toString() {
        return super.toString() + " with participants: " + participants;
    }

    // copy constructor
    public Meeting(Meeting other) {
        super(other);
        this.participants = new ArrayList<>(other.participants);
    }

    // Getters and Setters
    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

}
