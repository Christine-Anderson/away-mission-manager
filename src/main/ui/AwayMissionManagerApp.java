package ui;

import model.AwayMission;
import model.CrewMember;
import model.Starship;

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

    // MODIFIES: this
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
        System.out.println("Live long and prosper.");
    }

    // MODIFIES: this, Starship
    // EFFECTS: initializes starship and crew
    private void initializeStarship() {
        starship = new Starship("Captain", "Name");
        //TODO add crew
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this, Starship
    // EFFECTS: gets Captain's name from user
    private void inputCaptainName() {
        String fn = null;
        String ln = null;

        System.out.println(starship.getShipName() + " (" + starship.getShipID() + ")" + "\n");
        System.out.println("Enter your first and last name:");

        fn = input.next();
        ln = input.next();

        starship.setFirstNameOfCaptain(fn);
        starship.setLastNameOfCaptain(ln);
        System.out.println("Welcome Captain " + starship.getLastNameOfCaptain());

    }

    // EFFECTS: displays main menu of options to user
    private void mainDisplayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tcrew -> current crew");
        System.out.println("\tmission -> away mission manager");
        System.out.println("\tTo quit at any time enter \"quit\"");
    }


    // EFFECTS: processes user command for main menu
    private void processCommandMainMenu(String command) {
        if (command.equals("crew")) {
            currentCrew();
        } else if (command.equals("mission")) {
            awayMissionManager();
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
                System.out.println("Enter the number of crew member you would like stats on:");
                num = input.nextInt();
                printCrewMemberStats(num);
            } else {
                System.out.println("Selection not valid...");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: conducts a withdraw transaction TODO
    private void awayMissionManager() {

    }


    // EFFECTS: prints mission log
    public void printAwayMissionLog() {
        System.out.println("Mission log:");
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

    // EFFECTS: prints current crew
    public void printCurrentCrew() {
        System.out.println("Crew:");

        int i = 1;
        for (CrewMember cm: starship.getCrewMembers()) {
            System.out.println(i + ". " + cm.getLastName() + ". " + cm.getFirstName());
            i++;
        }

        System.out.println("\tTo print the stats of a crew member, enter \"stats\".");
        System.out.println("\tTo return to the previous menu enter \"back\"");
    }

    // EFFECTS: prints stats on selected crew member
    public void printCrewMemberStats(int num) {
        CrewMember cm = starship.getCrewMembers().get(num);

        System.out.println(cm.getLastName() + ". " + cm.getFirstName());
        System.out.println("Rank: " + cm.getRank().name());
        System.out.println("Division: " + cm.getDivision().name());
        System.out.println("Health Status: " + cm.getHealthStatus().name());
    }
}
