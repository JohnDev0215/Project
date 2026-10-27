package cerence.ark.assistant.controller.domain.ude.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CheckNaviFunctionAvailabilityParam implements JsonCompliant {
    private String naviFunction;

    public void setNaviFunction(String str) {
        this.naviFunction = str;
    }

    public String getNaviFunction() {
        return this.naviFunction;
    }

    public static CheckNaviFunctionAvailabilityParam fromJson(String str) throws JSONException {
        CheckNaviFunctionAvailabilityParam checkNaviFunctionAvailabilityParam = new CheckNaviFunctionAvailabilityParam();
        checkNaviFunctionAvailabilityParam.naviFunction = new JSONObject(str).optString("navi_function");
        return checkNaviFunctionAvailabilityParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("navi_function", this.naviFunction);
        return jSONObject.toString();
    }
}
