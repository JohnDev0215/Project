package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class EnableSiriEvent extends AppEvent {
    private EnableSiriEvent(Builder builder) {
        super("ENABLE_SIRI");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("mode", builder.mode);
            setJsonData(jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static class Builder {
        private String mode;

        public Builder setMode(String str) {
            this.mode = str;
            return this;
        }

        public EnableSiriEvent build() {
            return new EnableSiriEvent(this);
        }
    }
}
