package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Event class
 */
public class EventTest {
    private Event e;
    private Date d;

    @BeforeEach
    public void runBefore() {
        e = new Event("Giant Spock added to the away team.");
        d = Calendar.getInstance().getTime();
    }

    @Test
    public void testEvent() {
        assertEquals("Giant Spock added to the away team.", e.getDescription());
    }

    @Test
    public void testToString() {
        assertEquals(d.toString() + "\n" + "Giant Spock added to the away team.", e.toString());
    }
}
