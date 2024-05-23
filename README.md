# Project Description
This project is a comprehensive calendar application designed to help users manage various types of events, such as meetings, appointments, and birthdays. It demonstrates key Object-Oriented Programming (OOP) concepts including classes and objects, constructor chaining, deep copy constructors, encapsulation, inheritance, abstract classes, interfaces, method overriding, and polymorphism. The application allows users to create, share, and receive notifications for events, making it a robust tool for personal and collaborative scheduling.

# Key Concepts Demonstrated
This diagram provides a clear overview of the structural and behavioral relationships between different classes in the calendar application, showcasing the implementation of various OOP principles.
![CalenderApp GUI](https://github.com/SuzyAdel/CalenderAPP/assets/128175020/8e3a57a4-ab6e-4b4f-9021-cea2557ac305)

A) Inheritance: Event is an abstract class extended by Meeting, Appointment, and Birthday.

B) Polymorphism: The addReminder method in Event is overridden by the subclasses.

C) Encapsulation: Attributes are private or protected, accessed via public getter and setter methods.

D) Deep Copy Constructors: Ensures independent copies of objects for User, Calendar, Event, Meeting, Appointment, Birthday, and Reminder.

E) Interfaces: Notifiable interface is implemented by User.

# Calendar Application GUI Insights
The Calendar Application GUI provides a user-friendly interface to manage and add different types of events such as appointments, meetings, and birthdays. Below are the detailed insights into the functionality and features of the GUI:

