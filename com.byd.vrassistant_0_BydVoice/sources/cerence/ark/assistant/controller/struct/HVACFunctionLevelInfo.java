package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class HVACFunctionLevelInfo implements JsonCompliant {
    private int level;
    private String vehicleArea;

    public void setVehicleArea(String str) {
        this.vehicleArea = str;
    }

    public String getVehicleArea() {
        return this.vehicleArea;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public int getLevel() {
        return this.level;
    }

    public static HVACFunctionLevelInfo fromJson(String str) throws JSONException {
        HVACFunctionLevelInfo hVACFunctionLevelInfo = new HVACFunctionLevelInfo();
        JSONObject jSONObject = new JSONObject(str);
        hVACFunctionLevelInfo.vehicleArea = jSONObject.optString("vehicle_area");
        hVACFunctionLevelInfo.level = jSONObject.optInt("level");
        return hVACFunctionLevelInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("vehicle_area", this.vehicleArea);
        jSONObject.put("level", this.level);
        return jSONObject.toString();
    }
}
