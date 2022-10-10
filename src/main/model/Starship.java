package model;

import java.util.ArrayList;
import java.util.List;

// Represents a Starship with the name USS Intrepid, ship ID NCC-74600, and having a captain and list of crew members
public class Starship {
    private static final String SHIP_NAME = "USS Intrepid";
    private static final String SHIP_ID = "NCC-74600";

    private String firstNameOfCaptain;
    private String lastNameOfCaptain;
    private List<CrewMember> crewMembers;

    // EFFECTS: Constructs a Starship with given captain name and an empty list of crew member
    public Starship(String firstNameOfCaptain, String lastNameOfCaptain) {
        this.firstNameOfCaptain = firstNameOfCaptain;
        this.lastNameOfCaptain = lastNameOfCaptain;
        crewMembers = new ArrayList<>();
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

    //setters
    public void setFirstNameOfCaptain(String firstNameOfCaptain) {
        this.firstNameOfCaptain = firstNameOfCaptain;
    }

    public void setLastNameOfCaptain(String lastNameOfCaptain) {
        this.lastNameOfCaptain = lastNameOfCaptain;
    }

    public void setCrewMembers(List<CrewMember> newCrew) {
        this.crewMembers = newCrew;
    }

    // EFFECTS: if given crew member is currently serving on the starship, returns true
    //          otherwise, returns false
    public boolean hasCrewMember(CrewMember crewMember){
        return false;
    }

    // MODIFIES: this
    // EFFECTS: if crew member is not already on the starship, add new crewMember to list of crew members
    //          otherwise, does nothing
    public void addCrewMember(CrewMember crewMember) {
        //TODO
    }

    // MODIFIES: this
    // EFFECTS: if crew member is already on the starship, remove crewMember from list of crew members
    //          otherwise, does nothing
    public void removeCrewMember(CrewMember crewMember) {
        //TODO
    }
}
