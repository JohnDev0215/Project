package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class IsDetailPageSupportedResult extends BaseResult {
    private boolean isSupported;

    public void setIsSupported(boolean z) {
        this.isSupported = z;
    }

    public boolean getIsSupported() {
        return this.isSupported;
    }

    public static IsDetailPageSupportedResult fromJson(String str) throws JSONException {
        IsDetailPageSupportedResult isDetailPageSupportedResult = new IsDetailPageSupportedResult();
        isDetailPageSupportedResult.isSupported = new JSONObject(str).optBoolean("is_supported");
        return isDetailPageSupportedResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_supported", this.isSupported);
        return jSONObject.toString();
    }
}
