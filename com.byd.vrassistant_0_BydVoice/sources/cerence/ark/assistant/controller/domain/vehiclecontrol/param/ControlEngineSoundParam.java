package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ControlEngineSoundParam implements JsonCompliant {
    private String action;

    public void setAction(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }

    public static ControlEngineSoundParam fromJson(String str) throws JSONException {
        ControlEngineSoundParam controlEngineSoundParam = new ControlEngineSoundParam();
        controlEngineSoundParam.action = new JSONObject(str).optString("action");
        return controlEngineSoundParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("action", this.action);
        return jSONObject.toString();
    }
}
