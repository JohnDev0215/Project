package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.core.JsonCompliant;
import cerence.ark.assistant.library.JsonKey;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class DisplayParam implements JsonCompliant {
    private JSONObject data;
    private String scenario;

    public void setScenario(String str) {
        this.scenario = str;
    }

    public String getScenario() {
        return this.scenario;
    }

    public void setData(JSONObject jSONObject) {
        this.data = jSONObject;
    }

    public JSONObject getData() {
        return this.data;
    }

    public static DisplayParam fromJson(String str) throws JSONException {
        DisplayParam displayParam = new DisplayParam();
        JSONObject jSONObject = new JSONObject(str);
        displayParam.scenario = jSONObject.optString("scenario");
        displayParam.data = jSONObject.optJSONObject(JsonKey.RESULTS_DATA);
        return displayParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("scenario", this.scenario);
        jSONObject.put(JsonKey.RESULTS_DATA, this.data);
        return jSONObject.toString();
    }
}
