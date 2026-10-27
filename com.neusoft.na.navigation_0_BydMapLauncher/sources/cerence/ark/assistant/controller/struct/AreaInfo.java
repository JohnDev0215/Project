package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class AreaInfo implements JsonCompliant {
    private int percentage;
    private String vehicleArea;

    public void setVehicleArea(String str) {
        this.vehicleArea = str;
    }

    public String getVehicleArea() {
        return this.vehicleArea;
    }

    public void setPercentage(int i) {
        this.percentage = i;
    }

    public int getPercentage() {
        return this.percentage;
    }

    public static AreaInfo fromJson(String str) throws JSONException {
        AreaInfo areaInfo = new AreaInfo();
        JSONObject jSONObject = new JSONObject(str);
        areaInfo.vehicleArea = jSONObject.optString("vehicle_area");
        areaInfo.percentage = jSONObject.optInt("percentage");
        return areaInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("vehicle_area", this.vehicleArea);
        jSONObject.put("percentage", this.percentage);
        return jSONObject.toString();
    }
}
