package cerence.ark.assistant.controller.domain.phone.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IsPhoneConnectedResult extends BaseResult {
    private boolean isConnected;

    public void setIsConnected(boolean z) {
        this.isConnected = z;
    }

    public boolean getIsConnected() {
        return this.isConnected;
    }

    public static IsPhoneConnectedResult fromJson(String str) throws JSONException {
        IsPhoneConnectedResult isPhoneConnectedResult = new IsPhoneConnectedResult();
        isPhoneConnectedResult.isConnected = new JSONObject(str).optBoolean("is_connected");
        return isPhoneConnectedResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_connected", this.isConnected);
        return jSONObject.toString();
    }
}
