package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CheckDuplicateRoutePlaceResult extends BaseResult {
    private boolean isDuplicate;

    public void setIsDuplicate(boolean z) {
        this.isDuplicate = z;
    }

    public boolean getIsDuplicate() {
        return this.isDuplicate;
    }

    public static CheckDuplicateRoutePlaceResult fromJson(String str) throws JSONException {
        CheckDuplicateRoutePlaceResult checkDuplicateRoutePlaceResult = new CheckDuplicateRoutePlaceResult();
        checkDuplicateRoutePlaceResult.isDuplicate = new JSONObject(str).optBoolean("is_duplicate");
        return checkDuplicateRoutePlaceResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_duplicate", this.isDuplicate);
        return jSONObject.toString();
    }
}
