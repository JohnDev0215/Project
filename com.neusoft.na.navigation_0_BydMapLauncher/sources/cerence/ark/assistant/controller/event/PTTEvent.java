package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class PTTEvent extends AppEvent {
    private PTTEvent(Builder builder) {
        super("PTT");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("is_long_press", builder.isLongPress);
            setJsonData(jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static class Builder {
        private boolean isLongPress;

        public Builder setIsLongPress(boolean z) {
            this.isLongPress = z;
            return this;
        }

        public PTTEvent build() {
            return new PTTEvent(this);
        }
    }
}
