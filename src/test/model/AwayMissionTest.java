package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AwayMissionTest {
    private AwayMission testAwayMission;
    private CrewMember cm1;
    private CrewMember cm2;
    private CrewMember cm3;
    private List<CrewMember> testAwayTeam;

    @BeforeEach
    void setUp() {
        testAwayMission = new AwayMission(12345678, 41025);
        cm1 = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER, Division.SCIENCES);
        cm2 = new CrewMember("Leonard", "McCoy", Rank.LIEUTENANT_COMMANDER, Division.MEDICAL);
        cm3 = new CrewMember("Montgomery", "Scott", Rank.LIEUTENANT_COMMANDER, Division.ENGINEERING);
        testAwayTeam = new ArrayList<>();
        testAwayTeam.add(cm1);
        testAwayTeam.add(cm2);
        testAwayTeam.add(cm3);
    }

    @Test
    void testAwayMissionConstructor() {
        assertEquals(12345678, testAwayMission.getAwayMissionID());
        assertEquals(41025, testAwayMission.getStardate());
        assertFalse(testAwayMission.getIsActive());
        assertFalse(testAwayMission.getIsObjectiveComplete());
        assertTrue(testAwayMission.getAwayTeam().isEmpty());
    }

    @Test
    void testGettersAndSetters() {
        testAwayMission.setAwayMissionID(12345678);
        testAwayMission.setStardate(12345);
        testAwayMission.setIsActive(true);
        testAwayMission.setIsObjectiveComplete(true);
        testAwayMission.setAwayTeam(testAwayTeam);

        assertEquals(12345678, testAwayMission.getAwayMissionID());
        assertEquals(12345, testAwayMission.getStardate());
        assertTrue(testAwayMission.getIsActive());
        assertTrue(testAwayMission.getIsObjectiveComplete());
        assertEquals(testAwayTeam, testAwayMission.getAwayTeam());
    }

    @Test
    void testAddCrewMemberToAwayTeamInactiveMission() {
        testAwayMission.addCrewMemberToAwayTeam(cm1);

        assertEquals(cm1, testAwayMission.getAwayTeam().get(0));
        assertEquals(1, testAwayMission.getAwayTeam().size());
    }

    @Test
    void testAddCrewMemberToAwayTeamActiveMission() {
        testAwayMission.setIsActive(true);
        assertTrue(cm1.getIsOnStarship());

        testAwayMission.addCrewMemberToAwayTeam(cm1);

        assertEquals(cm1, testAwayMission.getAwayTeam().get(0));
        assertEquals(1, testAwayMission.getAwayTeam().size());
        assertFalse(cm1.getIsOnStarship());
    }

    @Test
    void testAddCrewMemberToAwayTeamAlreadyOnAwayTeam() {
        testAwayMission.setAwayTeam(testAwayTeam);
        assertTrue(testAwayMission.getAwayTeam().contains(cm1));

        testAwayMission.addCrewMemberToAwayTeam(cm1);

        assertEquals(testAwayTeam, testAwayMission.getAwayTeam());
    }

    @Test
    void testAddCrewMemberToAwayTeamDead() {
        cm1.setHealthStatus(HealthStatus.DEAD);
        assertTrue(testAwayMission.getAwayTeam().isEmpty());

        testAwayMission.addCrewMemberToAwayTeam(cm1);

        assertFalse(testAwayMission.getAwayTeam().contains(cm1));
    }

    @Test
    void testAddCrewMemberToAwayTeamMultipleTimes() {
        testAwayMission.addCrewMemberToAwayTeam(cm1);

        assertEquals(cm1, testAwayMission.getAwayTeam().get(0));
        assertEquals(1, testAwayMission.getAwayTeam().size());

        testAwayMission.addCrewMemberToAwayTeam(cm2);

        assertEquals(cm2, testAwayMission.getAwayTeam().get(1));
        assertEquals(2, testAwayMission.getAwayTeam().size());

        testAwayMission.addCrewMemberToAwayTeam(cm3);

        assertEquals(cm3, testAwayMission.getAwayTeam().get(2));
        assertEquals(3, testAwayMission.getAwayTeam().size());
    }

    @Test
    void testRemoveCrewMemberFromAwayTeamInactiveMission() {
        testAwayMission.setAwayTeam(testAwayTeam);
        assertTrue(testAwayMission.getAwayTeam().contains(cm1));
        assertEquals(3, testAwayMission.getAwayTeam().size());

        testAwayMission.removeCrewMemberFromAwayTeam(cm1);

        assertFalse(testAwayMission.getAwayTeam().contains(cm1));
        assertEquals(2, testAwayMission.getAwayTeam().size());
    }

    @Test
    void testRemoveCrewMemberFromAwayTeamActiveMission() {
        testAwayMission.setIsActive(true);
        testAwayMission.setAwayTeam(testAwayTeam);
        cm1.setIsOnStarship(false);
        assertTrue(testAwayMission.getAwayTeam().contains(cm1));
        assertEquals(3, testAwayMission.getAwayTeam().size());

        testAwayMission.removeCrewMemberFromAwayTeam(cm1);

        assertFalse(testAwayMission.getAwayTeam().contains(cm1));
        assertEquals(2, testAwayMission.getAwayTeam().size());
        assertTrue(cm1.getIsOnStarship());
    }

    @Test
    void testRemoveCrewMemberFromAwayTeamNotOnTeam() {
        testAwayMission.addCrewMemberToAwayTeam(cm1);
        testAwayMission.addCrewMemberToAwayTeam(cm2);
        assertFalse(testAwayMission.getAwayTeam().contains(cm3));

        testAwayMission.removeCrewMemberFromAwayTeam(cm3);

        assertFalse(testAwayMission.getAwayTeam().contains(cm3));
        assertEquals(cm1, testAwayMission.getAwayTeam().get(0));
        assertEquals(cm2, testAwayMission.getAwayTeam().get(1));
    }

    @Test
    void testRemoveCrewMemberFromAwayTeamMultipleTimes() {
        testAwayMission.setAwayTeam(testAwayTeam);
        assertTrue(testAwayMission.getAwayTeam().contains(cm1));
        assertTrue(testAwayMission.getAwayTeam().contains(cm2));
        assertTrue(testAwayMission.getAwayTeam().contains(cm3));
        assertEquals(3, testAwayMission.getAwayTeam().size());

        testAwayMission.removeCrewMemberFromAwayTeam(cm1);

        assertFalse(testAwayMission.getAwayTeam().contains(cm1));
        assertEquals(2, testAwayMission.getAwayTeam().size());

        testAwayMission.removeCrewMemberFromAwayTeam(cm2);

        assertFalse(testAwayMission.getAwayTeam().contains(cm2));
        assertEquals(1, testAwayMission.getAwayTeam().size());

        testAwayMission.removeCrewMemberFromAwayTeam(cm3);

        assertFalse(testAwayMission.getAwayTeam().contains(cm3));
        assertEquals(0, testAwayMission.getAwayTeam().size());
    }

    @Test
    void testTransportAwayTeamToStarship() {
        testAwayMission.setAwayTeam(testAwayTeam);
        for(CrewMember cm: testAwayTeam) {
            cm.setIsOnStarship(false);
        }

        testAwayMission.transportAwayTeamToStarship();

        for(CrewMember cm: testAwayTeam) {
            assertTrue(cm.getIsOnStarship());
        }
    }

    @Test
    void testTransportAwayTeamOffOfStarship() {
        testAwayMission.setAwayTeam(testAwayTeam);
        for(CrewMember cm: testAwayTeam) {
            assertTrue(cm.getIsOnStarship());
        }

        testAwayMission.transportAwayTeamOffOfStarship();

        for(CrewMember cm: testAwayTeam) {
            assertFalse(cm.getIsOnStarship());
        }
    }
}
