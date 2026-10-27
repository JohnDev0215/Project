package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IncomingCallEvent extends AppEvent {
    private IncomingCallEvent(Builder builder) {
        super("INCOMING_CALL");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("contact_name", builder.contactName);
            jSONObject.put("number", builder.number);
            setJsonData(jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static class Builder {
        private String contactName;
        private String number;

        public Builder setContactName(String str) {
            this.contactName = str;
            return this;
        }

        public Builder setNumber(String str) {
            this.number = str;
            return this;
        }

        public IncomingCallEvent build() {
            return new IncomingCallEvent(this);
        }
    }
}
