package cerence.ark.assistant.controller.domain.system.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CheckPermissionResult extends BaseResult {
    private boolean granted;

    public void setGranted(boolean z) {
        this.granted = z;
    }

    public boolean getGranted() {
        return this.granted;
    }

    public static CheckPermissionResult fromJson(String str) throws JSONException {
        CheckPermissionResult checkPermissionResult = new CheckPermissionResult();
        checkPermissionResult.granted = new JSONObject(str).optBoolean("granted");
        return checkPermissionResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("granted", this.granted);
        return jSONObject.toString();
    }
}
