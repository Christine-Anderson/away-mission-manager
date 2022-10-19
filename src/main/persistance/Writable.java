package persistance;

import org.json.JSONObject;

// Based on code from JsonSerializationDemo provided in class
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
