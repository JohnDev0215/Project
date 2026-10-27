package cerence.ark.assistant.controller.domain.phone.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IsEmergencyNumberParam implements JsonCompliant {
    private String number;

    public void setNumber(String str) {
        this.number = str;
    }

    public String getNumber() {
        return this.number;
    }

    public static IsEmergencyNumberParam fromJson(String str) throws JSONException {
        IsEmergencyNumberParam isEmergencyNumberParam = new IsEmergencyNumberParam();
        isEmergencyNumberParam.number = new JSONObject(str).optString("number");
        return isEmergencyNumberParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("number", this.number);
        return jSONObject.toString();
    }
}
