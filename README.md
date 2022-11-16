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

# Instructions for Grader //TODO

- You can generate the first required event related to adding Xs to a Y by...
- You can generate the second required event related to adding Xs to a Y by...
- You can locate my visual component by...
- You can save the state of my application by...
- You can reload the state of my application by...

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