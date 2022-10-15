package model;

import java.util.ArrayList;
import java.util.List;

// Represents a Starship with the name USS Intrepid, ship ID NCC-74600, and having a captain, a list of crew members,
// a mission log, and a current away mission
public class Starship {
    protected static final String SHIP_NAME = "USS Intrepid";
    protected static final String SHIP_ID = "NCC-74600";
    protected static final int INTIAL_STARDATE = 41025;
    protected static final int INITIAL_AWAY_MISSION_ID = 14268714;


    private String firstNameOfCaptain;
    private String lastNameOfCaptain;
    private int currentStardate;
    private int awayMissionID;
    private List<CrewMember> crewMembers;
    private List<AwayMission> missionLog;
    private AwayMission currentAwayMission;

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
    public void setFirstNameOfCaptain(String firstNameOfCaptain) {
        this.firstNameOfCaptain = firstNameOfCaptain;
    }

    public void setLastNameOfCaptain(String lastNameOfCaptain) {
        this.lastNameOfCaptain = lastNameOfCaptain;
    }

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

    public void setMissionLog(List<AwayMission> missionLog) {
        this.missionLog = missionLog;
    }

    // MODIFIES: this
    // EFFECTS: if crew member is not already on the starship, add new crewMember to list of crew members
    //          otherwise, does nothing
    public void addCrewMember(CrewMember crewMember) {
        if (!this.crewMembers.contains(crewMember)) {
            this.crewMembers.add(crewMember);
        }
        //TODO implement this to instead not accept different crew member objects with the same name?
        // users can't add these so does it matter?
    }

    // MODIFIES: this
    // EFFECTS: removes crewMember from list of crew members
    public void removeCrewMember(CrewMember crewMember) {
        this.crewMembers.remove(crewMember);
    }

    // MODIFIES: this, AwayMission
    // EFFECTS: updates the current stardate and creates a current away mission with the initial stardate and away
    //          mission ID
    public void createAwayMission() {
        updateCurrentStardate();
        currentAwayMission = new AwayMission(this.awayMissionID, this.currentStardate);
        updateAwayMissionID();
    }
    //TODO make it so that it updates the current stardate and creates a unique away mission ID

    // MODIFIES: this, CrewMember
    // EFFECTS: if current away mission is not active and the away is not empty, starts away mission, and transports
    //          away team off of the starship
    //          otherwise, does nothing
    public void startAwayMission() {
        if (!this.currentAwayMission.getIsActive() && !this.currentAwayMission.getAwayTeam().isEmpty()) {
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
            this.currentAwayMission.transportAwayTeamToStarship();
            for (CrewMember cm : this.currentAwayMission.getAwayTeam()) {
                cm.updateHealthStatus();
            }
            addCurrentAwayMissionToMissionLog();
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


    // MODIFIES: this
    // EFFECTS: increments stardate
    public void updateCurrentStardate() {
        this.currentStardate = this.currentStardate + 1;
        //TODO increment stardate
        //TODO possibly add a random incrementer (range 41025-54868)?
    }

    // REQUIRES: a stardate with 5 digits
    // EFFECTS: returns current stardate as String
    public String stardateToString(int stardate) {
        String s = Integer.toString(stardate);
        return s.substring(0, 4) + "." + s.charAt(4);
    }

    // MODIFIES: this
    // EFFECTS: increments away mission ID
    public void updateAwayMissionID() {
        this.awayMissionID = this.awayMissionID + 1;
        //TODO increment ID
    }
}