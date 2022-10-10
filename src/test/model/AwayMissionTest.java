package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AwayMissionTest {
    private AwayMission testAwayMission;
    private Starship testStarship;
    private CrewMember cm1;
    private CrewMember cm2;
    private CrewMember cm3;
    private List<CrewMember> testAwayTeam;

    @BeforeEach
    void setUp() {
        testAwayMission = new AwayMission(123456, 41025);
        testStarship = new Starship("James T.", "Kirk");
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
        assertEquals(123456, testAwayMission.getAwayMissionID());
        assertEquals(41025, testAwayMission.getStardate());
        assertFalse(testAwayMission.isActive());
        assertFalse(testAwayMission.isObjectiveComplete());
        assertTrue(testAwayMission.getAwayTeam().isEmpty());
    }

    @Test
    void testAddCrewMemberToAwayTeamInactiveMission() {
        testAwayMission.addCrewMemberToAwayTeam(cm1);

        assertEquals(cm1, testAwayMission.getAwayTeam().get(0));
        assertEquals(1, testAwayMission.getAwayTeam().size());
    }

    @Test
    void testAddCrewMemberToAwayTeamActiveMission() {
        testAwayMission.setActive(true);
        assertTrue(cm1.isOnStarship());

        testAwayMission.addCrewMemberToAwayTeam(cm1);

        assertEquals(cm1, testAwayMission.getAwayTeam().get(0));
        assertEquals(1, testAwayMission.getAwayTeam().size());
        assertFalse(cm1.isOnStarship());
    }

    @Test
    void testAddCrewMemberToAwayTeamAlreadyOnAwayTeam() {
        testAwayMission.setAwayTeam(testAwayTeam);
        assertTrue(testAwayMission.getAwayTeam().contains(cm1));

        testAwayMission.addCrewMemberToAwayTeam(cm1);

        assertEquals(testAwayTeam, testAwayMission.getAwayTeam());
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
        testAwayMission.setActive(true);
        testAwayMission.setAwayTeam(testAwayTeam);
        cm1.setOnStarship(false);
        assertTrue(testAwayMission.getAwayTeam().contains(cm1));
        assertEquals(3, testAwayMission.getAwayTeam().size());

        testAwayMission.removeCrewMemberFromAwayTeam(cm1);

        assertFalse(testAwayMission.getAwayTeam().contains(cm1));
        assertEquals(2, testAwayMission.getAwayTeam().size());
        assertTrue(cm1.isOnStarship());
        // TODO test that health status updated...
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
}
