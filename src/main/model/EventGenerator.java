package model;

/**
 * Generates Event descriptions for Events in Starship and Away Mission and adds them to the EventLog
 */
public class EventGenerator {

    // EFFECTS: logs new events with the given event description to the event log
    private void logEvent(String eventDescription) {
        EventLog.getInstance().logEvent(new Event(eventDescription));
    }

    // EFFECTS: generates event description for a create away mission event and logs event
    public void createAwayMissionEvent(int awayMissionID, String stardate) {
        logEvent("Created new away mission " + awayMissionID + " on stardate " + stardate + ".");
    }

    // EFFECTS: generates event description for a start away mission event and logs event
    public void startAwayMissionEvent(int awayMissionID) {
        logEvent("Mission " + awayMissionID + " started.");
    }

    // EFFECTS: generates event description for an end away mission event and logs event
    public void endAwayMissionEvent(int awayMissionID) {
        logEvent("Mission " + awayMissionID + " ended.");
    }

    // EFFECTS: generates event description for an update health status event and logs event
    public void updateHealthStatusEvent(String name, String healthStatus) {
        logEvent(name + " returned " + healthStatus + ".");
    }

    // EFFECTS: generates event description for a complete away mission objective event and logs event
    public void completeAwayMissionObjectiveEvent(int awayMissionID) {
        logEvent("Mission " + awayMissionID + " objective complete.");
    }

    // EFFECTS: generates event description for an add current away mission to the mission log event and logs event
    public void addCurrentAwayMissionToMissionLogEvent(int awayMissionID) {
        logEvent("Mission " + awayMissionID + " added to the away mission Log.");
    }

    // EFFECTS: generates event description for an add crew member to away team event and logs event
    public void addCrewMemberToAwayTeamEvent(String name) {
        logEvent(name + " added to the away team.");
    }

    // EFFECTS: generates event description for a transport off of starship event and logs event
    public void transportOffOfStarshipEvent(String name) {
        logEvent(name + " transported off of the starship.");
    }
    // EFFECTS: generates event description for a remove crew member from away team event and logs event
    public void removeCrewMemberFromAwayTeamEvent(String name) {
        logEvent(name + " removed from the away team.");
    }

    // EFFECTS: generates event description for a transport to starship event and logs event
    public void transportToStarshipEvent(String name) {
        logEvent(name + " transported to the starship.");
    }
}
