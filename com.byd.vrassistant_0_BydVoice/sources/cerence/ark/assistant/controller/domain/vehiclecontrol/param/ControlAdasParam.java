package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ControlAdasParam implements JsonCompliant {
    private String action;
    private String adasType;

    public void setAdasType(String str) {
        this.adasType = str;
    }

    public String getAdasType() {
        return this.adasType;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }

    public static ControlAdasParam fromJson(String str) throws JSONException {
        ControlAdasParam controlAdasParam = new ControlAdasParam();
        JSONObject jSONObject = new JSONObject(str);
        controlAdasParam.adasType = jSONObject.optString("adas_type");
        controlAdasParam.action = jSONObject.optString("action");
        return controlAdasParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("adas_type", this.adasType);
        jSONObject.put("action", this.action);
        return jSONObject.toString();
    }
}
