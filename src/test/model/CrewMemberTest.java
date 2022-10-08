package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class CrewMemberTest {
    private CrewMember testCrewMember;

    @BeforeEach
    void setUp() {
        testCrewMember = new CrewMember("Spock", Rank.COMMANDER, Division.SCIENCES);
    }

    @Test //TODO ask if i need to test for "randomness" of .next boolean
    void testCrewMemberConstructor() {
        assertEquals("Spock", testCrewMember.getName()); //TODO update
        assertEquals(Rank.COMMANDER, testCrewMember.getRank());
        assertEquals(Division.SCIENCES, testCrewMember.getDivision());
        assertEquals(HealthStatus.HEALTHY, testCrewMember.getHealthStatus());
        assertFalse(testCrewMember.isOnAwayMission());
    }
}
