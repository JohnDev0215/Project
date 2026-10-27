package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CheckVehicleStatusResult extends BaseResult {
    private String status;

    public void setStatus(String str) {
        this.status = str;
    }

    public String getStatus() {
        return this.status;
    }

    public static CheckVehicleStatusResult fromJson(String str) throws JSONException {
        CheckVehicleStatusResult checkVehicleStatusResult = new CheckVehicleStatusResult();
        checkVehicleStatusResult.status = new JSONObject(str).optString("status");
        return checkVehicleStatusResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("status", this.status);
        return jSONObject.toString();
    }
}
