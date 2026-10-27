package cerence.ark.assistant.controller.event;

import cerence.ark.assistant.api.event.AppEvent;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CancelDialogEvent extends AppEvent {
    private CancelDialogEvent(Builder builder) {
        super("CANCEL_DIALOG");
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("is_need_to_play_earcon", builder.isNeedToPlayEarcon);
            setJsonData(jSONObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static class Builder {
        private boolean isNeedToPlayEarcon;

        public Builder setIsNeedToPlayEarcon(boolean z) {
            this.isNeedToPlayEarcon = z;
            return this;
        }

        public CancelDialogEvent build() {
            return new CancelDialogEvent(this);
        }
    }
}
