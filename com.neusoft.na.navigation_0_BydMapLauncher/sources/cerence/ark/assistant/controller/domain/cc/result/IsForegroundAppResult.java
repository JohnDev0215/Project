package cerence.ark.assistant.controller.domain.cc.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IsForegroundAppResult extends BaseResult {
    private boolean isForeground;

    public void setIsForeground(boolean z) {
        this.isForeground = z;
    }

    public boolean getIsForeground() {
        return this.isForeground;
    }

    public static IsForegroundAppResult fromJson(String str) throws JSONException {
        IsForegroundAppResult isForegroundAppResult = new IsForegroundAppResult();
        isForegroundAppResult.isForeground = new JSONObject(str).optBoolean("is_foreground");
        return isForegroundAppResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_foreground", this.isForeground);
        return jSONObject.toString();
    }
}
