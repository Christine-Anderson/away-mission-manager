package model;

import java.util.Random;

// Represents a crew member having a name, rank, division, health status, shirt colour, plot armour, and location
public class CrewMember {
    private String firstName;
    private String lastName;
    private Rank rank;
    private Division division;
    private HealthStatus healthStatus;
    private boolean hasRedShirt;
    private boolean hasPlotArmour;
    private boolean isOnStarship;
    private Random random;

    // EFFECTS: Constructs a healthy crew member on the starship with a given name, rank, and division
    // plot amour and shirt colour are random
    public CrewMember(String firstName, String lastName, Rank rank, Division division) {
        this.random = new Random();
        this.firstName = firstName;
        this.lastName = lastName;
        this.rank = rank;
        this.division = division;
        this.healthStatus = HealthStatus.HEALTHY;
        this.hasRedShirt = random.nextBoolean();
        this.hasPlotArmour = random.nextBoolean();
        this.isOnStarship = true;
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

    public boolean getHasRedShirt() {
        return hasRedShirt;
    }

    public boolean getHasPlotArmour() {
        return hasPlotArmour;
    }

    public boolean getIsOnStarship() {
        return isOnStarship;
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

    public void setHasPlotArmour(boolean hasPlotArmour) {
        this.hasPlotArmour = hasPlotArmour;
    }

    public void setIsOnStarship(boolean onStarship) {
        this.isOnStarship = onStarship;
    }

    // MODIFIES: this
    // EFFECTS: randomly updates health status of crew member
    //          if they have plot armour, health status is either healthy or injured
    //          if they have a red shirt and no plot armour, health status is either healthy or dead
    //          if they don't have a red shirt or plot armour, health status may be any of the possible states
    public void updateHealthStatus(){
        if(this.hasPlotArmour) {
            if (random.nextBoolean()) {
                this.healthStatus = HealthStatus.INJURED;
            }
        } else if(this.hasRedShirt) {
            if (random.nextBoolean()) {
                this.healthStatus = HealthStatus.DEAD;
            }
        } else {
            int x = random.nextInt(3);
            if(x == 0){
                this.healthStatus = HealthStatus.INJURED;
            } else if(x == 1) {
                this.healthStatus = HealthStatus.DEAD;
            }
        }
    }
}
