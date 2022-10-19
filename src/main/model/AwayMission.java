package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

// Represents an away mission having an away mission ID, stardate, active status, objective status, and away team
public class AwayMission {
    private int awayMissionID;
    private int stardate; // 5 digit date
    private boolean isActive;
    private boolean isObjectiveComplete;
    private List<CrewMember> awayTeam;


    // EFFECTS: Constructs an inactive, incomplete away mission with a given away mission ID, given stardate, and an
    //          empty away team
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

    public boolean getIsActive() {
        return isActive;
    }

    public boolean getIsObjectiveComplete() {
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

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setIsObjectiveComplete(boolean isObjectiveComplete) {
        this.isObjectiveComplete = isObjectiveComplete;
    }

    public void setAwayTeam(List<CrewMember> awayTeam) {
        this.awayTeam = awayTeam;
    }

    // MODIFIES: this, CrewMember
    // EFFECTS: if crew member is not already assigned to the current away or not dead, adds them to the away team
    //          if the current away mission is active, transports them off of the starship
    //          otherwise, does nothing
    public void addCrewMemberToAwayTeam(CrewMember crewMember) {
        if (!this.awayTeam.contains(crewMember) && crewMember.getHealthStatus() != HealthStatus.DEAD) {
            this.awayTeam.add(crewMember);
            if (this.isActive) {
                crewMember.setIsOnStarship(false);
            }
        }
    }

    // MODIFIES: this, CrewMember
    // EFFECTS: if crew member is assigned to the current away team, removes them from the away team
    //          if current away mission is active, updates their health status, and transports them back to the starship
    //          otherwise, does nothing
    public void removeCrewMemberFromAwayTeam(CrewMember crewMember) {
        if (this.awayTeam.contains(crewMember)) {
            this.awayTeam.remove(crewMember);
            if (this.isActive) {
                crewMember.updateHealthStatus();
                crewMember.setIsOnStarship(true);
            }
        }
    }

    // MODIFIES: this, CrewMember
    // EFFECTS: sets the location of all members of the away team to the starship
    public void transportAwayTeamToStarship() {
        for (CrewMember cm : awayTeam) {
            cm.setIsOnStarship(true);
        }
    }

    // MODIFIES: this, CrewMember
    // EFFECTS: sets the location of all members of the away team off of the starship
    public void transportAwayTeamOffOfStarship() {
        for (CrewMember cm : awayTeam) {
            cm.setIsOnStarship(false);
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("awayMissionID", this.awayMissionID);
        json.put("isActive", this.isActive);
        json.put("isObjectiveComplete", this.isObjectiveComplete);
        json.put("awayTeam", awayTeamToJson());
        return json;
    }

    // EFFECTS: returns crew members on the away team as a JSON array
    private JSONArray awayTeamToJson() {
        JSONArray jsonArray = new JSONArray();

        for (CrewMember cm: awayTeam) {
            jsonArray.put(cm.toJson());
        }

        return jsonArray;
    }
}
