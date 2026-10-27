package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IsRainingResult extends BaseResult {
    private String isRaining;

    public void setIsRaining(String str) {
        this.isRaining = str;
    }

    public String getIsRaining() {
        return this.isRaining;
    }

    public static IsRainingResult fromJson(String str) throws JSONException {
        IsRainingResult isRainingResult = new IsRainingResult();
        isRainingResult.isRaining = new JSONObject(str).optString("is_raining");
        return isRainingResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_raining", this.isRaining);
        return jSONObject.toString();
    }
}
