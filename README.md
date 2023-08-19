## Away Mission Manager Application

**To *boldly* where no one has gone before...**

You are a starship captain on the USS Intrepid (NCC-74600) in the late 24th century.

This application will be used by the starship captain (who definitely should not be on away missions) to create new
away teams for missions and manage the away team from the safety of the ship.

This application will fill the current need of the starship captain to feel involved in the away mission while still
following Starfleet Regulation Section 12, Paragraph 4.

### User Stories

As the Captain, I want to be able to:
- create a new away mission
- end an away mission
- view previous missions in the mission log
- assign crew members to away mission
- remove crew members from the away mission team
- select a crew member and get stats on the crew member, for example, rank and division
- emergency beam out the away team 
- have the option to save the current starship data when I select the quit option from the application menu 
- have the option to load the previous starship data when I start the application

### Away Mission Manager Demo

![Away Mission Manager Demo](images/Away_Mission_Manager_Demo.gif)

### Event Log Demo

Sat Aug 19 15:08:45 PDT 2023  
Created new away mission 14268714 on stardate 4102.6.  
Sat Aug 19 15:09:06 PDT 2023  
Data added to the away team.  
Sat Aug 19 15:09:09 PDT 2023  
Reginald Baclay added to the away team.  
Sat Aug 19 15:09:13 PDT 2023  
Lwaxana Troi added to the away team.  
Sat Aug 19 15:09:16 PDT 2023  
Tribble added to the away team.  
Sat Aug 19 15:09:22 PDT 2023  
Mission 14268714 started.  
Sat Aug 19 15:09:22 PDT 2023  
Away team transported off of the starship.  
Sat Aug 19 15:09:31 PDT 2023  
Reginald Baclay removed from the away team.  
Sat Aug 19 15:09:31 PDT 2023  
Reginald Baclay transported to the starship.  
Sat Aug 19 15:09:31 PDT 2023  
Reginald Baclay returned dead.  
Sat Aug 19 15:09:37 PDT 2023  
Mission 14268714 objective complete.  
Sat Aug 19 15:09:37 PDT 2023  
Mission 14268714 ended.  
Sat Aug 19 15:09:37 PDT 2023  
Away team transported to the starship.  
Sat Aug 19 15:09:37 PDT 2023  
Data returned healthy.  
Sat Aug 19 15:09:37 PDT 2023  
Lwaxana Troi returned injured.  
Sat Aug 19 15:09:37 PDT 2023  
Tribble returned healthy. 
Sat Aug 19 15:09:37 PDT 2023  
Mission 14268714 added to the away mission Log.  
Sat Aug 19 15:09:43 PDT 2023  
Created new away mission 14268715 on stardate 4102.7.  
Sat Aug 19 15:10:19 PDT 2023  
Worf Rozhenko added to the away team.  
Sat Aug 19 15:10:24 PDT 2023  
The Doctor added to the away team.  
Sat Aug 19 15:10:27 PDT 2023  
Giant Spock added to the away team.  
Sat Aug 19 15:10:31 PDT 2023  
Giant Spock removed from the away team.  
Sat Aug 19 15:10:35 PDT 2023  
Mission 14268715 started.  
Sat Aug 19 15:10:35 PDT 2023  
Away team transported off of the starship.  
Sat Aug 19 15:10:43 PDT 2023  
Mission 14268715 ended.  
Sat Aug 19 15:10:43 PDT 2023  
Away team transported to the starship.  
Sat Aug 19 15:10:43 PDT 2023  
Worf Rozhenko returned injured.  
Sat Aug 19 15:10:43 PDT 2023  
The Doctor returned injured.  
Sat Aug 19 15:10:43 PDT 2023  
Mission 14268715 added to the away mission Log.  

### UML Design Diagram

![UML diagram](images/UML_Design_Diagram.png?raw=true)

#### Citations
1. The AwayMissionManagerApp Class console interface is based on code from the TellerApp project provided in class 
   1. I based the structure of methods processing user input, processing user command, and printing menu options on this 
       example
2. The classes in the persistence package, tests in the persistence package, and JSon writing methods in the Starship, 
   AwayMission, and CrewMember classes are based on code from the JsonSerializationDemo project provided in class 
   1. I based the structure of the classes on the example and used it to learn how to read and write JSon files
3. The AwayMissionManagerGUI Class is based on example code from www.javatpoint.com tutorials on Java Swing and Layout 
Managers
   1. I used these examples to learn how to make each component and what different components were available
4. The AwayMissionManagerGUI Class is based on example code from https://docs.oracle.com/javase/tutorial/uiswing/components/
tutorials
   1. I used these examples to learn how to make, adjust, and use lists and scroll panes
5. The AwayMissionManagerGUI Class is based on example code from https://stackoverflow.com/questions/6578205/swing-jlabel-text-change-on-the-running-application
    1. I used this example to learn how to use event handling
6. The EventLog, Event, EventLogTest, and EventTest Classes are all based on code from the AlarmSystem project provided 
in class
   1. These classes were taken directly from the project and only the test classes were modified