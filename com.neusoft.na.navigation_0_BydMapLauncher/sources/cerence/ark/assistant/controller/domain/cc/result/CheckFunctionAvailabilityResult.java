package cerence.ark.assistant.controller.domain.cc.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CheckFunctionAvailabilityResult extends BaseResult {
    private String checkResult;

    public void setCheckResult(String str) {
        this.checkResult = str;
    }

    public String getCheckResult() {
        return this.checkResult;
    }

    public static CheckFunctionAvailabilityResult fromJson(String str) throws JSONException {
        CheckFunctionAvailabilityResult checkFunctionAvailabilityResult = new CheckFunctionAvailabilityResult();
        checkFunctionAvailabilityResult.checkResult = new JSONObject(str).optString("check_result");
        return checkFunctionAvailabilityResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("check_result", this.checkResult);
        return jSONObject.toString();
    }
}
