package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetSnoozingEvent extends AppEvent {
    private SetSnoozingEvent(Builder builder) {
        super("SET_SNOOZING");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("snoozing_duration", builder.snoozingDuration);
            setJsonData(jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static class Builder {
        private int snoozingDuration;

        public Builder setSnoozingDuration(int i) {
            this.snoozingDuration = i;
            return this;
        }

        public SetSnoozingEvent build() {
            return new SetSnoozingEvent(this);
        }
    }
}
