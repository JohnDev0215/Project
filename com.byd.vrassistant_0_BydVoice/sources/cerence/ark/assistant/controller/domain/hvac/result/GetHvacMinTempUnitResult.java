package cerence.ark.assistant.controller.domain.hvac.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetHvacMinTempUnitResult extends BaseResult {
    private String tempUnit;

    public void setTempUnit(String str) {
        this.tempUnit = str;
    }

    public String getTempUnit() {
        return this.tempUnit;
    }

    public static GetHvacMinTempUnitResult fromJson(String str) throws JSONException {
        GetHvacMinTempUnitResult getHvacMinTempUnitResult = new GetHvacMinTempUnitResult();
        getHvacMinTempUnitResult.tempUnit = new JSONObject(str).optString("tempUnit");
        return getHvacMinTempUnitResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("tempUnit", this.tempUnit);
        return jSONObject.toString();
    }
}
