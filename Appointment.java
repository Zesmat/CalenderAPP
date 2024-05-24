package CalendarMain;

import java.util.Date;

class Appointment extends Event {
    private String doctor; // name of doctor of the appointment

    public Appointment(String eventId, String title, String description, Date date, String time, String location,
            String doctor) {
        super(eventId, title, description, date, time, location); // constructor chaining
        this.doctor = doctor;
    }

    @Override
    public void addReminder(Reminder reminder) {
        reminders.add(reminder);
    }

    // Deep copy constructor
    public Appointment(Appointment other) {
        super(other); // used to call the constructor of the superclass (Event) with the other object
                      // as an argument. This means that the superclass constructor will initialize
                      // the fields of the Event class using the corresponding fields of the other
                      // object.
        this.doctor = other.doctor;
    }

    // Getters and Setters
    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

}
