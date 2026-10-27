package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ControlDrivingModeParam implements JsonCompliant {
    private String drivingMode;
    private String state;

    public void setDrivingMode(String str) {
        this.drivingMode = str;
    }

    public String getDrivingMode() {
        return this.drivingMode;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String getState() {
        return this.state;
    }

    public static ControlDrivingModeParam fromJson(String str) throws JSONException {
        ControlDrivingModeParam controlDrivingModeParam = new ControlDrivingModeParam();
        JSONObject jSONObject = new JSONObject(str);
        controlDrivingModeParam.drivingMode = jSONObject.optString("drivingMode");
        controlDrivingModeParam.state = jSONObject.optString("state");
        return controlDrivingModeParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("drivingMode", this.drivingMode);
        jSONObject.put("state", this.state);
        return jSONObject.toString();
    }
}
