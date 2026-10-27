package cerence.ark.assistant.api.notification;

import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.JsonKey;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class DynamicContentUpdated extends AbsNotification {
    private String mDccType;

    public DynamicContentUpdated(String str, String str2) {
        super(str, str2);
    }

    @Override // cerence.ark.assistant.api.notification.AbsNotification
    protected void parseParam(String str) {
        try {
            this.mDccType = new JSONObject(str).optString(JsonKey.NOTIFICATION_DCC_TYPE);
        } catch (JSONException e) {
            Logger.error(TAG, "createDynamicContentChanged: parse json exception", e);
        }
    }

    public String getDccType() {
        return this.mDccType;
    }
}
