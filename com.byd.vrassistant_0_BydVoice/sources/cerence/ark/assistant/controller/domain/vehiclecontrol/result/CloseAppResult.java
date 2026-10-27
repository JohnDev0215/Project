package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CloseAppResult extends BaseResult {
    private String appName;

    public void setAppName(String str) {
        this.appName = str;
    }

    public String getAppName() {
        return this.appName;
    }

    public static CloseAppResult fromJson(String str) throws JSONException {
        CloseAppResult closeAppResult = new CloseAppResult();
        closeAppResult.appName = new JSONObject(str).optString("app_name");
        return closeAppResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("app_name", this.appName);
        return jSONObject.toString();
    }
}
