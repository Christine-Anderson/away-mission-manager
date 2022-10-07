package model;

import java.util.Random;

// Represents a crew member having a name, rank, division, and health, shirt colour, plot amour, and location status
public class CrewMember {
    private String name;
    private Rank rank;
    private Division division;
    private HealthStatus healthStatus;
    private boolean hasRedShirt;
    private boolean hasPlotAmour;
    private boolean onAwayMission; // when set to false, crew member is located on the starship

    // EFFECTS: Constructs a healthy crew member on the starship with a given name, rank, and division
    // plot amour and shirt colour are random
    public CrewMember(String name, Rank rank, Division division) {
        Random random = new Random();
        this.name = name;
        this.rank = rank;
        this.division = division;
        this.healthStatus = HealthStatus.HEALTHY;
        this.hasRedShirt = random.nextBoolean();
        this.hasPlotAmour = random.nextBoolean();
        this.onAwayMission = false;
    }

    // getters
    public String getName() {
        return name;
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
    public void setName(String name) {
        this.name = name;
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
