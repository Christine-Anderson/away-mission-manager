package model;

import java.util.ArrayList;
import java.util.List;

// Represents an away mission having an away mission ID, stardate, list of away team, active status,
// and objective status //TODO maek sure this is up to date
public class AwayMission {
    private int awayMissionID;
    private int stardate; // 5 digit date
    private boolean isActive;
    private boolean isObjectiveComplete;
    private List<CrewMember> awayTeam;


    // EFFECTS: Constructs an inactive, not complete away mission with a given away mission ID, given stardate, and
    // empty away team
    public AwayMission(int awayMissionID, int stardate) {
        this.awayMissionID = awayMissionID;
        this.stardate = stardate;
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
    public void setAwayMissionID(int awayMissionID) { //TODO get rid of extra setters?
        this.awayMissionID = awayMissionID;
    }

    public void setStardate(int stardate) {
        this.stardate = stardate;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setObjectiveComplete(boolean isObjectiveComplete) {
        this.isObjectiveComplete = isObjectiveComplete;
    }

    public void setAwayTeam(List<CrewMember> awayTeam) {
        this.awayTeam = awayTeam;
    }

    // MODIFIES: this, CrewMember
    // EFFECTS: if crew member is not already assigned to the current away team, adds them to the away team
    //          if the current away mission is active, transports them off of the starship
    //          otherwise, does nothing
    public void addCrewMemberToAwayTeam(CrewMember crewMember) {
        if(!this.awayTeam.contains(crewMember)) {
            this.awayTeam.add(crewMember);
            if(this.isActive) {
                crewMember.setOnStarship(false);
            }
        }
    }

    // MODIFIES: this, CrewMember
    // EFFECTS: if crew member is assigned to the current away team, removes them from the away team
    //          if current away mission is active, updates their health status, and transports them back to the starship
    //          otherwise, does nothing
    public void removeCrewMemberFromAwayTeam(CrewMember crewMember) {
        if(this.awayTeam.contains(crewMember)) {
            this.awayTeam.remove(crewMember);
            if(this.isActive) {
                crewMember.updateHealthStatus();
                crewMember.setOnStarship(true);
            }
        }
    }

    // MODIFIES: this, CrewMember
    // EFFECTS: if current away mission is not active, starts away mission, and transports away team off of the starship
    //          otherwise, does nothing
    public void startAwayMission() {
        if(! this.isActive) {
            this.setIsActive(true);
            transportAwayTeamOffOfStarship();
        }
    }

    // MODIFIES: this, Starship, CrewMember
    // EFFECTS: if current away mission is active, end away mission, complete objective, add it to the mission log,
    //          updates the health status of all members of the away team, and transports them back to the starship
    //          otherwise, does nothing
    public void endAwayMission() {
        //TODO
    }

    // MODIFIES: this TODO
    // EFFECTS: if current away mission is active, end away mission, add it to the mission log, updates the health
    //          status of all members of the away team, and transports them back to the starship
    //          otherwise, does nothing
    public void emergencyBeamOut() {
        //TODO
    }

    // MODIFIES: TODO this?, CrewMember
    // EFFECTS: sets the location of all members of the away team to the starship
    public void transportAwayTeamToStarship(){
        for(CrewMember cm: awayTeam) {
            cm.setOnStarship(true);
        }
    }

    // MODIFIES: TODO this?, CrewMember
    // EFFECTS: sets the location of all members of the away team off of the starship
    public void transportAwayTeamOffOfStarship(){
        for(CrewMember cm: awayTeam) {
            cm.setOnStarship(false);
        }
    }
}
