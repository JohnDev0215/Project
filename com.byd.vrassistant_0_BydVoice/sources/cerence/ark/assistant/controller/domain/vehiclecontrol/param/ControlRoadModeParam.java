package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ControlRoadModeParam implements JsonCompliant {
    private String roadMode;
    private String state;

    public void setRoadMode(String str) {
        this.roadMode = str;
    }

    public String getRoadMode() {
        return this.roadMode;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String getState() {
        return this.state;
    }

    public static ControlRoadModeParam fromJson(String str) throws JSONException {
        ControlRoadModeParam controlRoadModeParam = new ControlRoadModeParam();
        JSONObject jSONObject = new JSONObject(str);
        controlRoadModeParam.roadMode = jSONObject.optString("roadMode");
        controlRoadModeParam.state = jSONObject.optString("state");
        return controlRoadModeParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("roadMode", this.roadMode);
        jSONObject.put("state", this.state);
        return jSONObject.toString();
    }
}
