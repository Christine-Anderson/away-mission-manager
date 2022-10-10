package model;

import java.util.ArrayList;
import java.util.List;

// Represents a Starship with the name USS Intrepid, ship ID NCC-74600, and having a captain, a list of crew members,
// a mission log, and a current away mission
public class Starship {
    private static final String SHIP_NAME = "USS Intrepid";
    private static final String SHIP_ID = "NCC-74600";

    private String firstNameOfCaptain;
    private String lastNameOfCaptain;
    private List<CrewMember> crewMembers;
    private List<AwayMission> missionLog;
    // TODO add an current mission here and test and implmement all the needed methods
    // TODO add stardate (range 41025-54868) incrementer (1-9) that pull from previous away mission (if list mepty use this empty)
    // TODO print stardate method
    // TODO add away missionID incrementer +1 that pull from previous away mission
    //TODO for testing check number is within bounds

    // EFFECTS: Constructs a Starship with given captain name, an empty list of crew members, and an empty mission log
    public Starship(String firstNameOfCaptain, String lastNameOfCaptain) {
        this.firstNameOfCaptain = firstNameOfCaptain;
        this.lastNameOfCaptain = lastNameOfCaptain;
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
        if(!this.crewMembers.contains(crewMember)) {
            this.crewMembers.add(crewMember);
        }
    }

    // MODIFIES: this
    // EFFECTS: if crew member is already on the starship, remove crewMember from list of crew members
    //          otherwise, does nothing
    public void removeCrewMember(CrewMember crewMember) {
        if(this.crewMembers.contains(crewMember)) {
            this.crewMembers.remove(crewMember);
        }
    }

    // MODIFIES: this
    // EFFECTS: adds away mission to the mission log
    public void addAwayMission(AwayMission awayMission) {
        //this.missionLog.add(awayMission); TODO finsih
    }
}
