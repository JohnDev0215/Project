package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetInteriorLightStateParam implements JsonCompliant {
    private String action;
    private String lightType;
    private String vehicleAreaList;

    public void setAction(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }

    public void setLightType(String str) {
        this.lightType = str;
    }

    public String getLightType() {
        return this.lightType;
    }

    public void setVehicleAreaList(String str) {
        this.vehicleAreaList = str;
    }

    public String getVehicleAreaList() {
        return this.vehicleAreaList;
    }

    public static SetInteriorLightStateParam fromJson(String str) throws JSONException {
        SetInteriorLightStateParam setInteriorLightStateParam = new SetInteriorLightStateParam();
        JSONObject jSONObject = new JSONObject(str);
        setInteriorLightStateParam.action = jSONObject.optString("action");
        setInteriorLightStateParam.lightType = jSONObject.optString("light_type");
        setInteriorLightStateParam.vehicleAreaList = jSONObject.optString("vehicle_area_list");
        return setInteriorLightStateParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("action", this.action);
        jSONObject.put("light_type", this.lightType);
        jSONObject.put("vehicle_area_list", this.vehicleAreaList);
        return jSONObject.toString();
    }
}
