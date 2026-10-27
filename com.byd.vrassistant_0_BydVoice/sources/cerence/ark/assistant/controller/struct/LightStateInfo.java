package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class LightStateInfo implements JsonCompliant {
    private String state;
    private String vehicleArea;

    public void setVehicleArea(String str) {
        this.vehicleArea = str;
    }

    public String getVehicleArea() {
        return this.vehicleArea;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String getState() {
        return this.state;
    }

    public static LightStateInfo fromJson(String str) throws JSONException {
        LightStateInfo lightStateInfo = new LightStateInfo();
        JSONObject jSONObject = new JSONObject(str);
        lightStateInfo.vehicleArea = jSONObject.optString("vehicle_area");
        lightStateInfo.state = jSONObject.optString("state");
        return lightStateInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("vehicle_area", this.vehicleArea);
        jSONObject.put("state", this.state);
        return jSONObject.toString();
    }
}
