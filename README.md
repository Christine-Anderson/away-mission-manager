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
- assign crew members (X) to away mission (Y)
- remove crew members from the away mission team
- select a crew member and get stats on the crew member, for example, rank and division
- emergency beam out the away team 
- have the option to save the current starship data when I select the quit option from the application menu 
- have the option to load the previous starship data when I start the application

### Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by:
  - First, in the load window either click the "yes" button to load the starship data or the "no" button to progress to
  the Captain's name window. Input your name and click the "enter" button.
  - Then click the "create mission" button to create a new away mission. Note that if you loaded previous starship data
  with a current away mission, this menu will be skipped. This will generate the crew manager and mission manager menus.
  - In the crew manager window, the left panel displays the list of current crew members. Select crew member(s) from 
  the list by clicking their names (holding shift to select multiple crew members) and click the "add" button to add 
  them to the away team (right panel).
- You can generate the second required event related to adding Xs to a Y by:
  - First navigate to the crew manager menu as above.
  - Crew member(s) may be removed from the away team by selecting crew member(s) in the away team panel following a
  similar procedure as above and clicking the "remove" button.
  - As well, selecting crew member(s) from the crew member (left) panel as above and clicking the "stats" button will
  generate a window displaying stats on the selected crew member(s)
- You can locate my visual component by starting the program. The load window will display an image of the starship.
- You can save the state of my application by clicking the close button at any time which will bring up the save window.
Click the "yes" button to save and exit the program. Click the "no" button to exit without saving.
- You can reload the state of my application by clicking the "yes" button in the load window when you start the program.

### Phase 4: Task 2
A representative sample of the events that occur when my program runs:

Tue Nov 22 16:12:47 PST 2022
Created new Away Mission 14268714 on stardate 4102.6.
Tue Nov 22 16:12:52 PST 2022
William Riker added to the Away Team.
Tue Nov 22 16:12:52 PST 2022
Geordi La Forge added to the Away Team.
Tue Nov 22 16:12:52 PST 2022
Data added to the Away Team.
Tue Nov 22 16:12:52 PST 2022
Reginald Baclay added to the Away Team.
Tue Nov 22 16:12:54 PST 2022
Reginald Baclay removed from the Away Team.
Tue Nov 22 16:12:55 PST 2022
Mission 14268714 started.
Tue Nov 22 16:12:55 PST 2022
Away Team transported off of the Starship.
Tue Nov 22 16:12:57 PST 2022
Tribble added to the Away Team.
Tue Nov 22 16:12:57 PST 2022
Tribble transported off of the Starship.
Tue Nov 22 16:12:59 PST 2022
Tribble removed from the Away Team.
Tue Nov 22 16:12:59 PST 2022
Tribble transported to the Starship.
Tue Nov 22 16:12:59 PST 2022
Tribble returned injured.
Tue Nov 22 16:13:00 PST 2022
Mission 14268714 objective complete.
Tue Nov 22 16:13:00 PST 2022
Mission 14268714 ended.
Tue Nov 22 16:13:00 PST 2022
Away Team transported to the Starship.
Tue Nov 22 16:13:00 PST 2022
William Riker returned dead.
Tue Nov 22 16:13:00 PST 2022
Geordi La Forge returned injured.
Tue Nov 22 16:13:00 PST 2022
Data returned healthy.
Tue Nov 22 16:13:00 PST 2022
Mission 14268714 added to the Away Mission Log.
Tue Nov 22 16:13:02 PST 2022
Created new Away Mission 14268715 on stardate 4102.7.
Tue Nov 22 16:13:05 PST 2022
Tom Paris added to the Away Team.
Tue Nov 22 16:13:09 PST 2022
Harry Kim added to the Away Team.
Tue Nov 22 16:13:13 PST 2022
Seven of Nine added to the Away Team.
Tue Nov 22 16:13:16 PST 2022
Mission 14268715 started.
Tue Nov 22 16:13:16 PST 2022
Away Team transported off of the Starship.
Tue Nov 22 16:13:18 PST 2022
Mission 14268715 ended.
Tue Nov 22 16:13:18 PST 2022
Away Team transported to the Starship.
Tue Nov 22 16:13:18 PST 2022
Tom Paris returned dead.
Tue Nov 22 16:13:18 PST 2022
Harry Kim returned dead.
Tue Nov 22 16:13:18 PST 2022
Seven of Nine returned injured.
Tue Nov 22 16:13:18 PST 2022
Mission 14268715 added to the Away Mission Log.

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