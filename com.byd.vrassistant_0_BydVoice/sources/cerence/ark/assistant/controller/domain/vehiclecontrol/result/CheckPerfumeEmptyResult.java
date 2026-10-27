package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CheckPerfumeEmptyResult extends BaseResult {
    private String isPerfumeEmpty;

    public void setIsPerfumeEmpty(String str) {
        this.isPerfumeEmpty = str;
    }

    public String getIsPerfumeEmpty() {
        return this.isPerfumeEmpty;
    }

    public static CheckPerfumeEmptyResult fromJson(String str) throws JSONException {
        CheckPerfumeEmptyResult checkPerfumeEmptyResult = new CheckPerfumeEmptyResult();
        checkPerfumeEmptyResult.isPerfumeEmpty = new JSONObject(str).optString("is_perfume_empty");
        return checkPerfumeEmptyResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_perfume_empty", this.isPerfumeEmpty);
        return jSONObject.toString();
    }
}
