package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Represents a reader that reads starship from JSON data stored in file
 */
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads starship from file and returns it
    //          throws IOException if an error occurs reading data from file
    public Starship read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseStarship(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses starship from JSON object and returns it
    private Starship parseStarship(JSONObject jsonObject) {
        String firstNameOfCaptain = jsonObject.getString("firstNameOfCaptain");
        String lastNameOfCaptain = jsonObject.getString("lastNameOfCaptain");
        int currentStardate = Integer.parseInt(jsonObject.getString("currentStardate"));
        int awayMissionID = Integer.parseInt(jsonObject.getString("awayMissionID"));

        Starship starship = new Starship(firstNameOfCaptain, lastNameOfCaptain, currentStardate, awayMissionID);

        addCrewMembers(starship, jsonObject);
        addMissionLog(starship, jsonObject);
        addCurrentAwayMission(starship, jsonObject);

        return starship;
    }

    // MODIFIES: starship
    // EFFECTS: parses current crew members from JSON object and adds them to the starship
    private void addCrewMembers(Starship starship, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("crewMembers");
        for (Object json : jsonArray) {
            JSONObject nextCrewMember = (JSONObject) json;
            addCrewMember(starship, nextCrewMember);
        }
    }

    // MODIFIES: starship
    // EFFECTS: adds crew member from json object to current crew on starship
    private void addCrewMember(Starship starship, JSONObject jsonObject) {
        CrewMember crewMember = parseCrewMember(jsonObject);
        starship.addCrewMember(crewMember);
    }

    // EFFECTS: parses crew member from JSON object and returns it
    private CrewMember parseCrewMember(JSONObject jsonObject) {
        String firstName = jsonObject.getString("firstName");
        String lastName = jsonObject.getString("lastName");
        Rank rank = Rank.valueOf(jsonObject.getString("rank"));
        Division division = Division.valueOf(jsonObject.getString("division"));
        HealthStatus healthStatus = HealthStatus.valueOf(jsonObject.getString("healthStatus"));
        boolean hasRedShirt = jsonObject.getBoolean("hasRedShirt");
        boolean hasPlotArmour = jsonObject.getBoolean("hasPlotArmour");
        boolean isOnStarship = jsonObject.getBoolean("isOnStarship");

        return new CrewMember(firstName, lastName, rank, division, healthStatus, hasRedShirt,
                hasPlotArmour, isOnStarship);
    }

    // MODIFIES: starship
    // EFFECTS: parses mission log from JSON object and adds previous missions to the starship
    private void addMissionLog(Starship starship, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("missionLog");
        for (Object json : jsonArray) {
            JSONObject nextAwayMission = (JSONObject) json;
            addAwayMission(starship, nextAwayMission);
        }
    }

    // MODIFIES: starship
    // EFFECTS: adds away mission from json object to mission log on starship
    private void addAwayMission(Starship starship, JSONObject jsonObject) {
        AwayMission awayMission = parseAwayMission(jsonObject);
        addAwayTeam(awayMission, jsonObject);

        starship.getMissionLog().add(awayMission);
    }

    // MODIFIES: starship
    // EFFECTS: adds away mission from json object as current away mission on starship
    private void addCurrentAwayMission(Starship starship, JSONObject jsonObject) {
        if (jsonObject.isNull("currentAwayMission")) {
            starship.setCurrentAwayMission(null);
        } else {
            JSONObject jsonObjectSubset = (JSONObject) jsonObject.get("currentAwayMission");
            AwayMission awayMission = parseAwayMission(jsonObjectSubset);
            addAwayTeam(awayMission, jsonObjectSubset);

            starship.setCurrentAwayMission(awayMission);
        }
    }

    // EFFECTS: parses away mission from json object and returns it
    private AwayMission parseAwayMission(JSONObject jsonObject) {
        int awayMissionID = Integer.parseInt(jsonObject.getString("awayMissionID"));
        int stardate = Integer.parseInt(jsonObject.getString("stardate"));
        boolean isActive = jsonObject.getBoolean("isActive");
        boolean isObjectiveComplete = jsonObject.getBoolean("isObjectiveComplete");

        return new AwayMission(awayMissionID, stardate, isActive, isObjectiveComplete);
    }

    // MODIFIES: starship
    // EFFECTS: parses away team members from JSON object and adds them to the away mission
    private void addAwayTeam(AwayMission awayMission, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("awayTeam");
        for (Object json : jsonArray) {
            JSONObject nextAwayTeamMember = (JSONObject) json;
            addAwayTeamMember(awayMission, nextAwayTeamMember);
        }
    }

    // MODIFIES: starship
    // EFFECTS: adds away team member from json object to away team on away mission
    private void addAwayTeamMember(AwayMission awayMission, JSONObject jsonObject) {
        CrewMember crewMember = parseCrewMember(jsonObject);
        awayMission.getAwayTeam().add(crewMember);
    }

}
