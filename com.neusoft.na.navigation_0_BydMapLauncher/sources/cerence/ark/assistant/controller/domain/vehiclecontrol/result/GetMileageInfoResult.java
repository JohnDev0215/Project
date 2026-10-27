package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetMileageInfoResult extends BaseResult {
    private int mileage;
    private String unit;

    public void setMileage(int i) {
        this.mileage = i;
    }

    public int getMileage() {
        return this.mileage;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public String getUnit() {
        return this.unit;
    }

    public static GetMileageInfoResult fromJson(String str) throws JSONException {
        GetMileageInfoResult getMileageInfoResult = new GetMileageInfoResult();
        JSONObject jSONObject = new JSONObject(str);
        getMileageInfoResult.mileage = jSONObject.optInt("mileage");
        getMileageInfoResult.unit = jSONObject.optString("unit");
        return getMileageInfoResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("mileage", this.mileage);
        jSONObject.put("unit", this.unit);
        return jSONObject.toString();
    }
}
