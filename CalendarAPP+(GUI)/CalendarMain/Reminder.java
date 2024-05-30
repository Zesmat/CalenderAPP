package CalendarMain;

import java.util.Calendar;
import java.util.Date;

class Reminder {
    private String reminderId;
    private Date eventDate;
    private String message;

    // Constructor sets the event date to 8 AM the day before the event
    public Reminder(String reminderId, Date eventDate, String message) {
        this.reminderId = reminderId;
        this.eventDate = calculateEventDate(eventDate);
        this.message = message;
    }

    // Copy constructor for deep copy
    public Reminder(Reminder other) {
        this.reminderId = other.reminderId;
        this.eventDate = new Date(other.eventDate.getTime());
        this.message = other.message;
    }

    // Calculate the event date based on the input date
    private Date calculateEventDate(Date eventDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(eventDate);
        calendar.add(Calendar.DAY_OF_MONTH, -1); // Subtract one day
        calendar.set(Calendar.HOUR_OF_DAY, 8); // Set to 8 AM
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    public String getMessage() {
        return message;
    }

    public String getReminderId() {
        return reminderId;
    }

    public void setReminderId(String reminderId) {
        this.reminderId = reminderId;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Getters and setters
}