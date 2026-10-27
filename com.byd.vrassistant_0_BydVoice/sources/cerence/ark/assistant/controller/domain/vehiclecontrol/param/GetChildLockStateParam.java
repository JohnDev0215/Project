package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetChildLockStateParam implements JsonCompliant {
    private String vehicleArea;

    public void setVehicleArea(String str) {
        this.vehicleArea = str;
    }

    public String getVehicleArea() {
        return this.vehicleArea;
    }

    public static GetChildLockStateParam fromJson(String str) throws JSONException {
        GetChildLockStateParam getChildLockStateParam = new GetChildLockStateParam();
        getChildLockStateParam.vehicleArea = new JSONObject(str).optString("vehicle_area");
        return getChildLockStateParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("vehicle_area", this.vehicleArea);
        return jSONObject.toString();
    }
}
