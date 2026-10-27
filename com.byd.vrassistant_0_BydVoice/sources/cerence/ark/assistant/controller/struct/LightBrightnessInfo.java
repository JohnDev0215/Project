package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class LightBrightnessInfo implements JsonCompliant {
    private int brightness;
    private String vehicleArea;

    public void setVehicleArea(String str) {
        this.vehicleArea = str;
    }

    public String getVehicleArea() {
        return this.vehicleArea;
    }

    public void setBrightness(int i) {
        this.brightness = i;
    }

    public int getBrightness() {
        return this.brightness;
    }

    public static LightBrightnessInfo fromJson(String str) throws JSONException {
        LightBrightnessInfo lightBrightnessInfo = new LightBrightnessInfo();
        JSONObject jSONObject = new JSONObject(str);
        lightBrightnessInfo.vehicleArea = jSONObject.optString("vehicle_area");
        lightBrightnessInfo.brightness = jSONObject.optInt("brightness");
        return lightBrightnessInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("vehicle_area", this.vehicleArea);
        jSONObject.put("brightness", this.brightness);
        return jSONObject.toString();
    }
}
