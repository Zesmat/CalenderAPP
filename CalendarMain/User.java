package CalendarMain;

import java.util.ArrayList;
import java.util.List;

class User implements Notifiable {
    private String userId;
    private String name;
    private List<Calendar> calendars; // user can have many calenders to separate diff types of events (work or
                                      // personal) or to distribute it

    // constructor
    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.calendars = new ArrayList<>(); // initialize empty list
    }

    // add a calendar to user's list of calendars
    public void addCalendar(Calendar calendar) {
        calendars.add(calendar);
    }

    @Override
    public void sendNotification(String message) // send notification to user
    {
        System.out.println("Notification for " + name + ": " + message);
    }

    // Deep copy constructor // the new User will not affect the calendars in the
    // original User

    public User(User original) // if (shallow copy) so, both the original and new 'User' objects would share
                               // the same Calendar objects. Changes made to Calendar object from one User
                               // would be reflected in the other User, which we doesn't need.
    {
        this.userId = original.userId;
        this.name = original.name;
        this.calendars = new ArrayList<>(); // Create a new list to hold Calendar objects
        // Iterate through the original user's calendars list
        for (Calendar calendar : original.calendars) {
            this.calendars.add(new Calendar(calendar)); // Add a new Calendar object to the new list using the Calendar
                                                        // deep copy constructor
        }
    }

    // Getters and setters

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Calendar> getCalendars() {
        return calendars;
    }

    public void setCalendars(List<Calendar> calendars) {
        this.calendars = calendars;
    }

}