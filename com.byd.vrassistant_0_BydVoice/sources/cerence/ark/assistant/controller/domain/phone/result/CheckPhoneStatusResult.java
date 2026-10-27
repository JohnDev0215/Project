package cerence.ark.assistant.controller.domain.phone.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CheckPhoneStatusResult extends BaseResult {
    private String phoneStatus;

    public void setPhoneStatus(String str) {
        this.phoneStatus = str;
    }

    public String getPhoneStatus() {
        return this.phoneStatus;
    }

    public static CheckPhoneStatusResult fromJson(String str) throws JSONException {
        CheckPhoneStatusResult checkPhoneStatusResult = new CheckPhoneStatusResult();
        checkPhoneStatusResult.phoneStatus = new JSONObject(str).optString("phone_status");
        return checkPhoneStatusResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("phone_status", this.phoneStatus);
        return jSONObject.toString();
    }
}
