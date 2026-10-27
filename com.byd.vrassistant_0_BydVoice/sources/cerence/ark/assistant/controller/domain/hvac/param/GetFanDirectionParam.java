package cerence.ark.assistant.controller.domain.hvac.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetFanDirectionParam implements JsonCompliant {
    private String vehicleArea;

    public void setVehicleArea(String str) {
        this.vehicleArea = str;
    }

    public String getVehicleArea() {
        return this.vehicleArea;
    }

    public static GetFanDirectionParam fromJson(String str) throws JSONException {
        GetFanDirectionParam getFanDirectionParam = new GetFanDirectionParam();
        getFanDirectionParam.vehicleArea = new JSONObject(str).optString("vehicle_area");
        return getFanDirectionParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("vehicle_area", this.vehicleArea);
        return jSONObject.toString();
    }
}
