package cerence.ark.assistant.controller.domain.hvac.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetHvacTypeResult extends BaseResult {
    private String hvacType;

    public void setHvacType(String str) {
        this.hvacType = str;
    }

    public String getHvacType() {
        return this.hvacType;
    }

    public static GetHvacTypeResult fromJson(String str) throws JSONException {
        GetHvacTypeResult getHvacTypeResult = new GetHvacTypeResult();
        getHvacTypeResult.hvacType = new JSONObject(str).optString("hvac_type");
        return getHvacTypeResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("hvac_type", this.hvacType);
        return jSONObject.toString();
    }
}
