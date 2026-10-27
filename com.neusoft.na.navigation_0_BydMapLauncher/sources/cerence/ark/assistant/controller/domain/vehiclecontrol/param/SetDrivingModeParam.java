package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SetDrivingModeParam implements JsonCompliant {
    private String drivingMode;

    public void setDrivingMode(String str) {
        this.drivingMode = str;
    }

    public String getDrivingMode() {
        return this.drivingMode;
    }

    public static SetDrivingModeParam fromJson(String str) throws JSONException {
        SetDrivingModeParam setDrivingModeParam = new SetDrivingModeParam();
        setDrivingModeParam.drivingMode = new JSONObject(str).optString("driving_mode");
        return setDrivingModeParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("driving_mode", this.drivingMode);
        return jSONObject.toString();
    }
}
