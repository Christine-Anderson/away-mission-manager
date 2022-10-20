package persistance;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

// Represents a reader that reads starship from JSON data stored in file
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
        addAwayMission(starship, jsonObject);

        return starship;
    }

    // MODIFIES: starship
    // EFFECTS: parses thingies from JSON object and adds them to workroom
    private void addThingies(WorkRoom wr, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("thingies");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addThingy(wr, nextThingy);
        }
    }

    // MODIFIES: starship
    // EFFECTS: parses crew member from JSON object and adds it to starship //TODO
    private void addCrewMember(Starship starship, JSONObject jsonObject) {
        String firstName = jsonObject.getString("firstName");
        String lastName = jsonObject.getString("lastName");
        Rank rank = Rank.valueOf(jsonObject.getString("rank"));
        Division division = Division.valueOf(jsonObject.getString("division"));
        HealthStatus healthStatus = HealthStatus.valueOf(jsonObject.getString("division"));
        boolean hasRedShirt = Boolean.parseBoolean(jsonObject.getString("hasRedShirt"));
        boolean hasPlotArmour = Boolean.parseBoolean(jsonObject.getString("hasPlotArmour"));
        boolean isOnStarship = Boolean.parseBoolean(jsonObject.getString("isOnStarship"));

        CrewMember crewMember = new CrewMember(firstName, lastName, rank, division, healthStatus, hasRedShirt,
                hasPlotArmour, isOnStarship);

        // use these to add to crew and away team
//        Thingy thingy = new Thingy(name, category);
//        wr.addThingy(thingy);
    }
}
