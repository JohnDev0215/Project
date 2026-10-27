package cerence.ark.assistant.controller.domain.hvac.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetHvacStateParam implements JsonCompliant {
    private String action;
    private String hvacFunction;
    private String vehicleArea;

    public void setHvacFunction(String str) {
        this.hvacFunction = str;
    }

    public String getHvacFunction() {
        return this.hvacFunction;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }

    public void setVehicleArea(String str) {
        this.vehicleArea = str;
    }

    public String getVehicleArea() {
        return this.vehicleArea;
    }

    public static SetHvacStateParam fromJson(String str) throws JSONException {
        SetHvacStateParam setHvacStateParam = new SetHvacStateParam();
        JSONObject jSONObject = new JSONObject(str);
        setHvacStateParam.hvacFunction = jSONObject.optString("hvac_function");
        setHvacStateParam.action = jSONObject.optString("action");
        setHvacStateParam.vehicleArea = jSONObject.optString("vehicle_area");
        return setHvacStateParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("hvac_function", this.hvacFunction);
        jSONObject.put("action", this.action);
        jSONObject.put("vehicle_area", this.vehicleArea);
        return jSONObject.toString();
    }
}
