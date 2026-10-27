package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetInteriorLightStateParam implements JsonCompliant {
    private String lightType;
    private String vehicleArea;

    public void setLightType(String str) {
        this.lightType = str;
    }

    public String getLightType() {
        return this.lightType;
    }

    public void setVehicleArea(String str) {
        this.vehicleArea = str;
    }

    public String getVehicleArea() {
        return this.vehicleArea;
    }

    public static GetInteriorLightStateParam fromJson(String str) throws JSONException {
        GetInteriorLightStateParam getInteriorLightStateParam = new GetInteriorLightStateParam();
        JSONObject jSONObject = new JSONObject(str);
        getInteriorLightStateParam.lightType = jSONObject.optString("light_type");
        getInteriorLightStateParam.vehicleArea = jSONObject.optString("vehicle_area");
        return getInteriorLightStateParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("light_type", this.lightType);
        jSONObject.put("vehicle_area", this.vehicleArea);
        return jSONObject.toString();
    }
}
