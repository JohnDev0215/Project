package com.amazon.alexa.voice.core.processor.superbowl;

import android.util.ArrayMap;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class DirectiveFactory {
    private final ArrayMap<String, Factory> factories = new ArrayMap<>();

    public interface Factory<T extends SuperbowlDirective> {
        T create(JSONObject jSONObject, ContentProvider contentProvider) throws JSONException;
    }

    private String createKey(String str, String str2) {
        return str + ':' + str2;
    }

    public void add(String str, String str2, Factory factory) {
        Preconditions.notNull(str, "namespace == null");
        Preconditions.notNull(str2, "name == null");
        Preconditions.notNull(factory, "factory == null");
        this.factories.put(createKey(str, str2), factory);
    }

    public SuperbowlDirective create(JSONObject jSONObject, ContentProvider contentProvider) throws JSONException {
        Preconditions.notNull(jSONObject, "json == null");
        Preconditions.notNull(contentProvider, "contentProvider == null");
        JSONObject jSONObject2 = jSONObject.getJSONObject("directive");
        JSONObject jSONObject3 = jSONObject2.getJSONObject("header");
        Factory factory = this.factories.get(createKey(jSONObject3.getString("namespace"), jSONObject3.getString("name")));
        if (factory == null) {
            return null;
        }
        return factory.create(jSONObject2, contentProvider);
    }
}
