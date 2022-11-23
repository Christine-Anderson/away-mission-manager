package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Starship with the name USS Intrepid, ship ID NCC-74600, and having a captain, a list of crew members,
 * a mission log, and a current away mission
 */
public class Starship implements Writable {
    public static final String SHIP_NAME = "USS Intrepid";
    public static final String SHIP_ID = "NCC-74600";
    public static final int INITIAL_STARDATE = 41025;
    public static final int INITIAL_AWAY_MISSION_ID = 14268714;

    private String firstNameOfCaptain;
    private String lastNameOfCaptain;
    private int currentStardate;
    private int nextAwayMissionID;
    private List<CrewMember> crewMembers;
    private List<AwayMission> missionLog;
    private AwayMission currentAwayMission;

    // EFFECTS: Constructs the Starship USS Intrepid (NCC-74600) on stardate 41025 with given captain name, an initial
    //          away mission ID of 142687, an empty list of crew members, and an empty mission log
    public Starship(String firstNameOfCaptain, String lastNameOfCaptain) {
        this.firstNameOfCaptain = firstNameOfCaptain;
        this.lastNameOfCaptain = lastNameOfCaptain;
        this.currentStardate = INITIAL_STARDATE;
        this.nextAwayMissionID = INITIAL_AWAY_MISSION_ID;
        this.currentAwayMission = null;
        crewMembers = new ArrayList<>();
        missionLog = new ArrayList<>();
    }

    // EFFECTS: Constructs the Starship USS Intrepid (NCC-74600) with given captain name, stardate, and away mission ID,
    //          an empty list of crew members, and an empty mission log
    public Starship(String firstNameOfCaptain, String lastNameOfCaptain, int currentStardate, int nextAwayMissionID) {
        this(firstNameOfCaptain, lastNameOfCaptain);
        this.currentStardate = currentStardate;
        this.nextAwayMissionID = nextAwayMissionID;
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

    public int getNextAwayMissionID() {
        return nextAwayMissionID;
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

    public void setNextAwayMissionID(int nextAwayMissionID) {
        this.nextAwayMissionID = nextAwayMissionID;
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
    // EFFECTS: if the crew member is not already on the starship, add new crew member to list of crew members
    //          otherwise, does nothing
    public void addCrewMember(CrewMember crewMember) {
        if (!isAlreadyOnBoard(crewMember)) {
            this.crewMembers.add(crewMember);
        }
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
        currentAwayMission = new AwayMission(this.nextAwayMissionID, this.currentStardate);
        EventLog.getInstance().logEvent(new Event("Created new Away Mission "
                + this.getCurrentAwayMission().getAwayMissionID() + " on stardate "
                + this.stardateToString(this.getCurrentAwayMission().getStardate()) + "."));
        updateAwayMissionID();
    }

    // MODIFIES: this, CrewMember
    // EFFECTS: if current away mission is not active and the away is not empty, starts away mission, and transports
    //          away team off of the starship
    //          otherwise, does nothing
    public void startAwayMission() {
        if (!this.currentAwayMission.getIsActive() && !this.currentAwayMission.getAwayTeam().isEmpty()) {
            this.currentAwayMission.setIsActive(true);
            EventLog.getInstance().logEvent(new Event("Mission "
                    + this.getCurrentAwayMission().getAwayMissionID() + " started."));
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
            EventLog.getInstance().logEvent(new Event("Mission "
                    + this.getCurrentAwayMission().getAwayMissionID() + " ended."));
            this.currentAwayMission.transportAwayTeamToStarship();
            for (CrewMember cm : this.currentAwayMission.getAwayTeam()) {
                cm.updateHealthStatus();
                EventLog.getInstance().logEvent(new Event(cm.nameToString(true)
                        + " returned " + cm.getHealthStatus().name().toLowerCase() + "."));
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
            EventLog.getInstance().logEvent(new Event("Mission "
                    + this.getCurrentAwayMission().getAwayMissionID() + " objective complete."));
            emergencyBeamOut();
        }
    }

    // MODIFIES: this
    // EFFECTS: adds current away mission to the mission log
    public void addCurrentAwayMissionToMissionLog() {
        this.missionLog.add(this.currentAwayMission);
        EventLog.getInstance().logEvent(new Event("Mission " + this.getCurrentAwayMission().getAwayMissionID()
                + " added to the Away Mission Log."));
    }

    // EFFECTS: returns true if a crew member of the same name is already on board
    //          otherwise, returns false
    public boolean isAlreadyOnBoard(CrewMember crewMember) {
        boolean isOnBoard = false;

        for (CrewMember cm : crewMembers) {
            if (cm.getLastName().equals(crewMember.getLastName())
                    && cm.getFirstName().equals(crewMember.getFirstName())) {
                isOnBoard = true;
                break;
            }
        }

        return isOnBoard;
    }


    // MODIFIES: this
    // EFFECTS: increments stardate
    public void updateCurrentStardate() {
        this.currentStardate = this.currentStardate + 1;
        //TODO possibly add a random incrementer (range 41025-54868)?
    }

    // MODIFIES: this
    // EFFECTS: increments away mission ID
    public void updateAwayMissionID() {
        this.nextAwayMissionID = this.nextAwayMissionID + 1;
    }

    // REQUIRES: a stardate with 5 digits
    // EFFECTS: returns current stardate as String
    public String stardateToString(int stardate) {
        String s = Integer.toString(stardate);
        return s.substring(0, 4) + "." + s.charAt(4);
    }

    // EFFECTS: writes starship to a JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("firstNameOfCaptain", this.firstNameOfCaptain);
        json.put("lastNameOfCaptain", this.lastNameOfCaptain);
        json.put("currentStardate", Integer.toString(this.currentStardate));
        json.put("awayMissionID", Integer.toString(this.nextAwayMissionID));
        json.put("crewMembers", crewMembersToJson());
        json.put("missionLog", missionLogToJson());
        if (this.currentAwayMission == null) {
            json.put("currentAwayMission", JSONObject.NULL);
        } else {
            json.put("currentAwayMission", this.currentAwayMission.toJson());
        }

        return json;
    }

    // EFFECTS: returns crew members on the starship as a JSON array
    private JSONArray crewMembersToJson() {
        JSONArray jsonArray = new JSONArray();

        for (CrewMember cm : crewMembers) {
            jsonArray.put(cm.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: returns mission log as a JSON array
    private JSONArray missionLogToJson() {
        JSONArray jsonArray = new JSONArray();

        for (AwayMission awayMission : missionLog) {
            jsonArray.put(awayMission.toJson());
        }

        return jsonArray;
    }
}
