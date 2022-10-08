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

    // MODIFIES: this
    // EFFECTS: add new crewMember to list of crew members
    public void addCrewMember(CrewMember newRecruit) {
        //TODO
    }

    // MODIFIES: this
    // EFFECTS: remove crewMember from list of crew members
    public void removeCrewMember(CrewMember crewMember) {
        //TODO
    }

    //TODO get list of current crew members

    //TODO find crewmember
}
