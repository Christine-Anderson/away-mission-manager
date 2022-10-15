package ui;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Away mission manager application
// This class is based on code from TellerApp ui example provided in class
public class AwayMissionManagerApp {
    private Starship starship;
    private Scanner input;

    // EFFECTS: runs the away mission manager application
    public AwayMissionManagerApp() {
        runAwayMissionManager();
    }

    // MODIFIES: this, Starship, AwayMission, CrewMember
    // EFFECTS: processes user input for main menu
    private void runAwayMissionManager() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

        starship = new Starship("Captain", "Name");
        initializeCrew();
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

    // MODIFIES: this, Starship, CrewMember
    // EFFECTS: initializes crew
    private void initializeCrew() {
        List<CrewMember> crewMembers = new ArrayList<>();
        crewMembers.add(new CrewMember("William", "Riker", Rank.COMMANDER, Division.COMMAND));
        crewMembers.add(new CrewMember("Geordi", "La Forge", Rank.LIEUTENANT_COMMANDER, Division.ENGINEERING));
        crewMembers.add(new CrewMember("Data", "", Rank.COMMANDER, Division.COMMAND));
        crewMembers.add(new CrewMember("Reginald", "Baclay", Rank.LIEUTENANT, Division.ENGINEERING));
        crewMembers.add(new CrewMember("Keiko", "O'Brien", Rank.CIVILIAN, Division.COMMAND));
        crewMembers.add(new CrewMember("Jadzia", "Dax", Rank.LIEUTENANT, Division.MEDICAL));
        crewMembers.add(new CrewMember("Julian", "Bashir", Rank.LIEUTENANT, Division.MEDICAL));
        crewMembers.add(new CrewMember("Lwaxana", "Troi", Rank.CIVILIAN, Division.OTHER));
        crewMembers.add(new CrewMember("Tribble", "", Rank.OTHER, Division.OTHER));
        crewMembers.add(new CrewMember("Morn", "", Rank.CIVILIAN, Division.OTHER));
        crewMembers.add(new CrewMember("Elim", "Garak", Rank.CIVILIAN, Division.OTHER));
        crewMembers.add(new CrewMember("Worf", "Rozhenko", Rank.LIEUTENANT_COMMANDER, Division.COMMAND));
        crewMembers.add(new CrewMember("Quark", "", Rank.CIVILIAN, Division.OTHER));
        crewMembers.add(new CrewMember("Odo", "", Rank.CIVILIAN, Division.OTHER));
        crewMembers.add(new CrewMember("B'Elanna", "Torres", Rank.LIEUTENANT, Division.ENGINEERING));
        crewMembers.add(new CrewMember("Tom", "Paris", Rank.ENSIGN, Division.COMMAND));
        crewMembers.add(new CrewMember("Harry", "Kim", Rank.ENSIGN, Division.COMMAND));
        crewMembers.add(new CrewMember("The", "Doctor", Rank.OTHER, Division.MEDICAL));
        crewMembers.add(new CrewMember("Seven of Nine", "", Rank.CIVILIAN, Division.SCIENCES));
        crewMembers.add(new CrewMember("Iceb", "", Rank.CADET, Division.SCIENCES));
        crewMembers.add(new CrewMember("Giant", "Spock", Rank.OTHER, Division.OTHER));
        starship.setCrewMembers(crewMembers);
    }

