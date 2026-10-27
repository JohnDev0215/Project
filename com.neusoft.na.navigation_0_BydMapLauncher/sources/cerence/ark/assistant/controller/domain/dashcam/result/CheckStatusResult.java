package cerence.ark.assistant.controller.domain.dashcam.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CheckStatusResult extends BaseResult {
    private boolean isMalfunctional;

    public void setIsMalfunctional(boolean z) {
        this.isMalfunctional = z;
    }

    public boolean getIsMalfunctional() {
        return this.isMalfunctional;
    }

    public static CheckStatusResult fromJson(String str) throws JSONException {
        CheckStatusResult checkStatusResult = new CheckStatusResult();
        checkStatusResult.isMalfunctional = new JSONObject(str).optBoolean("is_malfunctional");
        return checkStatusResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_malfunctional", this.isMalfunctional);
        return jSONObject.toString();
    }
}
