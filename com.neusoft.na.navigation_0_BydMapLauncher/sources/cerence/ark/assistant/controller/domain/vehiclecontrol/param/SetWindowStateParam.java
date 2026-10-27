package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SetWindowStateParam implements JsonCompliant {
    private int percentage;
    private String vehicleArea;

    public void setPercentage(int i) {
        this.percentage = i;
    }

    public int getPercentage() {
        return this.percentage;
    }

    public void setVehicleArea(String str) {
        this.vehicleArea = str;
    }

    public String getVehicleArea() {
        return this.vehicleArea;
    }

    public static SetWindowStateParam fromJson(String str) throws JSONException {
        SetWindowStateParam setWindowStateParam = new SetWindowStateParam();
        JSONObject jSONObject = new JSONObject(str);
        setWindowStateParam.percentage = jSONObject.optInt("percentage");
        setWindowStateParam.vehicleArea = jSONObject.optString("vehicle_area");
        return setWindowStateParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("percentage", this.percentage);
        jSONObject.put("vehicle_area", this.vehicleArea);
        return jSONObject.toString();
    }
}
