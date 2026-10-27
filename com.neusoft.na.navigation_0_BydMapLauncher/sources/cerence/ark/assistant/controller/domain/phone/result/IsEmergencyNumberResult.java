package cerence.ark.assistant.controller.domain.phone.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IsEmergencyNumberResult extends BaseResult {
    private boolean isEmergency;

    public void setIsEmergency(boolean z) {
        this.isEmergency = z;
    }

    public boolean getIsEmergency() {
        return this.isEmergency;
    }

    public static IsEmergencyNumberResult fromJson(String str) throws JSONException {
        IsEmergencyNumberResult isEmergencyNumberResult = new IsEmergencyNumberResult();
        isEmergencyNumberResult.isEmergency = new JSONObject(str).optBoolean("is_emergency");
        return isEmergencyNumberResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_emergency", this.isEmergency);
        return jSONObject.toString();
    }
}