    // MODIFIES: this, Starship
    // EFFECTS: gets Captain's name from user
    private void inputCaptainName() {
        System.out.println(starship.getShipName() + " (" + starship.getShipID() + ")");
        System.out.println("--------------------------------");
        System.out.println("Enter your first and last name:");

        String fn = input.next();
        String ln = input.next();

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


    // MODIFIES: this, Starship, AwayMission, CrewMember
    // EFFECTS: processes user command for main menu
    private void processCommandMainMenu(String command) {
        if (command.equals("crew")) {
            currentCrew();
        } else if (command.equals("mission")) {
            awayMissions();
        } else {
            System.out.println("\nHighly illogical.");
        }
    }

    // EFFECTS: processes user input for current crew menu
    private void currentCrew() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            printCurrentCrew();
            currentCrewMenu();
            command = input.next();

            if (command.equals("back")) {
                keepGoing = false;
            } else {
                processCommandCurrentCrew(command);
            }
        }
    }

    // EFFECTS: displays current crew menu to user
    private void currentCrewMenu() {
        System.out.println("\nTo print the stats of a crew member, enter \"stats\" and number of crew member.");
        if (starship.getCurrentAwayMission() != null) {
            System.out.println("To add a crew member to the away team, enter \"add\" and number of crew member.");
        }
        System.out.println("To return to the previous menu enter \"back\"");
    }

    // MODIFIES: this, Starship, AwayMission, CrewMember
    // EFFECTS: processes user command for current crew menu
    private void processCommandCurrentCrew(String command) {
        if (command.equals("stats")) {
            printCrewMemberStats(input.nextInt());
        } else if (command.equals("add")) { // this menu option only available after an away mission has been created
            if (starship.getCurrentAwayMission() != null) {
                int i = input.nextInt();
                printAddCrewMember(i);
            } else {
                System.out.println("\nPlease select a valid number.");
            }
        } else {
            System.out.println("\nHighly illogical.");
        }
    }

    // MODIFIES: this, Starship, AwayMission, CrewMember
    // EFFECTS: processes user input for away missions menu
    private void awayMissions() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            awayMissionsMenu();
            command = input.next();

            if (command.equals("back")) {
                keepGoing = false;
            } else {
                processCommandAwayMissions(command);
            }
        }
    }

    // EFFECTS: displays away missions menu to user
    private void awayMissionsMenu() {
        System.out.println("\nTo view the mission log, enter \"log\".");
        System.out.println("To create an away mission, enter \"new\".");
        System.out.println("To return to the previous menu enter \"back\"");
    }

    // MODIFIES: this, Starship, AwayMission
    // EFFECTS: processes user command for away missions menu
    private void processCommandAwayMissions(String command) {
        if (command.equals("log")) {
            printAwayMissionLog();
        } else if (command.equals("new")) {
            AwayMission am = new AwayMission(starship.getAwayMissionID(), starship.getCurrentStardate());
            starship.setCurrentAwayMission(am);
            System.out.println("\nCreated new away mission " + starship.getCurrentAwayMission().getAwayMissionID()
                    + " on stardate " + starship.stardateToString(starship.getCurrentAwayMission().getStardate())
                    + ".");
            currentAwayMission();
        } else {
            System.out.println("\nHighly illogical.");
        }
    }

    // MODIFIES: this, Starship, AwayMission, CrewMember
    // EFFECTS: processes user input for current away mission menu
    private void currentAwayMission() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            currentAwayMissionMenu();
            command = input.next();

            keepGoing = processCommandCurrentAwayMission(command);
        }
    }

    // EFFECTS: displays current away mission menu to user
    private void currentAwayMissionMenu() {
        System.out.println("\nTo manage the away team, enter \"manage\".");
        System.out.println("To start the away mission, enter \"start\".");
        System.out.println("To end the away mission, enter \"end\".");
        System.out.println("To emergency beam out the away team, enter \"out\".");
    }

    // MODIFIES: this, Starship, AwayMission, CrewMember
    // EFFECTS: processes user command for current away mission menu
    private boolean processCommandCurrentAwayMission(String command) {
        boolean keepGoing = true;
        if (command.equals("manage")) {
            awayTeam();
        } else if (command.equals("start")) {
            printStartAwayMission();
            starship.startAwayMission();
        } else if (command.equals("end")) {
            printEndAwayMission();
            if (starship.getCurrentAwayMission().getIsActive()) {
                keepGoing = false;
            }
            starship.endAwayMission();
        } else if (command.equals("out")) {
            printEmergencyBeamOut();
            if (starship.getCurrentAwayMission().getIsActive()) {
                keepGoing = false;
            }
            starship.emergencyBeamOut();
        } else {
            System.out.println("\nHighly illogical.");
        }
        return keepGoing;
    }

    // MODIFIES: this, Starship, AwayMission, CrewMember
    // EFFECTS: processes user input for away team menu
    private void awayTeam() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            awayTeamMenu();
            command = input.next();

            if (command.equals("back")) {
                keepGoing = false;
            } else {
                processCommandAwayTeam(command);
            }
        }
    }

    // EFFECTS: displays away team menu to user
    private void awayTeamMenu() {
        System.out.println("\nTo view the current crew and add crew members to the away team, enter \"crew\".");
        System.out.println("To view the away team and remove crew members from the away team, enter \"team\".");
        System.out.println("To return to the previous menu enter \"back\"");
    }

    // MODIFIES: this, Starship, AwayMission, CrewMember
    // EFFECTS: processes user command for away team menu
    private void processCommandAwayTeam(String command) {
        if (command.equals("crew")) {
            currentCrew();
        } else if (command.equals("team")) {
            currentAwayTeam();
        } else {
            System.out.println("\nHighly illogical.");
        }
    }

    // MODIFIES: this, Starship, AwayMission, CrewMember
    // EFFECTS: processes user input for current away team menu
    private void currentAwayTeam() {
        boolean keepGoing = true;
        String command;

        while (keepGoing) {
            printAwayTeam();
            currentAwayTeamMenu();
            command = input.next();

            if (command.equals("back")) {
                keepGoing = false;
            } else {
                processCommandCurrentAwayTeam(command);
            }
        }
    }

    // EFFECTS: displays away team menu to user
    private void currentAwayTeamMenu() {
        System.out.println("\nTo remove a crew member from the away team, enter \"remove\" and the number of the crew "
                + "member.");
        System.out.println("To return to the previous menu enter \"back\"");
    }

    // MODIFIES: this, Starship, AwayMission, CrewMember
    // EFFECTS: processes user command for away team menu
    private void processCommandCurrentAwayTeam(String command) {
        if (command.equals("remove")) {
            int i = input.nextInt();
            if (!starship.getCurrentAwayMission().getAwayTeam().isEmpty() && i >= 1
                    && i <= starship.getCurrentAwayMission().getAwayTeam().size()) {
                CrewMember cm = starship.getCurrentAwayMission().getAwayTeam().get(i - 1);
                starship.getCurrentAwayMission().removeCrewMemberFromAwayTeam(cm);
                if (starship.getCurrentAwayMission().getIsActive()) {
                    if (cm.getLastName() == "") {
                        System.out.println("\n" + cm.getFirstName() + " has been transported back to the starship.");
                    } else {
                        System.out.println("\n" + cm.getFirstName() + " " + cm.getLastName()
                                + " has been transported back to the starship.");
                    }
                } else {
                    if (cm.getLastName() == "") {
                        System.out.println("\n" + cm.getFirstName() + " has been removed from the away team.");
                    } else {
                        System.out.println("\n" + cm.getFirstName() + " " + cm.getLastName()
                                + " has been removed from the away team.");
                    }
                }
            } else {
                System.out.println("\nPlease select a valid number.");
            }
        } else {
            System.out.println("\nHighly illogical.");
        }
    }

    // EFFECTS: prints mission log
    public void printAwayMissionLog() {
        if (starship.getMissionLog().isEmpty()) {
            System.out.println("\nNo previous missions.");
        } else {
            System.out.println("\nMission log:");
            for (AwayMission am : starship.getMissionLog()) {
                String s;
                if (am.getIsObjectiveComplete()) {
                    s = "complete";
                } else {
                    s = "incomplete";
                }

                System.out.println("\nMission ID: " + am.getAwayMissionID());
                System.out.println("Stardate: " + starship.stardateToString(am.getStardate()));
                System.out.println("Objective " + s);
            }
        }
    }

    // EFFECTS: prints current crew
    public void printCurrentCrew() {
        System.out.println("\nCrew:");

        int i = 1;
        for (CrewMember cm : starship.getCrewMembers()) {
            if (cm.getLastName() == "") {
                System.out.println("\t" + i + ". " + cm.getFirstName());
                i++;
            } else {
                System.out.println("\t" + i + ". " + cm.getLastName() + ", " + cm.getFirstName());
                i++;
            }
        }
    }

    // EFFECTS: prints stats on given crew member
    public void printCrewMemberStats(int i) {
        if (i >= 1 && i <= starship.getCrewMembers().size()) {
            CrewMember cm = starship.getCrewMembers().get(i - 1);

            if (cm.getLastName() == "") {
                System.out.println("\n" + cm.getFirstName());
            } else {
                System.out.println("\n" + cm.getLastName() + ", " + cm.getFirstName());
            }
            System.out.println("Rank: " + cm.getRank().name());
            System.out.println("Division: " + cm.getDivision().name());
            System.out.println("Health Status: " + cm.getHealthStatus().name());
        } else {
            System.out.println("\nPlease select a valid number.");
        }
    }

    // EFFECTS: prints away team
    public void printAwayTeam() {
        if (starship.getCurrentAwayMission().getAwayTeam().isEmpty()) {
            System.out.println("\nNo one assigned to the away team.");
        } else {
            System.out.println("\nAway Team:");

            int i = 1;
            for (CrewMember cm : starship.getCurrentAwayMission().getAwayTeam()) {
                if (cm.getLastName() == "") {
                    System.out.println("\t" + i + ". " + cm.getFirstName());
                    i++;
                } else {
                    System.out.println("\t" + i + ". " + cm.getLastName() + ", " + cm.getFirstName());
                    i++;
                }
            }
        }
    }

    // EFFECTS: prints for start of mission
    public void printStartAwayMission() {
        if (!starship.getCurrentAwayMission().getIsActive()
                && !starship.getCurrentAwayMission().getAwayTeam().isEmpty()) {
            System.out.println("\nMake it so.");
        } else if (starship.getCurrentAwayMission().getIsActive()) {
            System.out.println("\nAway Mission has already started.");
        } else {
            System.out.println("\nPlease select an away team.");
        }
    }

    // EFFECTS: prints for end of mission
    public void printEndAwayMission() {
        if (starship.getCurrentAwayMission().getIsActive()) {
            System.out.println("\nGood job number one.");
        } else {
            System.out.println("\nAway Mission has not started.");
        }
    }

    // EFFECTS: prints for emergency beam out
    public void printEmergencyBeamOut() {
        if (starship.getCurrentAwayMission().getIsActive()) {
            System.out.println("\nBeam me up Scottie!");
        } else {
            System.out.println("\nAway Mission has not started.");
        }
    }

    // EFFECTS: adds a crew member and prints the results
    public void printAddCrewMember(int i) {
        if (i >= 1 && i <= starship.getCrewMembers().size()) {
            CrewMember cm = starship.getCrewMembers().get(i - 1);
            if (cm.getHealthStatus() == HealthStatus.DEAD) {
                System.out.println("\nHe's dead Jim.");
            } else {
                starship.getCurrentAwayMission().addCrewMemberToAwayTeam(cm);
                if (starship.getCurrentAwayMission().getIsActive()) {
                    if (cm.getLastName() == "") {
                        System.out.println("\n" + cm.getFirstName() + " has been transported off of the starship.");
                    } else {
                        System.out.println("\n" + cm.getFirstName() + " " + cm.getLastName()
                                + " has been transported off of the starship.");
                    }
                } else {
                    if (cm.getLastName() == "") {
                        System.out.println("\n" + cm.getFirstName() + " has been added to the away team.");
                    } else {
                        System.out.println("\n" + cm.getFirstName() + " " + cm.getLastName()
                                + " has been added to the away team.");
                    }
                }
            }
        }
    }
}
