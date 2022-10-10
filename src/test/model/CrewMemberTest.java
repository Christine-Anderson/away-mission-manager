package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CrewMemberTest {
    private CrewMember testCrewMember;

    @BeforeEach
    void setUp() {
        testCrewMember = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER,
                Division.SCIENCES);
    }

    @Test
    void testCrewMemberConstructor() {
        assertEquals("S'chn T'gai", testCrewMember.getFirstName());
        assertEquals("Spock", testCrewMember.getLastName());
        assertEquals(Rank.COMMANDER, testCrewMember.getRank());
        assertEquals(Division.SCIENCES, testCrewMember.getDivision());
        assertEquals(HealthStatus.HEALTHY, testCrewMember.getHealthStatus());
        assertNotNull(testCrewMember.hasRedShirt()); //TODO is there something better to test, it just needs to be set to something
        assertNotNull(testCrewMember.hasPlotArmour());
        assertTrue(testCrewMember.isOnStarship());
    }

    @Test
    void testUpdateHealthStatusHasPlotArmour() {
// TODO all three
    }

    @Test
    void testUpdateHealthStatusHasRedShirtNoPlotArmour() {

    }

    @Test
    void testUpdateHealthStatusNoPlotArmour() {

    }
}
