package cerence.ark.assistant.controller.domain.phone.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class IsContactListEmptyResult extends BaseResult {
    private boolean isEmpty;

    public void setIsEmpty(boolean z) {
        this.isEmpty = z;
    }

    public boolean getIsEmpty() {
        return this.isEmpty;
    }

    public static IsContactListEmptyResult fromJson(String str) throws JSONException {
        IsContactListEmptyResult isContactListEmptyResult = new IsContactListEmptyResult();
        isContactListEmptyResult.isEmpty = new JSONObject(str).optBoolean("is_empty");
        return isContactListEmptyResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_empty", this.isEmpty);
        return jSONObject.toString();
    }
}
