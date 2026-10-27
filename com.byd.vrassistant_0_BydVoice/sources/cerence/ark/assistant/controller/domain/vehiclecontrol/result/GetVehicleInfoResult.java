package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.VehicleInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetVehicleInfoResult extends BaseResult {
    private VehicleInfo vehicleInfo;

    public void setVehicleInfo(VehicleInfo vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    public VehicleInfo getVehicleInfo() {
        return this.vehicleInfo;
    }

    public static GetVehicleInfoResult fromJson(String str) throws JSONException {
        GetVehicleInfoResult getVehicleInfoResult = new GetVehicleInfoResult();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("vehicle_info");
        if (jSONObjectOptJSONObject != null) {
            getVehicleInfoResult.vehicleInfo = VehicleInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return getVehicleInfoResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.vehicleInfo != null) {
            jSONObject.put("vehicle_info", new JSONObject(this.vehicleInfo.toJsonString()));
        }
        return jSONObject.toString();
    }
}
