package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class QueryRemainingMileageResult extends BaseResult {
    private int remainingMileage;
    private String unit;

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

    public static QueryRemainingMileageResult fromJson(String str) throws JSONException {
        QueryRemainingMileageResult queryRemainingMileageResult = new QueryRemainingMileageResult();
        JSONObject jSONObject = new JSONObject(str);
        queryRemainingMileageResult.remainingMileage = jSONObject.optInt("remaining_mileage");
        queryRemainingMileageResult.unit = jSONObject.optString("unit");
        return queryRemainingMileageResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("remaining_mileage", this.remainingMileage);
        jSONObject.put("unit", this.unit);
        return jSONObject.toString();
    }
}
