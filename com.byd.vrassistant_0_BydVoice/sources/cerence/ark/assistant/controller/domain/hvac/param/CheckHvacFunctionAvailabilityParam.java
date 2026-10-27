package cerence.ark.assistant.controller.domain.hvac.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CheckHvacFunctionAvailabilityParam implements JsonCompliant {
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

    public static CheckHvacFunctionAvailabilityParam fromJson(String str) throws JSONException {
        CheckHvacFunctionAvailabilityParam checkHvacFunctionAvailabilityParam = new CheckHvacFunctionAvailabilityParam();
        JSONObject jSONObject = new JSONObject(str);
        checkHvacFunctionAvailabilityParam.hvacFunction = jSONObject.optString("hvac_function");
        checkHvacFunctionAvailabilityParam.vehicleArea = jSONObject.optString("vehicle_area");
        return checkHvacFunctionAvailabilityParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("hvac_function", this.hvacFunction);
        jSONObject.put("vehicle_area", this.vehicleArea);
        return jSONObject.toString();
    }
}
