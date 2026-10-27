package cerence.ark.assistant.controller.domain.music.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IsFavoriteListFullResult extends BaseResult {
    private boolean isFull;

    public void setIsFull(boolean z) {
        this.isFull = z;
    }

    public boolean getIsFull() {
        return this.isFull;
    }

    public static IsFavoriteListFullResult fromJson(String str) throws JSONException {
        IsFavoriteListFullResult isFavoriteListFullResult = new IsFavoriteListFullResult();
        isFavoriteListFullResult.isFull = new JSONObject(str).optBoolean("is_full");
        return isFavoriteListFullResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_full", this.isFull);
        return jSONObject.toString();
    }
}
