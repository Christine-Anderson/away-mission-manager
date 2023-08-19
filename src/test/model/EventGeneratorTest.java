package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class EventGeneratorTest {
    EventGenerator eventGenerator;
    EventLog eventLog;
    Iterator<Event> iterator;

    @BeforeEach
    void setUp() {
        eventGenerator = new EventGenerator();
        eventLog = EventLog.getInstance();
        eventLog.clear();
    }

    @Test
    void testCreateAwayMissionEvent() {
        eventGenerator.createAwayMissionEvent(123456, "1234.5");
        iterator = eventLog.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        assertEquals("Created new away mission 123456 on stardate 1234.5.", iterator.next().getDescription());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testStartAwayMissionEvent() {
        eventGenerator.startAwayMissionEvent(123456);
        iterator = eventLog.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        assertEquals("Mission 123456 started.", iterator.next().getDescription());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testEndAwayMissionEvent() {
        eventGenerator.endAwayMissionEvent(123456);
        iterator = eventLog.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        assertEquals("Mission 123456 ended.", iterator.next().getDescription());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testUpdateHealthStatusEvent() {
        eventGenerator.updateHealthStatusEvent("Tribble", "dead");
        iterator = eventLog.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        assertEquals("Tribble returned dead.", iterator.next().getDescription());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testCompleteAwayMissionObjectiveEvent() {
        eventGenerator.completeAwayMissionObjectiveEvent(123456);
        iterator = eventLog.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        assertEquals("Mission 123456 objective complete.", iterator.next().getDescription());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testAddCurrentAwayMissionToMissionLogEvent() {
        eventGenerator.addCurrentAwayMissionToMissionLogEvent(123456);
        iterator = eventLog.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        assertEquals("Mission 123456 added to the away mission Log.", iterator.next().getDescription());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testAddCrewMemberToAwayTeamEvent() {
        eventGenerator.addCrewMemberToAwayTeamEvent("Giant Spock");
        iterator = eventLog.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        assertEquals("Giant Spock added to the away team.", iterator.next().getDescription());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testTransportOffOfStarshipEvent() {
        eventGenerator.transportOffOfStarshipEvent("Giant Spock");
        iterator = eventLog.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        assertEquals("Giant Spock transported off of the starship.", iterator.next().getDescription());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testRemoveCrewMemberFromAwayTeamEvent() {
        eventGenerator.removeCrewMemberFromAwayTeamEvent("Giant Spock");
        iterator = eventLog.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        assertEquals("Giant Spock removed from the away team.", iterator.next().getDescription());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testTransportToStarshipEvent() {
        eventGenerator.transportToStarshipEvent("Giant Spock");
        iterator = eventLog.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        assertEquals("Giant Spock transported to the starship.", iterator.next().getDescription());
        assertFalse(iterator.hasNext());
    }

    @Test
    void testMultipleEvents() {
        eventGenerator.createAwayMissionEvent(123456, "1234.5");
        eventGenerator.addCrewMemberToAwayTeamEvent("Giant Spock");
        eventGenerator.startAwayMissionEvent(123456);
        eventGenerator.transportOffOfStarshipEvent("Giant Spock");

        iterator = eventLog.iterator();
        assertTrue(iterator.hasNext());
        assertEquals("Event log cleared.", iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        assertEquals("Created new away mission 123456 on stardate 1234.5.", iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        assertEquals("Giant Spock added to the away team.", iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        assertEquals("Mission 123456 started.", iterator.next().getDescription());
        assertTrue(iterator.hasNext());
        assertEquals("Giant Spock transported off of the starship.", iterator.next().getDescription());
        assertFalse(iterator.hasNext());
    }
}
