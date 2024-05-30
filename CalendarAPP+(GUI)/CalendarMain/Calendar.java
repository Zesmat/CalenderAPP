package CalendarMain;

import java.util.ArrayList;
import java.util.List;

class Calendar {
    private String calendarId;
    private String name;
    private User owner;
    private List<Event> events;
    private List<User> sharedWith;

    public Calendar(String calendarId, String name, User owner) {
        this.calendarId = calendarId;
        this.name = name;
        this.owner = owner;
        this.events = new ArrayList<>();
        this.sharedWith = new ArrayList<>();
    }

    public void addEvent(Event event) {
        events.add(event);
    }

    public void shareWith(User user) // shares the calendar with another user
    {
        sharedWith.add(user);
    }

    // Method to retrieve events for a specific year, month, and day
    // Method to retrieve events for a specific year, month, and day
    public List<Event> getEvents(int year, int month, int day) {
        List<Event> eventsInDay = new ArrayList<>();
        for (Event event : events) {
            if (event.getYear() == year && event.getMonth() == month && event.getDay() == day) {
                eventsInDay.add(event);
            }
        }
        return eventsInDay;
    }

    // Deep copy constructor
    public Calendar(Calendar other) {
        this.calendarId = other.calendarId;
        this.name = other.name;
        this.owner = new User(other.owner); // Deep copy constructor for owner
        this.events = new ArrayList<>();
        this.sharedWith = new ArrayList<>();

        for (Event event : other.events) {
            if (event instanceof Meeting) // 3shan a3rf el event ele ana feh dlw2ty dah ehh since (polymorphism)
            {
                this.events.add(new Meeting((Meeting) event));
            } else if (event instanceof Appointment) {
                this.events.add(new Appointment((Appointment) event));
            } else if (event instanceof Birthday) {
                this.events.add(new Birthday((Birthday) event));
            }
        }

        for (User user : other.sharedWith) {
            this.sharedWith.add(new User(user)); // Deep copy constructor for each user
        }
    }

    /*
     * public Calendar(Calendar other) {
     * this.calendarId = other.calendarId;
     * this.name = other.name;
     * this.owner = new User(other.owner);
     * this.events = new ArrayList<>(other.events);
     * this.sharedWith = new ArrayList<>(other.sharedWith);
     * }
     */
    public Calendar shallowCopy(Calendar other) {
        this.calendarId = other.calendarId;
        this.name = other.name;
        this.owner = other.owner;
        this.events = other.events;
        this.sharedWith = other.sharedWith;
        return this;
    }

    public List<Event> getEvents() {
        return events;
    }

    // Getters and setters
}
