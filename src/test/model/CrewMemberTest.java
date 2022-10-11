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
        assertTrue(testCrewMember.isOnStarship());
    }

    @Test
    void testCrewMemberConstructorRandomCoverageHasRedShirt() {
        for(int i = 0; i <= 1000; i++) {
            testCrewMember = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER,
                    Division.SCIENCES);
            seenBoolean.add(testCrewMember.hasRedShirt());

            if(seenBoolean.equals(allOptionsBoolean)) {
                break;
            }
        }
        assertTrue(seenBoolean.equals(allOptionsBoolean));
    }

    @Test
    void testCrewMemberConstructorRandomCoverageHasPlotArmour() {
        for(int i = 0; i <= 1000; i++) {
            testCrewMember = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER,
                    Division.SCIENCES);
            seenBoolean.add(testCrewMember.hasPlotArmour());

            if(seenBoolean.equals(allOptionsBoolean)) {
                break;
            }
        }
        assertTrue(seenBoolean.equals(allOptionsBoolean));
    }

    @Test
    void testUpdateHealthStatusHasPlotArmour() {
        allOptionsHealthStatus.add(HealthStatus.HEALTHY);
        allOptionsHealthStatus.add(HealthStatus.INJURED);

        for(int i = 0; i <= 1000; i++) {
            testCrewMember = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER,
                    Division.SCIENCES);
            testCrewMember.setHasPlotArmour(true);

            testCrewMember.updateHealthStatus();
            seenHealthStatus.add(testCrewMember.getHealthStatus());

            if(seenHealthStatus.equals(allOptionsHealthStatus)) {
                break;
            }
        }
        assertTrue(seenHealthStatus.equals(allOptionsHealthStatus));
    }

    @Test
    void testUpdateHealthStatusHasRedShirtNoPlotArmour() {
        allOptionsHealthStatus.add(HealthStatus.HEALTHY);
        allOptionsHealthStatus.add(HealthStatus.DEAD);

        for(int i = 0; i <= 1000; i++) {
            testCrewMember = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER,
                    Division.SCIENCES);
            testCrewMember.setHasRedShirt(true);
            testCrewMember.setHasPlotArmour(false);

            testCrewMember.updateHealthStatus();
            seenHealthStatus.add(testCrewMember.getHealthStatus());

            if(seenHealthStatus.equals(allOptionsHealthStatus)) {
                break;
            }
        }
        assertTrue(seenHealthStatus.equals(allOptionsHealthStatus));
    }

    @Test
    void testUpdateHealthStatusNoRedShirtNoPlotArmour() {
        allOptionsHealthStatus.add(HealthStatus.HEALTHY);
        allOptionsHealthStatus.add(HealthStatus.INJURED);
        allOptionsHealthStatus.add(HealthStatus.DEAD);

        for(int i = 0; i <= 1000; i++) {
            testCrewMember = new CrewMember("S'chn T'gai", "Spock", Rank.COMMANDER,
                    Division.SCIENCES);
            testCrewMember.setHasRedShirt(false);
            testCrewMember.setHasPlotArmour(false);

            testCrewMember.updateHealthStatus();
            seenHealthStatus.add(testCrewMember.getHealthStatus());

            if(seenHealthStatus.equals(allOptionsHealthStatus)) {
                break;
            }
        }
        assertTrue(seenHealthStatus.equals(allOptionsHealthStatus));
    }
}
