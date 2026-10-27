package com.amplifyframework.core.category;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
public abstract class CategoryConfiguration implements CategoryTypeable {
    private static final String PLUGINS_KEY = "plugins";
    private final Map<String, JSONObject> pluginConfigs = new ConcurrentHashMap();

    public final JSONObject getPluginConfig(String str) {
        return this.pluginConfigs.get(str);
    }

    public void populateFromJSON(JSONObject jSONObject) throws JSONException {
        if (jSONObject.has(PLUGINS_KEY)) {
            JSONObject jSONObject2 = jSONObject.getJSONObject(PLUGINS_KEY);
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                this.pluginConfigs.put(next, jSONObject2.getJSONObject(next));
            }
        }
    }
}
