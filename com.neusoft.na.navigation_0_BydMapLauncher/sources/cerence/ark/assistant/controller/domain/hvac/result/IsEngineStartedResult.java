package cerence.ark.assistant.controller.domain.hvac.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IsEngineStartedResult extends BaseResult {
    private boolean isStarted;

    public void setIsStarted(boolean z) {
        this.isStarted = z;
    }

    public boolean getIsStarted() {
        return this.isStarted;
    }

    public static IsEngineStartedResult fromJson(String str) throws JSONException {
        IsEngineStartedResult isEngineStartedResult = new IsEngineStartedResult();
        isEngineStartedResult.isStarted = new JSONObject(str).optBoolean("is_started");
        return isEngineStartedResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_started", this.isStarted);
        return jSONObject.toString();
    }
}
