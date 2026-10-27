package cerence.ark.assistant.controller.domain.phone.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IsSynchronizingContactResult extends BaseResult {
    private boolean isSynchronizing;

    public void setIsSynchronizing(boolean z) {
        this.isSynchronizing = z;
    }

    public boolean getIsSynchronizing() {
        return this.isSynchronizing;
    }

    public static IsSynchronizingContactResult fromJson(String str) throws JSONException {
        IsSynchronizingContactResult isSynchronizingContactResult = new IsSynchronizingContactResult();
        isSynchronizingContactResult.isSynchronizing = new JSONObject(str).optBoolean("is_synchronizing");
        return isSynchronizingContactResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_synchronizing", this.isSynchronizing);
        return jSONObject.toString();
    }
}
