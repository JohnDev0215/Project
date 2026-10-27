package cerence.ark.assistant.controller.domain.system.result;

import androidx.core.app.NotificationCompat;
import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetNetworkStatusResult extends BaseResult {
    private String status;

    public void setStatus(String str) {
        this.status = str;
    }

    public String getStatus() {
        return this.status;
    }

    public static GetNetworkStatusResult fromJson(String str) throws JSONException {
        GetNetworkStatusResult getNetworkStatusResult = new GetNetworkStatusResult();
        getNetworkStatusResult.status = new JSONObject(str).optString(NotificationCompat.CATEGORY_STATUS);
        return getNetworkStatusResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(NotificationCompat.CATEGORY_STATUS, this.status);
        return jSONObject.toString();
    }
}
