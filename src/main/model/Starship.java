package model;

import java.util.ArrayList;
import java.util.List;

// Represents a Starship with the name USS Intrepid, ship ID NCC-74600, and having a captain, a list of crew members,
// a mission log, and a current away mission //TODO update
public class Starship {
    protected static final String SHIP_NAME = "USS Intrepid";
    protected static final String SHIP_ID = "NCC-74600";
    protected static final int INTIAL_STARDATE = 41025;
    protected static final int FINAL_STARDATE = 54868;
    protected static final int INITIAL_AWAY_MISSION_ID = 14268714;


    private String firstNameOfCaptain;
    private String lastNameOfCaptain;
    private int currentStardate;
    private int awayMissionID;
    private AwayMission currentAwayMission;
    private List<CrewMember> crewMembers;
    private List<AwayMission> missionLog;

    // EFFECTS: Constructs the Starship USS Intrepid (NCC-74600) on stardate 41025 with given captain name, an initial
    // away mission ID of 142687, an empty list of crew members, and an empty mission log
    public Starship(String firstNameOfCaptain, String lastNameOfCaptain) {
        this.firstNameOfCaptain = firstNameOfCaptain;
        this.lastNameOfCaptain = lastNameOfCaptain;
        this.currentStardate = INTIAL_STARDATE;
        this.awayMissionID = INITIAL_AWAY_MISSION_ID;
        this.currentAwayMission = null;
        crewMembers = new ArrayList<>();
        missionLog = new ArrayList<>();
    }

    //getters
    public String getShipName() {
        return SHIP_NAME;
    }

    public String getShipID() {
        return SHIP_ID;
    }

    public String getFirstNameOfCaptain() {
        return firstNameOfCaptain;
    }

    public String getLastNameOfCaptain() {
        return lastNameOfCaptain;
    }

    public int getCurrentStardate() {
        return currentStardate;
    }

    public int getAwayMissionID() {
        return awayMissionID;
    }

    public AwayMission getCurrentAwayMission() {
        return currentAwayMission;
    }

    public List<CrewMember> getCrewMembers() {
        return this.crewMembers;
    }

    public List<AwayMission> getMissionLog() {
        return missionLog;
    }

    //setters
    public void setFirstNameOfCaptain(String firstNameOfCaptain) { //TODO do these need tests, i don't even need them...
        this.firstNameOfCaptain = firstNameOfCaptain;
    }

    public void setLastNameOfCaptain(String lastNameOfCaptain) {
        this.lastNameOfCaptain = lastNameOfCaptain;
    } //TODO get rid of extra setters?

    public void setCurrentStardate(int currentStardate) {
        this.currentStardate = currentStardate;
    }

    public void setAwayMissionID(int awayMissionID) {
        this.awayMissionID = awayMissionID;
    }

    public void setCurrentAwayMission(AwayMission currentAwayMission) {
        this.currentAwayMission = currentAwayMission;
    }

    public void setCrewMembers(List<CrewMember> newCrew) {
        this.crewMembers = newCrew;
    }

    public void setMissionLog(List<AwayMission> missionLog) { //TODO shouldn't need this one either...
        this.missionLog = missionLog;
    }

    // MODIFIES: this
    // EFFECTS: if crew member is not already on the starship, add new crewMember to list of crew members
    //          otherwise, does nothing
    public void addCrewMember(CrewMember crewMember) {
        if (!this.crewMembers.contains(crewMember)) {
            this.crewMembers.add(crewMember);
        }
    }

    // MODIFIES: this
    // EFFECTS: if crew member is already on the starship, remove crewMember from list of crew members
    //          otherwise, does nothing
    public void removeCrewMember(CrewMember crewMember) {
        if (this.crewMembers.contains(crewMember)) {
            this.crewMembers.remove(crewMember);
        }
    }

    // MODIFIES: this, AwayMission
    // EFFECTS: updates the current stardate and creates a current away mission with the current stardate and a unique
    //          away mission ID
    public void createAwayMission() {
        updateCurrentStardate();
        currentAwayMission = new AwayMission(this.awayMissionID, this.currentStardate);
        updateAwayMissionID();
    }

    // MODIFIES: this, CrewMember
    // EFFECTS: if current away mission is not active, starts away mission, and transports away team off of the starship
    //          otherwise, does nothing
    public void startAwayMission() {
        if (! this.currentAwayMission.getIsActive()) {
            this.currentAwayMission.setIsActive(true);
            this.currentAwayMission.transportAwayTeamOffOfStarship();
        }
    }

    // MODIFIES: this, Starship, CrewMember
    // EFFECTS: if current away mission is active, ends away mission, adds it to the mission log, removes it as the
    //          current mission, updates the health status of all members of the away team, and transports them back to
    //          the starship
    //          otherwise, does nothing
    public void emergencyBeamOut() {
        if (this.currentAwayMission.getIsActive()) {
            this.currentAwayMission.setIsActive(false);
            addCurrentAwayMissionToMissionLog();
            for (CrewMember cm: this.currentAwayMission.getAwayTeam()) {
                cm.updateHealthStatus();
                cm.setIsOnStarship(true);
            }
            this.currentAwayMission = null;
        }
    }

    // MODIFIES: this, Starship, CrewMember
    // EFFECTS: if current away mission is active, end away mission, complete objective, adds it to the mission log,
    //          removes it as the current mission, updates the health status of all members of the away team, and
    //          transports them back to the starship
    //          otherwise, does nothing
    public void endAwayMission() {
        if (this.currentAwayMission.getIsActive()) {
            this.currentAwayMission.setIsObjectiveComplete(true);
            emergencyBeamOut();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds current away mission to the mission log
    public void addCurrentAwayMissionToMissionLog() {
        this.missionLog.add(this.currentAwayMission);
    }

    // EFFECTS: adds away mission to the mission log
    public void printAwayMissionLog() {
        //TODO do with ui to test properly
    }

    // MODIFIES: this
    // EFFECTS: increments stardate
    public void updateCurrentStardate() {
        this.currentStardate = this.currentStardate + 1;
    }
    //TODO possibly add a random incrementer (range 41025-54868)?

    // REQUIRES: a stardate with 5 digits
    // EFFECTS: returns current stardate as String
    public String printStardate() {
        String s = Integer.toString(this.currentStardate);
        return s.substring(0,4) + "." + s.charAt(4);
    }

    // MODIFIES: this
    // EFFECTS: increments away mission ID
    public void updateAwayMissionID() {
        this.awayMissionID = this.awayMissionID + 1;
    }
}
