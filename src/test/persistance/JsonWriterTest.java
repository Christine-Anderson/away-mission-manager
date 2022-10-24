package persistance;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static model.Starship.INITIAL_AWAY_MISSION_ID;
import static model.Starship.INITIAL_STARDATE;
import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    private Starship starship;

    @BeforeEach
    void setUp() {
        starship = new Starship("James T.", "Kirk");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyStarship() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyStarship.json");
            writer.open();
            writer.write(starship);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyStarship.json");
            starship = reader.read();
            checkStarship("James T.", "Kirk", INITIAL_STARDATE, INITIAL_AWAY_MISSION_ID, starship);
            assertTrue(starship.getCrewMembers().isEmpty());
            assertTrue(starship.getMissionLog().isEmpty());
            assertNull(starship.getCurrentAwayMission());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterStarshipWithCrewAndAwayMissions() {
        try {
            setUpStarship(starship);

            JsonWriter writer = new JsonWriter("./data/testWriterStarshipWithCrewAndAwayMissions.json");
            writer.open();
            writer.write(starship);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterStarshipWithCrewAndAwayMissions.json");
            starship = reader.read();

            checkStarship("James T.", "Kirk", INITIAL_STARDATE, INITIAL_AWAY_MISSION_ID, starship);

            assertEquals(3, starship.getCrewMembers().size());
            checkCrewMember("S'chn T'gai", "Spock", Rank.COMMANDER, Division.SCIENCES, HealthStatus.HEALTHY,
                    true, true, true, starship.getCrewMembers().get(0));
            checkCrewMember("Leonard", "McCoy", Rank.LIEUTENANT_COMMANDER, Division.MEDICAL, HealthStatus.HEALTHY,
                    false, false, true, starship.getCrewMembers().get(1));
            checkCrewMember("Montgomery", "Scott", Rank.LIEUTENANT_COMMANDER, Division.ENGINEERING, HealthStatus.HEALTHY,
                    true, false, true, starship.getCrewMembers().get(2));

            assertEquals(2, starship.getMissionLog().size());
            checkAwayMission(12345678, 45232, false, true,
                    starship.getMissionLog().get(0));
            assertEquals(2, starship.getMissionLog().get(0).getAwayTeam().size());
            checkCrewMember("S'chn T'gai", "Spock", Rank.COMMANDER, Division.SCIENCES, HealthStatus.HEALTHY,
                    true, true, true, starship.getMissionLog().get(0).getAwayTeam().get(0));
            checkCrewMember("Leonard", "McCoy", Rank.LIEUTENANT_COMMANDER, Division.MEDICAL, HealthStatus.HEALTHY,
                    false, false, true, starship.getMissionLog().get(0).getAwayTeam().get(1));

            checkAwayMission(12345679, 53924, false, false,
                    starship.getMissionLog().get(1));
            assertEquals(2, starship.getMissionLog().get(0).getAwayTeam().size());
            checkCrewMember("S'chn T'gai", "Spock", Rank.COMMANDER, Division.SCIENCES, HealthStatus.HEALTHY,
                    true, true, true, starship.getMissionLog().get(1).getAwayTeam().get(0));
            checkCrewMember("Leonard", "McCoy", Rank.LIEUTENANT_COMMANDER, Division.MEDICAL, HealthStatus.HEALTHY,
                    false, false, true, starship.getMissionLog().get(1).getAwayTeam().get(1));

            checkAwayMission(12345680, 45237, true, false,
                    starship.getCurrentAwayMission());
            assertEquals(1, starship.getCurrentAwayMission().getAwayTeam().size());
            checkCrewMember("Montgomery", "Scott", Rank.LIEUTENANT_COMMANDER, Division.ENGINEERING, HealthStatus.HEALTHY,
                    true, false, true, starship.getCurrentAwayMission().getAwayTeam().get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
