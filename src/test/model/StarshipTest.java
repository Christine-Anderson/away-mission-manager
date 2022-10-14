package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static model.Starship.*;
import static org.junit.jupiter.api.Assertions.*;

public class StarshipTest {
    private Starship testStarship;
    private CrewMember cm1;
    private CrewMember cm2;
    private CrewMember cm3;
    private List<CrewMember> testCrewMembers;
    private AwayMission am1;
    private AwayMission am2;
    private AwayMission am3;
    private List<CrewMember> testAwayTeam;

    @BeforeEach
    void setUp() {
        testStarship = new Starship("James T.", "Kirk");
        cm1 = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER, Division.SCIENCES);
        cm2 = new CrewMember("Leonard", "McCoy", Rank.LIEUTENANT_COMMANDER, Division.MEDICAL);
        cm3 = new CrewMember("Montgomery", "Scott", Rank.LIEUTENANT_COMMANDER, Division.ENGINEERING);
        testCrewMembers = new ArrayList<>();
        testCrewMembers.add(cm1);
        testCrewMembers.add(cm2);
        testCrewMembers.add(cm3);
        am1 = new AwayMission(12345678, 45232);
        am2 = new AwayMission(12345679, 53924);
        am3 = new AwayMission(12345680, 45237);
        testAwayTeam = new ArrayList<>();
        testAwayTeam.add(cm1);
        testAwayTeam.add(cm2);
        testAwayTeam.add(cm3);
    }

    @Test
    void testStarshipConstructor() {
        assertEquals(SHIP_NAME, testStarship.getShipName());
        assertEquals(SHIP_ID, testStarship.getShipID());
        assertEquals("James T.", testStarship.getFirstNameOfCaptain());
        assertEquals("Kirk", testStarship.getLastNameOfCaptain());
        assertEquals(INTIAL_STARDATE, testStarship.getCurrentStardate());
        assertEquals(INITIAL_AWAY_MISSION_ID, testStarship.getAwayMissionID());
        assertNull(testStarship.getCurrentAwayMission());
        assertTrue(testStarship.getCrewMembers().isEmpty());
        assertTrue(testStarship.getMissionLog().isEmpty());
    }

    //TODO cheeck all tests for good setup checks
    @Test
    void testAddCrewMember() {
        testStarship.addCrewMember(cm1);

        assertEquals(cm1, testStarship.getCrewMembers().get(0));
        assertEquals(1, testStarship.getCrewMembers().size());
    }

    @Test
    void testAddCrewMemberAlreadyOnStarship() {
        testStarship.setCrewMembers(testCrewMembers);
        assertTrue(testStarship.getCrewMembers().contains(cm1));

        testStarship.addCrewMember(cm1);

        assertEquals(testCrewMembers, testStarship.getCrewMembers());
    }

    @Test
    void testAddCrewMemberMultipleTimes() {
        testStarship.addCrewMember(cm1);

        assertEquals(cm1, testStarship.getCrewMembers().get(0));
        assertEquals(1, testStarship.getCrewMembers().size());

        testStarship.addCrewMember(cm2);

        assertEquals(cm2, testStarship.getCrewMembers().get(1));
        assertEquals(2, testStarship.getCrewMembers().size());

        testStarship.addCrewMember(cm3);

        assertEquals(cm3, testStarship.getCrewMembers().get(2));
        assertEquals(3, testStarship.getCrewMembers().size());
    }

    @Test
    void testRemoveCrewMember() {
        testStarship.setCrewMembers(testCrewMembers);
        assertTrue(testStarship.getCrewMembers().contains(cm1));
        assertEquals(3, testStarship.getCrewMembers().size());

        testStarship.removeCrewMember(cm1);

        assertFalse(testStarship.getCrewMembers().contains(cm1));
        assertEquals(2, testStarship.getCrewMembers().size());

    }

    @Test
    void testRemoveCrewMemberNotOnStarship() {
        testStarship.addCrewMember(cm1);
        testStarship.addCrewMember(cm2);
        assertFalse(testStarship.getCrewMembers().contains(cm3));
        assertEquals(2, testStarship.getCrewMembers().size());

        testStarship.removeCrewMember(cm3);

        assertFalse(testStarship.getCrewMembers().contains(cm3));
        assertEquals(cm1, testStarship.getCrewMembers().get(0));
        assertEquals(cm2, testStarship.getCrewMembers().get(1));
    }

    @Test
    void testRemoveCrewMemberMultipleTimes() {
        testStarship.setCrewMembers(testCrewMembers);
        assertTrue(testStarship.getCrewMembers().contains(cm1));
        assertTrue(testStarship.getCrewMembers().contains(cm2));
        assertTrue(testStarship.getCrewMembers().contains(cm3));
        assertEquals(3, testStarship.getCrewMembers().size());

        testStarship.removeCrewMember(cm1);

        assertFalse(testStarship.getCrewMembers().contains(cm1));
        assertEquals(2, testStarship.getCrewMembers().size());

        testStarship.removeCrewMember(cm2);

        assertFalse(testStarship.getCrewMembers().contains(cm2));
        assertEquals(1, testStarship.getCrewMembers().size());

        testStarship.removeCrewMember(cm3);

        assertFalse(testStarship.getCrewMembers().contains(cm3));
        assertEquals(0, testStarship.getCrewMembers().size());
    }

    @Test
    void testCreateAwayMission() {
        testStarship.createAwayMission();
        
        assertNotNull(testStarship.getCurrentAwayMission());
        assertEquals(INTIAL_STARDATE + 1, testStarship.getCurrentAwayMission().getStardate());
        assertEquals(INITIAL_AWAY_MISSION_ID, testStarship.getCurrentAwayMission().getAwayMissionID());
    }

    @Test
    void testStartAwayMissionInactiveMission() {
        am1.setAwayTeam(testAwayTeam);
        testStarship.setCurrentAwayMission(am1);
        assertFalse(testStarship.getCurrentAwayMission().getIsActive());
        for(CrewMember cm: testAwayTeam) {
            assertTrue(cm.getIsOnStarship());
        }

        testStarship.startAwayMission();

        assertTrue(testStarship.getCurrentAwayMission().getIsActive());
        for(CrewMember cm: testAwayTeam) {
            assertFalse(cm.getIsOnStarship());
        }
    }

    @Test
    void testStartAwayMissionActiveMission() {
        am1.setAwayTeam(testAwayTeam);
        am1.setIsActive(true);
        testStarship.setCurrentAwayMission(am1);
        for(CrewMember cm: testAwayTeam) {
            cm.setIsOnStarship(false);
        }

        testStarship.startAwayMission();

        assertTrue(testStarship.getCurrentAwayMission().getIsActive());
        for(CrewMember cm: testAwayTeam) {
            assertFalse(cm.getIsOnStarship());
        }
    }

    @Test
    void testEmergencyBeamOutActiveMission() {
        am1.setAwayTeam(testAwayTeam);
        am1.setIsActive(true);
        testStarship.setCurrentAwayMission(am1);
        assertFalse(testStarship.getCurrentAwayMission().getIsObjectiveComplete());
        assertTrue(testStarship.getMissionLog().isEmpty());
        //TODO test health status??
        for(CrewMember cm: testAwayTeam) {
            cm.setIsOnStarship(false);
        }

        testStarship.emergencyBeamOut();

        assertFalse(am1.getIsActive());
        assertFalse(am1.getIsObjectiveComplete());
        assertEquals(am1, testStarship.getMissionLog().get(0));
        //TODO test health status??
        for(CrewMember cm: testAwayTeam) {
            assertTrue(cm.getIsOnStarship());
        }
        assertNull(testStarship.getCurrentAwayMission());
    }

    @Test
    void testEmergencyBeamOutInactiveMission() {
        am1.setAwayTeam(testAwayTeam);
        testStarship.setCurrentAwayMission(am1);
        assertFalse(testStarship.getCurrentAwayMission().getIsActive());
        assertFalse(testStarship.getCurrentAwayMission().getIsObjectiveComplete());
        assertTrue(testStarship.getMissionLog().isEmpty());
        //TODO test health status??
        for(CrewMember cm: testAwayTeam) {
            assertTrue(cm.getIsOnStarship());
        }

        testStarship.endAwayMission();

        assertFalse(testStarship.getCurrentAwayMission().getIsActive());
        assertFalse(testStarship.getCurrentAwayMission().getIsObjectiveComplete());
        assertTrue(testStarship.getMissionLog().isEmpty());
        //TODO test health status??
        for(CrewMember cm: testAwayTeam) {
            assertTrue(cm.getIsOnStarship());
        }
    }

    @Test
    void testEndAwayMissionActiveMission() { //TODO should i do something about test code duplication? for example if things are tested elsewhere
        am1.setAwayTeam(testAwayTeam);
        am1.setIsActive(true);
        testStarship.setCurrentAwayMission(am1);
        assertFalse(testStarship.getCurrentAwayMission().getIsObjectiveComplete());
        assertTrue(testStarship.getMissionLog().isEmpty());
        //TODO test health status??
        for(CrewMember cm: testAwayTeam) {
            cm.setIsOnStarship(false);
        }

        testStarship.endAwayMission();

        assertFalse(am1.getIsActive());
        assertTrue(am1.getIsObjectiveComplete());
        assertEquals(am1, testStarship.getMissionLog().get(0));
        //TODO test health status??
        for(CrewMember cm: testAwayTeam) {
            assertTrue(cm.getIsOnStarship());
        }
        assertNull(testStarship.getCurrentAwayMission());
    }

    @Test
    void testEndAwayMissionInactiveMission() {
        am1.setAwayTeam(testAwayTeam);
        testStarship.setCurrentAwayMission(am1);
        assertFalse(testStarship.getCurrentAwayMission().getIsActive());
        assertFalse(testStarship.getCurrentAwayMission().getIsObjectiveComplete());
        assertTrue(testStarship.getMissionLog().isEmpty());
        //TODO test health status??
        for(CrewMember cm: testAwayTeam) {
            assertTrue(cm.getIsOnStarship());
        }

        testStarship.endAwayMission();

        assertFalse(testStarship.getCurrentAwayMission().getIsActive());
        assertFalse(testStarship.getCurrentAwayMission().getIsObjectiveComplete());
        assertTrue(testStarship.getMissionLog().isEmpty());
        //TODO test health status??
        for(CrewMember cm: testAwayTeam) {
            assertTrue(cm.getIsOnStarship());
        }
    }

    @Test
    void testAddAwayMissionToMissionLog() {
        testStarship.setCurrentAwayMission(am1);
        testStarship.addCurrentAwayMissionToMissionLog();

        assertEquals(am1, testStarship.getMissionLog().get(0));
        assertEquals(1, testStarship.getMissionLog().size());
    }

    @Test
    void testAddAwayMissionToMissionLogMultipleTimes() {
        testStarship.setCurrentAwayMission(am1);
        testStarship.addCurrentAwayMissionToMissionLog();

        assertEquals(am1, testStarship.getMissionLog().get(0));
        assertEquals(1, testStarship.getMissionLog().size());

        testStarship.setCurrentAwayMission(am2);
        testStarship.addCurrentAwayMissionToMissionLog();

        assertEquals(am2, testStarship.getMissionLog().get(1));
        assertEquals(2, testStarship.getMissionLog().size());

        testStarship.setCurrentAwayMission(am3);
        testStarship.addCurrentAwayMissionToMissionLog();

        assertEquals(am3, testStarship.getMissionLog().get(2));
        assertEquals(3, testStarship.getMissionLog().size());
    }

    @Test
    void testUpdateCurrentStardate() {
        assertEquals(INTIAL_STARDATE, testStarship.getCurrentStardate());
        testStarship.updateCurrentStardate();
        assertEquals(INTIAL_STARDATE + 1, testStarship.getCurrentStardate());
    }

    @Test
    void testUpdateCurrentStardateMultipleTimes() {
        assertEquals(INTIAL_STARDATE, testStarship.getCurrentStardate());
        testStarship.updateCurrentStardate();
        assertEquals(INTIAL_STARDATE + 1, testStarship.getCurrentStardate());

        testStarship.updateCurrentStardate();
        assertEquals(INTIAL_STARDATE + 2, testStarship.getCurrentStardate());
    }

    @Test
    void testPrintStardate() {
        testStarship.setCurrentStardate(12345);
        assertEquals("1234.5", testStarship.printStardate());
    }

    @Test
    void testUpdateAwayMissionID() {
        assertEquals(INITIAL_AWAY_MISSION_ID, testStarship.getAwayMissionID());
        testStarship.updateAwayMissionID();
        assertEquals(INITIAL_AWAY_MISSION_ID + 1, testStarship.getAwayMissionID());
    }

    @Test
    void testUpdateAwayMissionIDMultipleTimes() {
        assertEquals(INITIAL_AWAY_MISSION_ID, testStarship.getAwayMissionID());
        testStarship.updateAwayMissionID();
        assertEquals(INITIAL_AWAY_MISSION_ID + 1, testStarship.getAwayMissionID());

        testStarship.updateAwayMissionID();
        assertEquals(INITIAL_AWAY_MISSION_ID + 2, testStarship.getAwayMissionID());
    }
}