![Main image](https://github.com/SuzyAdel/CalenderAPP/assets/128175020/7383af47-db6f-41a3-a72a-5efef90356cb)

# Key Features
1. Year and Month Selection:
   
![year dropdown](https://github.com/SuzyAdel/CalenderAPP/assets/128175020/8e69d2fa-a61d-484c-a500-6fcead7cc83f)

![month dropdown](https://github.com/SuzyAdel/CalenderAPP/assets/128175020/aaf8c005-acee-43fc-a34a-9612f84ce3fd)


Dropdown Menu: Users can select the desired year and month using dropdown menus at the top of the calendar interface.
Navigation: This allows for easy navigation to view and manage events across different months and years.

2.Event Addition:


![type dd](https://github.com/SuzyAdel/CalenderAPP/assets/128175020/b8ba5cdb-5a2a-43be-a54b-ae5a417de98b)


Event Type Dropdown: Users can choose the type of event (Appointment, Meeting, Birthday) from a dropdown list.
This selection determines the specific fields required for the event.

Input Fields: Based on the selected event type, the interface provides specific input fields:
Title: A text box to enter the event title.
Description: A text box to enter a description of the event.
Time: A text box to specify the time of the event.
Location: A text box to specify the location of the event.
Participants: (Only for Meetings) A text box or list to add participants.
Doctor: (Only for Appointments) A field to specify the doctor's name.
Person Name: (Only for Birthdays) A field to specify the name of the person whose birthday it is.

Add Button: After filling in the required details, users can click the "Add" button to save the event.

3. Event Visualization:

Color Coding: Each type of event is color-coded for easy differentiation on the calendar:
Yellow: For Birthdays
Green: For Appointments
Blue: For Meetings
Shading: The days on the calendar are shaded with the corresponding color when an event is added, providing a quick visual representation of scheduled events.
// N@ES SORA 

# Class Descriptions
# 1. CalendarMain

 ![CalenderMain](https://github.com/SuzyAdel/CalenderAPP/assets/128175020/8b0e4892-6cd6-479c-b54a-1c649e796ac7)

Purpose: The main class that serves as the entry point of the application.

Description: Initializes the application by creating users, calendars, events, and reminders. It also demonstrates the use of polymorphism by processing different types of events.

# 2. User

![User](https://github.com/SuzyAdel/CalenderAPP/assets/128175020/f13a47b3-8b37-4511-93f5-fa2be65567b7)

   
Purpose: Represents a user of the calendar application.

Description: Contains user details and a list of calendars owned by the user. It implements the Notifiable interface to receive notifications. Supports deep copying to ensure independent instances of user objects.

Attributes: userId, name, calendars.

Methods: addCalendar, sendNotification, deep copy constructor, and standard getters and setters.

# 3. Calendar

![Calender](https://github.com/SuzyAdel/CalenderAPP/assets/128175020/baed482d-c993-4341-beb6-463d3abeceda)

   
Purpose: Represents a calendar that can hold various events and be shared with other users.

Description: Contains calendar details, a list of events, and a list of users with whom the calendar is shared. Supports deep copying of the calendar, including all events and shared users.

Attributes: calendarId, name, owner, events, sharedWith.

Methods: addEvent, shareWith, deep copy constructor, and standard getters and setters.

# 4. Event

![Event](https://github.com/SuzyAdel/CalenderAPP/assets/128175020/074638d1-4466-4b4c-bc3f-430be1e90c22)

   
Purpose: Abstract base class for different types of events.

Description: Defines common properties and methods for events, such as ID, title, description, date, time, location, and reminders. It includes an abstract method addReminder for adding reminders to events and a deep copy constructor to create independent event instances.

Attributes: eventId, title, description, date, time, location, reminders.

Methods: Abstract method addReminder, deep copy constructor, and standard getters and setters.


# 5. Meeting

![Meeting](https://github.com/SuzyAdel/CalenderAPP/assets/128175020/48c00e5d-cd18-4218-82b1-84bee17c77b2)

   
Purpose: Represents a meeting event.

Description: Extends Event and includes a list of participants. Implements the addReminder method and provides functionality to add participants. Supports deep copying of meeting events.

Attributes: participants (list of User).

Methods: addParticipant, addReminder, deep copy constructor, and standard getters and setters.


# 6. Appointment

![Appointment](https://github.com/SuzyAdel/CalenderAPP/assets/128175020/b0323d4e-c42e-4154-87a9-3d35e35b0087)

   
Purpose: Represents an appointment event.

Description: Extends Event and includes details about the doctor. Implements the addReminder method and supports deep copying of appointment events.

Attributes: doctor.

Methods: addReminder, deep copy constructor, and standard getters and setters.


# 7. Birthday

![Birthday](https://github.com/SuzyAdel/CalenderAPP/assets/128175020/2ae35e35-322d-4d67-83b1-702a3a7691ce)

   
Purpose: Represents a birthday event.

Description: Extends Event and includes the name of the person whose birthday is being celebrated. Implements the addReminder method and supports deep copying of birthday events.

Attributes: personName.

Methods: addReminder, deep copy constructor, and standard getters and setters.


# 8. Reminder

![Reminder](https://github.com/SuzyAdel/CalenderAPP/assets/128175020/fb0b839a-b1b5-4158-9718-10fcae5e5c48)

   
Purpose: Represents a reminder for an event.

Description: Contains reminder details such as ID, time, and message. Supports deep copying to ensure reminders are independently copied.

# 9. CalendarApp

![CalenderApp UML](https://github.com/SuzyAdel/CalenderAPP/assets/128175020/e08b66f2-ba19-4ff4-bb65-ea1d0654d91f)

    
Purpose: Represents the calendar application with a user.

Description: Provides a structure to hold the user of the application, facilitating access to user-related operations.

Attributes: user.

Methods: getUser, setUser.

# 10. Notifiable

![Notifiable](https://github.com/SuzyAdel/CalenderAPP/assets/128175020/a787a78b-4c25-491c-a67d-efa2e922a019)

    
Purpose: Interface to provide notification functionality.

Description: Defines a method sendNotification for sending notifications, which is implemented by the User class to receive event reminders.

Methods: sendNotification.

# Example Usage in CalendarMain

The CalendarMain class demonstrates how to use the calendar application:

Create Users and Calendars: A user named Alice is created with a work calendar.
Add Events: Different types of events (meeting, appointment, birthday) are added to the calendar.
Add Reminders: Reminders are added to events to notify users.
Share Calendars: The calendar is shared with another user, Bob.
Process Events: Events are processed to demonstrate polymorphism by checking the event type and displaying specific information about the event.
This example highlights the creation and management of events, the use of deep copying to ensure data integrity, and the implementation of polymorphic behavior to handle different event types dynamically.
