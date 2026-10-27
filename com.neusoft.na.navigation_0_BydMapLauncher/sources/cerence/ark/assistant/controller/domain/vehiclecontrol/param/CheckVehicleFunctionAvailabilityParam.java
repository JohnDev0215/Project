package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CheckVehicleFunctionAvailabilityParam implements JsonCompliant {
    private String vehicleFunction;

    public void setVehicleFunction(String str) {
        this.vehicleFunction = str;
    }

    public String getVehicleFunction() {
        return this.vehicleFunction;
    }

    public static CheckVehicleFunctionAvailabilityParam fromJson(String str) throws JSONException {
        CheckVehicleFunctionAvailabilityParam checkVehicleFunctionAvailabilityParam = new CheckVehicleFunctionAvailabilityParam();
        checkVehicleFunctionAvailabilityParam.vehicleFunction = new JSONObject(str).optString("vehicle_function");
        return checkVehicleFunctionAvailabilityParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("vehicle_function", this.vehicleFunction);
        return jSONObject.toString();
    }
}
