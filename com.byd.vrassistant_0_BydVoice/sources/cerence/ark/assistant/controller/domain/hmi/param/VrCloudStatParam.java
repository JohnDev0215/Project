package cerence.ark.assistant.controller.domain.hmi.param;

import androidx.core.app.NotificationCompat;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class VrCloudStatParam implements JsonCompliant {
    private String status;

    public void setStatus(String str) {
        this.status = str;
    }

    public String getStatus() {
        return this.status;
    }

    public static VrCloudStatParam fromJson(String str) throws JSONException {
        VrCloudStatParam vrCloudStatParam = new VrCloudStatParam();
        vrCloudStatParam.status = new JSONObject(str).optString(NotificationCompat.CATEGORY_STATUS);
        return vrCloudStatParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(NotificationCompat.CATEGORY_STATUS, this.status);
        return jSONObject.toString();
    }
}
