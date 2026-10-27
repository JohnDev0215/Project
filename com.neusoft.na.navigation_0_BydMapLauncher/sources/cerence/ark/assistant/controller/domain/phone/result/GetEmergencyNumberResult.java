package cerence.ark.assistant.controller.domain.phone.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetEmergencyNumberResult extends BaseResult {
    private String number;

    public void setNumber(String str) {
        this.number = str;
    }

    public String getNumber() {
        return this.number;
    }

    public static GetEmergencyNumberResult fromJson(String str) throws JSONException {
        GetEmergencyNumberResult getEmergencyNumberResult = new GetEmergencyNumberResult();
        getEmergencyNumberResult.number = new JSONObject(str).optString("number");
        return getEmergencyNumberResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("number", this.number);
        return jSONObject.toString();
    }
}
