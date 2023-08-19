package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

public class CrewMemberTest {
    private CrewMember testCrewMember;
    HashSet<Boolean> seenBoolean;
    HashSet<Boolean> allOptionsBoolean;
    HashSet<HealthStatus> seenHealthStatus;
    HashSet<HealthStatus> allOptionsHealthStatus;

    @BeforeEach
    void setUp() {
        testCrewMember = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER,
                Division.SCIENCES);
        seenBoolean = new HashSet<>();
        allOptionsBoolean = new HashSet<>();
        allOptionsBoolean.add(true);
        allOptionsBoolean.add(false);
        seenHealthStatus = new HashSet<>();
        allOptionsHealthStatus = new HashSet<>();
    }

    @Test
    void testCrewMemberConstructor() {
        assertEquals("S'chn T'gai", testCrewMember.getFirstName());
        assertEquals("Spock", testCrewMember.getLastName());
        assertEquals(Rank.COMMANDER, testCrewMember.getRank());
        assertEquals(Division.SCIENCES, testCrewMember.getDivision());
        assertEquals(HealthStatus.HEALTHY, testCrewMember.getHealthStatus());
        assertTrue(testCrewMember.getIsOnStarship());
    }

    @Test
    void testCrewMemberConstructor2() {
        testCrewMember = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER, Division.SCIENCES,
                HealthStatus.DEAD, false, true, false);

        assertEquals("S'chn T'gai", testCrewMember.getFirstName());
        assertEquals("Spock", testCrewMember.getLastName());
        assertEquals(Rank.COMMANDER, testCrewMember.getRank());
        assertEquals(Division.SCIENCES, testCrewMember.getDivision());
        assertEquals(HealthStatus.DEAD, testCrewMember.getHealthStatus());
        assertFalse(testCrewMember.getHasRedShirt());
        assertTrue(testCrewMember.getHasPlotArmour());
        assertFalse(testCrewMember.getIsOnStarship());
    }

    @Test
    void testSettersAndGetters() {
        testCrewMember.setFirstName("fn");
        testCrewMember.setLastName("ln");
        testCrewMember.setRank(Rank.OTHER);
        testCrewMember.setDivision(Division.OTHER);
        testCrewMember.setHealthStatus(HealthStatus.DEAD);
        testCrewMember.setHasRedShirt(true);
        testCrewMember.setHasPlotArmour(true);
        testCrewMember.setIsOnStarship(false);

        assertEquals("fn", testCrewMember.getFirstName());
        assertEquals("ln", testCrewMember.getLastName());
        assertEquals(Rank.OTHER, testCrewMember.getRank());
        assertEquals(Division.OTHER, testCrewMember.getDivision());
        assertEquals(HealthStatus.DEAD, testCrewMember.getHealthStatus());
        assertTrue(testCrewMember.getHasRedShirt());
        assertTrue(testCrewMember.getHasPlotArmour());
        assertFalse(testCrewMember.getIsOnStarship());
    }

    @Test
    void testCrewMemberConstructorRandomCoverageHasRedShirt() {
        for (int i = 0; i <= 1000; i++) {
            testCrewMember = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER,
                    Division.SCIENCES);
            seenBoolean.add(testCrewMember.getHasRedShirt());

            if (seenBoolean.equals(allOptionsBoolean)) {
                break;
            }
        }
        assertEquals(allOptionsBoolean, seenBoolean);
    }

    @Test
    void testCrewMemberConstructorRandomCoverageHasPlotArmour() {
        for (int i = 0; i <= 1000; i++) {
            testCrewMember = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER,
                    Division.SCIENCES);
            seenBoolean.add(testCrewMember.getHasPlotArmour());

            if (seenBoolean.equals(allOptionsBoolean)) {
                break;
            }
        }
        assertEquals(allOptionsBoolean, seenBoolean);
    }

    @Test
    void testUpdateHealthStatusHasPlotArmour() {
        allOptionsHealthStatus.add(HealthStatus.HEALTHY);
        allOptionsHealthStatus.add(HealthStatus.INJURED);

        for (int i = 0; i <= 1000; i++) {
            testCrewMember = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER,
                    Division.SCIENCES);
            testCrewMember.setHasPlotArmour(true);

            testCrewMember.updateHealthStatus();
            seenHealthStatus.add(testCrewMember.getHealthStatus());

            if (seenHealthStatus.equals(allOptionsHealthStatus)) {
                break;
            }
        }
        assertEquals(allOptionsHealthStatus, seenHealthStatus);
    }

    @Test
    void testUpdateHealthStatusHasRedShirtNoPlotArmour() {
        allOptionsHealthStatus.add(HealthStatus.HEALTHY);
        allOptionsHealthStatus.add(HealthStatus.DEAD);

        for (int i = 0; i <= 1000; i++) {
            testCrewMember = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER,
                    Division.SCIENCES);
            testCrewMember.setHasRedShirt(true);
            testCrewMember.setHasPlotArmour(false);

            testCrewMember.updateHealthStatus();
            seenHealthStatus.add(testCrewMember.getHealthStatus());

            if (seenHealthStatus.equals(allOptionsHealthStatus)) {
                break;
            }
        }
        assertEquals(allOptionsHealthStatus, seenHealthStatus);
    }

    @Test
    void testUpdateHealthStatusNoRedShirtNoPlotArmour() {
        allOptionsHealthStatus.add(HealthStatus.HEALTHY);
        allOptionsHealthStatus.add(HealthStatus.INJURED);
        allOptionsHealthStatus.add(HealthStatus.DEAD);

        for (int i = 0; i <= 1000; i++) {
            testCrewMember = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER,
                    Division.SCIENCES);
            testCrewMember.setHasRedShirt(false);
            testCrewMember.setHasPlotArmour(false);

            testCrewMember.updateHealthStatus();
            seenHealthStatus.add(testCrewMember.getHealthStatus());

            if (seenHealthStatus.equals(allOptionsHealthStatus)) {
                break;
            }
        }
        assertEquals(allOptionsHealthStatus, seenHealthStatus);
    }

    @Test
    void testNameToStringNoLastName() {
        testCrewMember.setLastName("");

        assertEquals("S'chn T'gai", testCrewMember.nameToString(true));
        assertEquals("S'chn T'gai", testCrewMember.nameToString(false));
    }

    @Test
    void testNameToStringFirstNameFirst() {
        assertEquals("S'chn T'gai Spock", testCrewMember.nameToString(true));
    }

    @Test
    void testNameToStringFirstNameLast() {
        assertEquals("Spock, S'chn T'gai", testCrewMember.nameToString(false));
    }

}
