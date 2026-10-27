package cerence.ark.assistant.controller.domain.system.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetNetworkStatusResult extends BaseResult {
    private boolean isNetworkConnected;

    public void setIsNetworkConnected(boolean z) {
        this.isNetworkConnected = z;
    }

    public boolean getIsNetworkConnected() {
        return this.isNetworkConnected;
    }

    public static GetNetworkStatusResult fromJson(String str) throws JSONException {
        GetNetworkStatusResult getNetworkStatusResult = new GetNetworkStatusResult();
        getNetworkStatusResult.isNetworkConnected = new JSONObject(str).optBoolean("is_network_connected");
        return getNetworkStatusResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_network_connected", this.isNetworkConnected);
        return jSONObject.toString();
    }
}
