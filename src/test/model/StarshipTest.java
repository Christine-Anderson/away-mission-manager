package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        //TODO create away missions
    }

    @Test
    void testStarshipConstructor() {
        assertEquals("USS Intrepid", testStarship.getShipName());
        assertEquals("NCC-74600", testStarship.getShipID());
        assertEquals("James T.", testStarship.getFirstNameOfCaptain());
        assertEquals("Kirk", testStarship.getLastNameOfCaptain());
        assertTrue(testStarship.getCrewMembers().isEmpty());
        assertTrue(testStarship.getMissionLog().isEmpty());
    }

    //TODO add tests for setters?

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
//TODO test and implement mission log
    /*@Test
    void testAddCrewMember() {
        testStarship.addCrewMember(cm1);

        assertEquals(cm1, testStarship.getCrewMembers().get(0));
        assertEquals(1, testStarship.getCrewMembers().size());
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
    }*/
}
