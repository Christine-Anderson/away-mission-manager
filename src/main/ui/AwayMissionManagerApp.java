package ui;

import model.*;

import java.util.Scanner;

// Away mission manager application
// This class is based on code from TellerApp ui example provided in class
public class AwayMissionManagerApp {
    private Starship starship;
    private Scanner input;

    // EFFECTS: runs the teller application
    public AwayMissionManagerApp(){
        runAwayMissionManager();
    }

    //TODO update all specifications
    // MODIFIES: this, Starship, AwayMission, CrewMember //TODO figure out modfies for this
    // EFFECTS: processes user input
    private void runAwayMissionManager(){
        boolean keepGoing = true;
        String command;

        initializeStarship();
        inputCaptainName();

        while (keepGoing) {
            mainDisplayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("quit")) {
                keepGoing = false;
            } else {
                processCommandMainMenu(command);
            }
        }
        System.out.println("\nLive long and prosper.");
    }

    // MODIFIES: this, Starship
    // EFFECTS: initializes starship and crew
    private void initializeStarship() {
        starship = new Starship("Captain", "Name");
        CrewMember cm1 = new CrewMember("William", "Riker", Rank.COMMANDER, Division.COMMAND);
        CrewMember cm2 = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER, Division.SCIENCES);
        CrewMember cm3 = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER, Division.SCIENCES);
        CrewMember cm4 = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER, Division.SCIENCES);
        CrewMember cm5 = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER, Division.SCIENCES);
        CrewMember cm6 = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER, Division.SCIENCES);
        CrewMember cm7 = new CrewMember("Giant", "Spock", Rank.COMMANDER, Division.SCIENCES);
        starship.addCrewMember(cm1);
        starship.addCrewMember(cm2);
        starship.addCrewMember(cm3);
        starship.addCrewMember(cm4);
        starship.addCrewMember(cm5);
        starship.addCrewMember(cm6);
        starship.addCrewMember(cm7);

        input = new Scanner(System.in);
    }

    // MODIFIES: this, Starship
    // EFFECTS: gets Captain's name from user
    private void inputCaptainName() {
        String fn = null;
        String ln = null;

        System.out.println(starship.getShipName() + " (" + starship.getShipID() + ")");
        System.out.println("--------------------------------");
        System.out.println("Enter your first and last name:");

        fn = input.next();
        ln = input.next();

        starship.setFirstNameOfCaptain(fn);
        starship.setLastNameOfCaptain(ln);
        System.out.println("\nWelcome Captain " + starship.getLastNameOfCaptain());

    }

    // EFFECTS: displays main menu of options to user
    private void mainDisplayMenu() {
        System.out.println("\nTo view the current crew, enter \"crew\".");
        System.out.println("To view the away mission menu, enter \"mission\".");
        System.out.println("To quit at any time enter \"quit\"");
    }


    // EFFECTS: processes user command for main menu
    private void processCommandMainMenu(String command) {
        if (command.equals("crew")) {
            currentCrew();
        } else if (command.equals("mission")) {
            awayMissions();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: prints current crew and allows user to get crew stats
    private void currentCrew() {
        boolean keepGoing = true;
        String command;
        int num;

        while (keepGoing) {
            printCurrentCrew();
            command = input.next();

            if (command.equals("back")) {
                keepGoing = false;
            } else if (command.equals("stats")) {
                System.out.println("\nEnter the number of crew member you would like stats on:");
                num = input.nextInt();
                printCrewMemberStats(num);
            } else {
                System.out.println("Selection not valid...");
            }
        }
    }

    // MODIFIES: this, AwayMission
    // EFFECTS: displays away mission menu to user
    private void awayMissions() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            awayMissionsMenu();
            command = input.next();

            if (command.equals("back")) {
                keepGoing = false;
            } else {
                processCommandAwayMissionsMenu(command);
            }
        }
    }

    // EFFECTS: displays away mission menu to user
    private void awayMissionsMenu() {
        System.out.println("\nTo view the mission log, enter \"log\".");
        System.out.println("To create an away mission, enter \"new\".");
        System.out.println("To return to the previous menu enter \"back\"");
    }

    // MODIFIES:
    // EFFECTS: processes user command for main menu
    private void processCommandAwayMissionsMenu(String command) {
        if (command.equals("log")) {
            printAwayMissionLog();
        } else if (command.equals("new")) {
            AwayMission am = new AwayMission(starship.getAwayMissionID(), starship.getCurrentStardate());
            starship.setCurrentAwayMission(am);
            currentAwayMission();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this, AwayMission
    // EFFECTS: displays away mission menu to user
    private void currentAwayMission() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            currentAwayMissionMenu();
            command = input.next();

            if (command.equals("back")) {
                keepGoing = false;
            } else {
                processCommandCurrentAwayMissionMenu(command);
            }
        }
    }

    // EFFECTS: displays away mission menu to user
    private void currentAwayMissionMenu() {
        System.out.println("\nTo manage the away team, enter \"team\".");
        System.out.println("To start the away mission, enter \"start\".");
        System.out.println("To end the away mission, enter \"end\".");
        System.out.println("To emergency beam out the away team, enter \"Beam me up Scottie!\".");
        System.out.println("To return to the previous menu enter \"back\"");
    }

    // MODIFIES:
    // EFFECTS: processes user command for main menu
    private void processCommandCurrentAwayMissionMenu(String command) {
        if (command.equals("log")) {
            printAwayMissionLog();
        } else if (command.equals("new")) {
            AwayMission am = new AwayMission(starship.getAwayMissionID(), starship.getCurrentStardate());
            starship.setCurrentAwayMission(am);
            currentAwayMission();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // EFFECTS: prints mission log
    public void printAwayMissionLog() {
        if (starship.getMissionLog().isEmpty()) {
            System.out.println("\nNo previous missions.");
        } else {
            System.out.println("\nMission log:");
            for (AwayMission am: starship.getMissionLog()) {
                String s;
                if (am.getIsObjectiveComplete()) {
                    s = "complete";
                } else {
                    s = "incomplete";
                }

                System.out.println("Mission ID: " + am.getAwayMissionID());
                System.out.println("Stardate: " + starship.stardateToString(am.getStardate()));
                System.out.println("Objective " + s);
            }
        }
    }

    // EFFECTS: prints current crew
    public void printCurrentCrew() {
        System.out.println("\nCrew:");

        int i = 1;
        for (CrewMember cm: starship.getCrewMembers()) {
            System.out.println("\t" + i + ". " + cm.getLastName() + ". " + cm.getFirstName());
            i++;
        }

        System.out.println("\nTo print the stats of a crew member, enter \"stats\".");
        System.out.println("To return to the previous menu enter \"back\"");
    }

    // EFFECTS: prints stats on selected crew member
    public void printCrewMemberStats(int num) {
        CrewMember cm = starship.getCrewMembers().get(num);

        System.out.println("\n" + cm.getLastName() + ", " + cm.getFirstName());
        System.out.println("Rank: " + cm.getRank().name());
        System.out.println("Division: " + cm.getDivision().name());
        System.out.println("Health Status: " + cm.getHealthStatus().name());
    }
}
