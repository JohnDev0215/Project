package com.amazon.alexa.voice.core.processor.superbowl.events;

import android.util.ArrayMap;
import com.amazon.alexa.voice.core.internal.util.Preconditions;
import com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent;
import com.amazon.music.inappmessaging.internal.model.Splash;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class SettingsUpdatedEvent extends SuperbowlEvent {
    private final ArrayMap<String, String> keyValues;

    private SettingsUpdatedEvent(Builder builder) {
        super(builder);
        this.keyValues = builder.keyValues;
    }

    @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent
    protected JSONObject getPayload() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("settings", getSettingsJson());
        return jSONObject;
    }

    private JSONArray getSettingsJson() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        int size = this.keyValues.size();
        for (int i = 0; i < size; i++) {
            jSONArray.put(new JSONObject().put("key", this.keyValues.keyAt(i)).put("value", this.keyValues.valueAt(i)));
        }
        return jSONArray;
    }

    public static final class Builder extends SuperbowlEvent.Builder<SettingsUpdatedEvent> {
        final ArrayMap<String, String> keyValues;

        public Builder() {
            super("Settings", "SettingsUpdated");
            this.keyValues = new ArrayMap<>();
        }

        public Builder add(String str, String str2) {
            Preconditions.notNull(str, "key == null");
            Preconditions.notNull(str2, "value == null");
            this.keyValues.put(str, str2);
            return this;
        }

        public Builder locale(Locale locale) {
            return add(Splash.PARAMS_LOCALE, locale.getLanguage() + '-' + locale.getCountry());
        }

        @Override // com.amazon.alexa.voice.core.processor.superbowl.SuperbowlEvent.Builder
        public SettingsUpdatedEvent build() {
            return new SettingsUpdatedEvent(this);
        }
    }
}
