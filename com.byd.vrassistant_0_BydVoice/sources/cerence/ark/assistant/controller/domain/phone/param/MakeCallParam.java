package cerence.ark.assistant.controller.domain.phone.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class MakeCallParam implements JsonCompliant {
    private String phoneNumber;

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public static MakeCallParam fromJson(String str) throws JSONException {
        MakeCallParam makeCallParam = new MakeCallParam();
        makeCallParam.phoneNumber = new JSONObject(str).optString("phone_number");
        return makeCallParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("phone_number", this.phoneNumber);
        return jSONObject.toString();
    }
}
