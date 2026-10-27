package cerence.ark.assistant.controller.domain.hvac.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IsPowerOnRequiredResult extends BaseResult {
    private boolean isRequired;

    public void setIsRequired(boolean z) {
        this.isRequired = z;
    }

    public boolean getIsRequired() {
        return this.isRequired;
    }

    public static IsPowerOnRequiredResult fromJson(String str) throws JSONException {
        IsPowerOnRequiredResult isPowerOnRequiredResult = new IsPowerOnRequiredResult();
        isPowerOnRequiredResult.isRequired = new JSONObject(str).optBoolean("is_required");
        return isPowerOnRequiredResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_required", this.isRequired);
        return jSONObject.toString();
    }
}
