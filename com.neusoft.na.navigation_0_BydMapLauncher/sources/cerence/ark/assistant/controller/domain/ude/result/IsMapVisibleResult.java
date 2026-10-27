package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IsMapVisibleResult extends BaseResult {
    private boolean isVisible;

    public void setIsVisible(boolean z) {
        this.isVisible = z;
    }

    public boolean getIsVisible() {
        return this.isVisible;
    }

    public static IsMapVisibleResult fromJson(String str) throws JSONException {
        IsMapVisibleResult isMapVisibleResult = new IsMapVisibleResult();
        isMapVisibleResult.isVisible = new JSONObject(str).optBoolean("is_visible");
        return isMapVisibleResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_visible", this.isVisible);
        return jSONObject.toString();
    }
}
