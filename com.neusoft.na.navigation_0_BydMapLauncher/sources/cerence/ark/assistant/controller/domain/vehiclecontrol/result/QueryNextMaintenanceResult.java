package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class QueryNextMaintenanceResult extends BaseResult {
    private int remainingDays;
    private int remainingMileage;
    private String status;
    private String unit;

    public void setStatus(String str) {
        this.status = str;
    }

    public String getStatus() {
        return this.status;
    }

    public void setRemainingDays(int i) {
        this.remainingDays = i;
    }

    public int getRemainingDays() {
        return this.remainingDays;
    }

    public void setRemainingMileage(int i) {
        this.remainingMileage = i;
    }

    public int getRemainingMileage() {
        return this.remainingMileage;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public String getUnit() {
        return this.unit;
    }

    public static QueryNextMaintenanceResult fromJson(String str) throws JSONException {
        QueryNextMaintenanceResult queryNextMaintenanceResult = new QueryNextMaintenanceResult();
        JSONObject jSONObject = new JSONObject(str);
        queryNextMaintenanceResult.status = jSONObject.optString("status");
        queryNextMaintenanceResult.remainingDays = jSONObject.optInt("remaining_days");
        queryNextMaintenanceResult.remainingMileage = jSONObject.optInt("remaining_mileage");
        queryNextMaintenanceResult.unit = jSONObject.optString("unit");
        return queryNextMaintenanceResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("status", this.status);
        jSONObject.put("remaining_days", this.remainingDays);
        jSONObject.put("remaining_mileage", this.remainingMileage);
        jSONObject.put("unit", this.unit);
        return jSONObject.toString();
    }
}
