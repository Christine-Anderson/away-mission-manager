package model;

import java.util.Random;

// Represents a crew member having a name, rank, division, and health, shirt colour, plot amour, and location status
public class CrewMember {
    private String firstName;
    private String lastName;
    private Rank rank;
    private Division division;
    private HealthStatus healthStatus;
    private boolean hasRedShirt;
    private boolean hasPlotAmour;
    private boolean onAwayMission; // when set to false, crew member is located on the starship

    // EFFECTS: Constructs a healthy crew member on the starship with a given name, rank, and division
    // plot amour and shirt colour are random
    public CrewMember(String firstName, String lastName, Rank rank, Division division) {
        Random random = new Random();
        this.firstName = firstName;
        this.lastName = lastName;
        this.rank = rank;
        this.division = division;
        this.healthStatus = HealthStatus.HEALTHY;
        this.hasRedShirt = random.nextBoolean();
        this.hasPlotAmour = random.nextBoolean();
        this.onAwayMission = false;
    }

    // getters
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }

    public Rank getRank() {
        return rank;
    }

    public Division getDivision() {
        return division;
    }

    public HealthStatus getHealthStatus() {
        return healthStatus;
    }

    public boolean isHasRedShirt() {
        return hasRedShirt;
    }

    public boolean isHasPlotAmour() {
        return hasPlotAmour;
    }

    public boolean isOnAwayMission() {
        return onAwayMission;
    }


    // setters
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public void setHealthStatus(HealthStatus healthStatus) {
        this.healthStatus = healthStatus;
    }

    public void setHasRedShirt(boolean hasRedShirt) {
        this.hasRedShirt = hasRedShirt;
    }

    public void setHasPlotAmour(boolean hasPlotAmour) {
        this.hasPlotAmour = hasPlotAmour;
    }

    public void setOnAwayMission(boolean onAwayMission) {
        this.onAwayMission = onAwayMission;
    }
}
