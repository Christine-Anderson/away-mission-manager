package persistance;

import model.*;

import java.util.ArrayList;
import java.util.List;

import static model.Starship.INITIAL_AWAY_MISSION_ID;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonTest {
    public void setUpStarship(Starship testStarship) {
        // assign crew to Starship
        CrewMember cm1 = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER, Division.SCIENCES);
        cm1.setHasRedShirt(true);
        cm1.setHasPlotArmour(true);

        CrewMember cm2 = new CrewMember("Leonard", "McCoy", Rank.LIEUTENANT_COMMANDER, Division.MEDICAL);
        cm1.setHasRedShirt(false);
        cm1.setHasPlotArmour(false);

        CrewMember cm3 = new CrewMember("Montgomery", "Scott", Rank.LIEUTENANT_COMMANDER, Division.ENGINEERING);
        cm1.setHasRedShirt(true);
        cm1.setHasPlotArmour(false);

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
        am1.setAwayTeam(testAwayTeam);

        AwayMission am3 = new AwayMission(12345680, 45237);
        am3.setIsActive(true);
        am3.getAwayTeam().add(cm3);

        testStarship.getMissionLog().add(am1);
        testStarship.getMissionLog().add(am2);
        testStarship.setCurrentAwayMission(am3);
    }

    public void checkStarship() {

    }
}
