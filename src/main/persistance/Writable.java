package persistance;

import org.json.JSONObject;

/**
 * An interface for objects that are writable to JSON Object
 */
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
