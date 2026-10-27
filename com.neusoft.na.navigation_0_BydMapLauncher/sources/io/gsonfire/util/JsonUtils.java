package io.gsonfire.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class JsonUtils {
    private JsonUtils() {
    }

    public static JsonElement deepCopy(JsonElement jsonElement) {
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = new JsonObject();
            for (Map.Entry<String, JsonElement> entry : jsonElement.getAsJsonObject().entrySet()) {
                jsonObject.add(entry.getKey(), deepCopy(entry.getValue()));
            }
            return jsonObject;
        }
        if (!jsonElement.isJsonArray()) {
            return (jsonElement.isJsonPrimitive() || jsonElement.isJsonNull()) ? jsonElement : JsonNull.INSTANCE;
        }
        JsonArray jsonArray = new JsonArray();
        Iterator<JsonElement> it = jsonElement.getAsJsonArray().iterator();
        while (it.hasNext()) {
            jsonArray.add(it.next());
        }
        return jsonArray;
    }
}
