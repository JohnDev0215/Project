package cerence.ark.assistant.controller.domain.ude.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetMapOptionParam implements JsonCompliant {
    private String action;
    private String mapOption;

    public void setAction(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }

    public void setMapOption(String str) {
        this.mapOption = str;
    }

    public String getMapOption() {
        return this.mapOption;
    }

    public static SetMapOptionParam fromJson(String str) throws JSONException {
        SetMapOptionParam setMapOptionParam = new SetMapOptionParam();
        JSONObject jSONObject = new JSONObject(str);
        setMapOptionParam.action = jSONObject.optString("action");
        setMapOptionParam.mapOption = jSONObject.optString("map_option");
        return setMapOptionParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("action", this.action);
        jSONObject.put("map_option", this.mapOption);
        return jSONObject.toString();
    }
}
