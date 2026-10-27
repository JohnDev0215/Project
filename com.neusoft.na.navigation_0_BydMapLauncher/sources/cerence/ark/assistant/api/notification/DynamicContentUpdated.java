package cerence.ark.assistant.api.notification;

import cerence.ark.assistant.common.Logger;
import cerence.ark.assistant.library.JsonKey;
import cerence.ark.assistant.library.Utils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class DynamicContentUpdated extends AbsNotification {
    private String mDccType;

    public DynamicContentUpdated(String str) {
        super(str);
        Logger.info(TAG, Utils.format("DynamicContentUpdated: %s", str));
    }

    @Override // cerence.ark.assistant.api.notification.AbsNotification
    protected void parseParam(String str) throws JSONException {
        this.mDccType = new JSONObject(str).optString(JsonKey.NOTIFICATION_DCC_TYPE);
    }

    public String getDccType() {
        return this.mDccType;
    }
}
