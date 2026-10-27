package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IsNavigatingResult extends BaseResult {
    private boolean isNavigating;

    public void setIsNavigating(boolean z) {
        this.isNavigating = z;
    }

    public boolean getIsNavigating() {
        return this.isNavigating;
    }

    public static IsNavigatingResult fromJson(String str) throws JSONException {
        IsNavigatingResult isNavigatingResult = new IsNavigatingResult();
        isNavigatingResult.isNavigating = new JSONObject(str).optBoolean("is_navigating");
        return isNavigatingResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_navigating", this.isNavigating);
        return jSONObject.toString();
    }
}
