package cerence.ark.assistant.controller.domain.hvac.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetHavcLevelParam implements JsonCompliant {
    private String hvacFunction;
    private String vehicleArea;

    public void setHvacFunction(String str) {
        this.hvacFunction = str;
    }

    public String getHvacFunction() {
        return this.hvacFunction;
    }

    public void setVehicleArea(String str) {
        this.vehicleArea = str;
    }

    public String getVehicleArea() {
        return this.vehicleArea;
    }

    public static GetHavcLevelParam fromJson(String str) throws JSONException {
        GetHavcLevelParam getHavcLevelParam = new GetHavcLevelParam();
        JSONObject jSONObject = new JSONObject(str);
        getHavcLevelParam.hvacFunction = jSONObject.optString("hvac_function");
        getHavcLevelParam.vehicleArea = jSONObject.optString("vehicle_area");
        return getHavcLevelParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("hvac_function", this.hvacFunction);
        jSONObject.put("vehicle_area", this.vehicleArea);
        return jSONObject.toString();
    }
}
