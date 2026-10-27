package cerence.ark.assistant.controller.domain.audiocontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class IsBTConnectedResult extends BaseResult {
    private boolean isConnected;

    public void setIsConnected(boolean z) {
        this.isConnected = z;
    }

    public boolean getIsConnected() {
        return this.isConnected;
    }

    public static IsBTConnectedResult fromJson(String str) throws JSONException {
        IsBTConnectedResult isBTConnectedResult = new IsBTConnectedResult();
        isBTConnectedResult.isConnected = new JSONObject(str).optBoolean("is_connected");
        return isBTConnectedResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_connected", this.isConnected);
        return jSONObject.toString();
    }
}
