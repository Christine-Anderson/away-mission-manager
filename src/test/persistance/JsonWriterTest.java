package persistance;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static model.Starship.INITIAL_AWAY_MISSION_ID;
import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {
    private Starship testStarship;

    @BeforeEach
    void setUp() {
        testStarship = new Starship("James T.", "Kirk");
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
    void testWriterUnchangedStarship() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterUnchangedStarship.json");
            writer.open();
            writer.write(testStarship);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterUnchangedStarship.json");
            testStarship = reader.read();
            assertEquals("James T.", testStarship.getFirstNameOfCaptain());
            assertEquals("Kirk", testStarship.getLastNameOfCaptain());
            assertEquals(INTIAL_STARDATE, testStarship.getCurrentStardate());
            assertEquals(INITIAL_AWAY_MISSION_ID, testStarship.getAwayMissionID());
            assertNull(testStarship.getCurrentAwayMission());
            assertTrue(testStarship.getCrewMembers().isEmpty());
            assertTrue(testStarship.getMissionLog().isEmpty());
            //checkStarship();
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterStarshipWithCrewAndAwayMissions() {
        try {
            setUpStarship(testStarship);

            JsonWriter writer = new JsonWriter("./data/testWriterStarshipWithCrewAndAwayMissions.json");
            writer.open();
            writer.write(testStarship);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterStarshipWithCrewAndAwayMissions.json");
            testStarship = reader.read();
            assertEquals("My work room", wr.getName());
            List<Thingy> thingies = wr.getThingies();
            assertEquals(2, thingies.size());
            //abstract away some checks?
//            checkThingy("saw", Category.METALWORK, thingies.get(0));
//            checkThingy("needle", Category.STITCHING, thingies.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
