package cerence.ark.assistant.controller.domain.cc.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class OpenAppParam implements JsonCompliant {
    private String appName;

    public void setAppName(String str) {
        this.appName = str;
    }

    public String getAppName() {
        return this.appName;
    }

    public static OpenAppParam fromJson(String str) throws JSONException {
        OpenAppParam openAppParam = new OpenAppParam();
        openAppParam.appName = new JSONObject(str).optString("app_name");
        return openAppParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("app_name", this.appName);
        return jSONObject.toString();
    }
}
