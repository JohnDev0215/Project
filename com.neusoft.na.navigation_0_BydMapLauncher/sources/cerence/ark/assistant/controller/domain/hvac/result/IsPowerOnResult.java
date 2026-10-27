package cerence.ark.assistant.controller.domain.hvac.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IsPowerOnResult extends BaseResult {
    private boolean isOn;

    public void setIsOn(boolean z) {
        this.isOn = z;
    }

    public boolean getIsOn() {
        return this.isOn;
    }

    public static IsPowerOnResult fromJson(String str) throws JSONException {
        IsPowerOnResult isPowerOnResult = new IsPowerOnResult();
        isPowerOnResult.isOn = new JSONObject(str).optBoolean("is_on");
        return isPowerOnResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_on", this.isOn);
        return jSONObject.toString();
    }
}
