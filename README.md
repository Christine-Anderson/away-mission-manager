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

### To Do

If I had more time to work on the project, I would do the following refactoring to improve my design:
- use a HashMap instead of an ArrayList as my data structure for the lists of crew members on the starship and on the
away team as I am working with unordered sets with no duplicates
- three of my classes have low cohesion
  - this is evident in the UML diagram for Starship which is maintaining a single AwayMission, a list of AwayMission,
  and a list of CrewMember
- they be broken up into smaller classes as follows:
1. **AwayMissionManagerGUI** would have several classes representing different parts of the UI:  
for example, missionCreationWindow, missionManagerWindow, and crewManagerWindow
2. **Starship** would have fields name, ship ID, and one of each of the following classes:
   | Class  | Select fields and methods |
   | ------------- |:-------------:|
   | Captain      | firstName, LastName     |
   | CrewManger      | set of CrewMember, add, remove     |
   | CurrentAwayMissionManager      | currentAwayMission, awayMissionID, create, start, end, emergencyBeamOut     |
   | MissionLog      | list of (previous) AwayMission, add, toString     | 
   | Stardate      | currentStardate, updateStardate, toString     |
3. **AwayMission** would have awayMissionID, stardate, isActive, and isObjectiveComplete; 
   | Class  | Select fields and methods |
   | ------------- |:-------------:|
   | AwayTeam      | set of CrewMembers, add, remove, transport     |

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