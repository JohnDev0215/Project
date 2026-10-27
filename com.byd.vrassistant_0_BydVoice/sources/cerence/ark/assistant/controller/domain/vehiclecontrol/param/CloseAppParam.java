package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CloseAppParam implements JsonCompliant {
    private String appName;

    public void setAppName(String str) {
        this.appName = str;
    }

    public String getAppName() {
        return this.appName;
    }

    public static CloseAppParam fromJson(String str) throws JSONException {
        CloseAppParam closeAppParam = new CloseAppParam();
        closeAppParam.appName = new JSONObject(str).optString("app_name");
        return closeAppParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("app_name", this.appName);
        return jSONObject.toString();
    }
}
