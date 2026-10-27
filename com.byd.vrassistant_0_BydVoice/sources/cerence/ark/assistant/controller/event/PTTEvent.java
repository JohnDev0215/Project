package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class PTTEvent extends AppEvent {
    private PTTEvent(Builder builder) {
        super("PTT");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("active_zone", builder.activeZone);
            setJsonData(jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static class Builder {
        private String activeZone;

        public Builder setActiveZone(String str) {
            this.activeZone = str;
            return this;
        }

        public PTTEvent build() {
            return new PTTEvent(this);
        }
    }
}
