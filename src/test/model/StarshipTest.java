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
    private List<CrewMember> testCrewMemberList;

    @BeforeEach
    void setUp() {
        testStarship = new Starship("James T.", "Kirk");
        cm1 = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER, Division.SCIENCES);
        cm2 = new CrewMember("Leonard", "McCoy", Rank.LIEUTENANT_COMMANDER, Division.MEDICAL);
        cm3 = new CrewMember("Montgomery", "Scott", Rank.LIEUTENANT_COMMANDER, Division.ENGINEERING);
        testCrewMemberList = new ArrayList<>();
    }

    @Test
    void testStarshipConstructor() {
        assertEquals("USS Intrepid", testStarship.getShipName());
        assertEquals("NCC-74600", testStarship.getShipID());
        assertEquals("James T.", testStarship.getFirstNameOfCaptain());
        assertEquals("Kirk", testStarship.getLastNameOfCaptain());
        assertTrue(testStarship.getCrewMembers().isEmpty());
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
        testStarship.setCrewMembers(testCrewMemberList);
        assertTrue(testStarship.hasCrewMember(cm1));
        assertEquals(3, testStarship.getCrewMembers().size());

        testStarship.addCrewMember(cm1);

        assertTrue(testStarship.hasCrewMember(cm1));
        assertEquals(3, testStarship.getCrewMembers().size());
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
        testStarship.setCrewMembers(testCrewMemberList);
        assertTrue(testStarship.hasCrewMember(cm1));
        assertEquals(3, testStarship.getCrewMembers().size());

        testStarship.removeCrewMember(cm1);

        assertFalse(testStarship.hasCrewMember(cm1));
        assertEquals(2, testStarship.getCrewMembers().size());

    }

    @Test
    void testRemoveCrewMemberNotOnStarship() {
        testStarship.addCrewMember(cm1);
        testStarship.addCrewMember(cm2);
        assertFalse(testStarship.hasCrewMember(cm3));
        assertEquals(2, testStarship.getCrewMembers().size());

        testStarship.removeCrewMember(cm3);

        assertFalse(testStarship.hasCrewMember(cm3));
        assertEquals(2, testStarship.getCrewMembers().size());
    }

    @Test
    void testRemoveCrewMemberMultipleTimes() {
        testStarship.setCrewMembers(testCrewMemberList);
        assertTrue(testStarship.hasCrewMember(cm1));
        assertTrue(testStarship.hasCrewMember(cm2));
        assertTrue(testStarship.hasCrewMember(cm3));
        assertEquals(3, testStarship.getCrewMembers().size());

        testStarship.removeCrewMember(cm1);

        assertFalse(testStarship.hasCrewMember(cm1));
        assertEquals(2, testStarship.getCrewMembers().size());

        testStarship.removeCrewMember(cm2);

        assertFalse(testStarship.hasCrewMember(cm2));
        assertEquals(1, testStarship.getCrewMembers().size());

        testStarship.removeCrewMember(cm3);

        assertFalse(testStarship.hasCrewMember(cm3));
        assertEquals(0, testStarship.getCrewMembers().size());
    }
}
