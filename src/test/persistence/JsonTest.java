package persistence;

import model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    public void setUpStarship(Starship testStarship) {
        // assign crew to Starship
        CrewMember cm1 = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER, Division.SCIENCES);
        cm1.setHasRedShirt(true);
        cm1.setHasPlotArmour(true);

        CrewMember cm2 = new CrewMember("Leonard", "McCoy", Rank.LIEUTENANT_COMMANDER, Division.MEDICAL);
        cm2.setHasRedShirt(false);
        cm2.setHasPlotArmour(false);

        CrewMember cm3 = new CrewMember("Montgomery", "Scott", Rank.LIEUTENANT_COMMANDER, Division.ENGINEERING);
        cm3.setHasRedShirt(true);
        cm3.setHasPlotArmour(false);

        testStarship.addCrewMember(cm1);
        testStarship.addCrewMember(cm2);
        testStarship.addCrewMember(cm3);

        // add away missions to the mission log and create a current away mission
        List<CrewMember> testAwayTeam = new ArrayList<>();
        testAwayTeam.add(cm1);
        testAwayTeam.add(cm2);

        AwayMission am1 = new AwayMission(12345678, 45232);
        am1.setIsObjectiveComplete(true);
        am1.setAwayTeam(testAwayTeam);

        AwayMission am2 = new AwayMission(12345679, 53924);
        am2.setAwayTeam(testAwayTeam);

        AwayMission am3 = new AwayMission(12345680, 45237);
        am3.setIsActive(true);
        am3.getAwayTeam().add(cm3);

        testStarship.getMissionLog().add(am1);
        testStarship.getMissionLog().add(am2);
        testStarship.setCurrentAwayMission(am3);
    }

    public void checkStarship(String fn, String ln, int stardate, int awayMissionID, Starship starship) {
        assertEquals(fn, starship.getFirstNameOfCaptain());
        assertEquals(ln, starship.getLastNameOfCaptain());
        assertEquals(stardate, starship.getCurrentStardate());
        assertEquals(awayMissionID, starship.getAwayMissionID());
    }



    public void checkCrewMember(String fn, String ln, Rank rank, Division division, HealthStatus healthStatus,
                                boolean hasRedShirt, boolean hasPlotArmour, boolean isOnStarship, CrewMember cm) {
        assertEquals(fn, cm.getFirstName());
        assertEquals(ln, cm.getLastName());
        assertEquals(rank, cm.getRank());
        assertEquals(division, cm.getDivision());
        assertEquals(healthStatus, cm.getHealthStatus());
        assertEquals(hasRedShirt, cm.getHasRedShirt());
        assertEquals(hasPlotArmour, cm.getHasPlotArmour());
        assertEquals(isOnStarship, cm.getIsOnStarship());
    }

    public void checkAwayMission(int awayMissionID, int stardate, boolean isActive, boolean isComplete,
                                 AwayMission am) {
        assertEquals(awayMissionID, am.getAwayMissionID());
        assertEquals(stardate, am.getStardate());
        assertEquals(isActive, am.getIsActive());
        assertEquals(isComplete, am.getIsObjectiveComplete());
    }
}
