package cerence.ark.assistant.core;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public interface JsonCompliant {
    default JSONObject toJSONObject() throws JSONException {
        return null;
    }

    String toJsonString() throws JSONException;
}
