package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class VehicleControlCarInfoParam implements JsonCompliant {
    private String infoType;

    public void setInfoType(String str) {
        this.infoType = str;
    }

    public String getInfoType() {
        return this.infoType;
    }

    public static VehicleControlCarInfoParam fromJson(String str) throws JSONException {
        VehicleControlCarInfoParam vehicleControlCarInfoParam = new VehicleControlCarInfoParam();
        vehicleControlCarInfoParam.infoType = new JSONObject(str).optString("info_type");
        return vehicleControlCarInfoParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("info_type", this.infoType);
        return jSONObject.toString();
    }
}
