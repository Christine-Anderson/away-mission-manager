package model;

import java.util.ArrayList;
import java.util.List;

// Represents an away mission having an away mission ID, stardate, list of away team, active status,
// and objective status
public class AwayMission {
    private int awayMissionID;
    private int stardate;
    private boolean isActive;
    private boolean isObjectiveComplete;
    private List<CrewMember> awayTeam;


    // EFFECTS: Constructs an away mission with a unique away mission ID,
    public AwayMission() {
        this.awayMissionID = 0; //TODO how to auto construct with a unique ID?
        this.stardate = 0; //TODO set to 0 or do not set?
        this.isActive = false;
        this.isObjectiveComplete = false;
        this.awayTeam = new ArrayList<>();
    }

    //getters
    public int getAwayMissionID() {
        return awayMissionID;
    }

    public int getStardate() {
        return stardate;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isObjectiveComplete() {
        return isObjectiveComplete;
    }

    public List<CrewMember> getAwayTeam() {
        return awayTeam;
    }

    //setters
    public void setAwayMissionID(int awayMissionID) {
        this.awayMissionID = awayMissionID;
    }

    public void setStardate(int stardate) {
        this.stardate = stardate;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setObjectiveComplete(boolean objectiveComplete) {
        isObjectiveComplete = objectiveComplete;
    }

    public void setAwayTeam(List<CrewMember> awayTeam) {
        this.awayTeam = awayTeam;
    }

    // MODIFIES: this
    // EFFECTS: if crew member is not already assigned to an away mission, adds them to the away team
    //          otherwise, does nothing
    public void addCrewMemberToAwayTeam(CrewMember crewMember) { //TODO should these add and remove functions still work after the away mission has started?
        //TODO
    }

    // MODIFIES: this
    // EFFECTS: if a crew member is assigned to an away team, removes them from the away team
    //          otherwise, does nothing
    public void removeCrewMemberToAwayTeam(CrewMember crewMember) {
        //TODO
    }

    // MODIFIES: this
    // EFFECTS: if away mission is not active, starts away mission //TODO set location of awayteam to not on ship?
    //          otherwise, does nothing
    public void startAwayMission() {
        //TODO
    }

    // MODIFIES: this
    // EFFECTS: if away mission is active, end away mission with complete objective //TODO set location of awayteam to on ship? set health status
    //          otherwise, does nothing
    public void endAwayMission() {
        //TODO
    }

    // MODIFIES: this
    // EFFECTS: if away mission is active, end away mission
    //          otherwise, does nothing
    public void emergencyBeamOut() { //TODO set location of awayteam to on ship? set health status
        //TODO
    }
}
