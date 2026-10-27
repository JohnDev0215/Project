package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CheckVehicleStatusParam implements JsonCompliant {
    private String checkType;

    public void setCheckType(String str) {
        this.checkType = str;
    }

    public String getCheckType() {
        return this.checkType;
    }

    public static CheckVehicleStatusParam fromJson(String str) throws JSONException {
        CheckVehicleStatusParam checkVehicleStatusParam = new CheckVehicleStatusParam();
        checkVehicleStatusParam.checkType = new JSONObject(str).optString("check_type");
        return checkVehicleStatusParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("check_type", this.checkType);
        return jSONObject.toString();
    }
}
