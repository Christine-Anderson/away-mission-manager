package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static model.Starship.SHIP_ID;
import static model.Starship.SHIP_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StarshipTest {
    private Starship testStarship;

    @BeforeEach
    void setUp() {
        testStarship = new Starship("James T.", "Kirk");
    }

    @Test
    void testStarshipConstructor() {
        assertEquals(SHIP_NAME, testStarship.getShipName()); //TODO how to fix this?
        assertEquals(SHIP_ID, testStarship.getShipID());
        assertEquals("James T. Kirk", testStarship.getCaptain()); //TODO update
        assertTrue(testStarship.getCrewMembers().isEmpty());
    }

    @Test
    void name() {
    }

    @Test
    void name() {
    }

    @Test
    void name() {

    }
}
