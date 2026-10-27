package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CheckNaviFunctionAvailabilityResult extends BaseResult {
    private String checkResult;

    public void setCheckResult(String str) {
        this.checkResult = str;
    }

    public String getCheckResult() {
        return this.checkResult;
    }

    public static CheckNaviFunctionAvailabilityResult fromJson(String str) throws JSONException {
        CheckNaviFunctionAvailabilityResult checkNaviFunctionAvailabilityResult = new CheckNaviFunctionAvailabilityResult();
        checkNaviFunctionAvailabilityResult.checkResult = new JSONObject(str).optString("check_result");
        return checkNaviFunctionAvailabilityResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("check_result", this.checkResult);
        return jSONObject.toString();
    }
}
