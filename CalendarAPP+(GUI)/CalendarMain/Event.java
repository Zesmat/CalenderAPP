package CalendarMain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

abstract class Event {
    protected String eventId;
    protected String title;
    protected String description;
    protected Date eventDate;
    protected String time;
    protected String location;
    protected List<Reminder> reminders;
    protected static final int NOTIFICATION_HOUR = 8; // 8 AM the day before the event

    public Event(String eventId, String title, String description, Date eventDate, String time, String location) {
        this.eventId = eventId;
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.time = time;
        this.location = location;
        this.reminders = new ArrayList<>();
    }

    public abstract void addReminder(Reminder reminder); // Abstract method to add a reminder to the event (must be
                                                         // implemented by subclasses). //since abstract so, lazem el
                                                         // classes ele ht-inherit mno t-implement override lel method
                                                         // deh

    // Deep copy constructor
    public Event(Event other) {
        this.eventId = other.eventId;
        this.title = other.title;
        this.description = other.description;
        this.eventDate = new Date(other.eventDate.getTime());
        this.time = other.time;
        this.location = other.location;
        this.reminders = new ArrayList<>();

        for (Reminder reminder : other.reminders) {
            this.reminders.add(new Reminder(reminder));
        }
    }

    /*
     * public Event(Event other) //shallow copy
     * {
     * return new Event(other.eventId, other.title, other.description, other.date,
     * other.time, other.location) {
     * 
     * @Override
     * public void addReminder(Reminder reminder)
     * {
     * // Implement in subclass
     * }
     * };
     * }
     */

    public List<Reminder> getReminders() {
        return reminders;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date geteventDate() {
        return eventDate;
    }

    public void seteventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }

    public static int getNotificationHour() {
        return NOTIFICATION_HOUR;
    }

    public int getDay() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(eventDate);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public int getYear() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(eventDate);
        return cal.get(Calendar.YEAR);
    }

    public int getMonth() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(eventDate);
        return cal.get(Calendar.MONTH);
    }

}
