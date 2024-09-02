package CalendarMain;

import java.util.Date;

class Birthday extends Event {
    private String personName;

    public Birthday(String eventId, String title, String description, Date date, String time, String location,
            String personName) {
        super(eventId, title, description, date, time, location);
        this.personName = personName;
    }

    @Override
    public void addReminder(Reminder reminder) {
        reminders.add(reminder);
    }

    public Birthday(Birthday other) {
        super(other);
        this.personName = other.personName;
    }

    public String getPersonName() {// person name
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
