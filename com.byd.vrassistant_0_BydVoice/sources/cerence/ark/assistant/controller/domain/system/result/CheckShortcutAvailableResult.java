package cerence.ark.assistant.controller.domain.system.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CheckShortcutAvailableResult extends BaseResult {
    private boolean isAvailable;

    public void setIsAvailable(boolean z) {
        this.isAvailable = z;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public static CheckShortcutAvailableResult fromJson(String str) throws JSONException {
        CheckShortcutAvailableResult checkShortcutAvailableResult = new CheckShortcutAvailableResult();
        checkShortcutAvailableResult.isAvailable = new JSONObject(str).optBoolean("isAvailable");
        return checkShortcutAvailableResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("isAvailable", this.isAvailable);
        return jSONObject.toString();
    }
}
