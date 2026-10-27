package cerence.ark.assistant.controller.domain.phone.param;

import androidx.core.app.NotificationCompat;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetServiceNumberParam implements JsonCompliant {
    private String service;

    public void setService(String str) {
        this.service = str;
    }

    public String getService() {
        return this.service;
    }

    public static GetServiceNumberParam fromJson(String str) throws JSONException {
        GetServiceNumberParam getServiceNumberParam = new GetServiceNumberParam();
        getServiceNumberParam.service = new JSONObject(str).optString(NotificationCompat.CATEGORY_SERVICE);
        return getServiceNumberParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(NotificationCompat.CATEGORY_SERVICE, this.service);
        return jSONObject.toString();
    }
}
