package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class PhoneNumberInfo implements JsonCompliant {
    private String number;
    private String numberType;

    public void setNumber(String str) {
        this.number = str;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumberType(String str) {
        this.numberType = str;
    }

    public String getNumberType() {
        return this.numberType;
    }

    public static PhoneNumberInfo fromJson(String str) throws JSONException {
        PhoneNumberInfo phoneNumberInfo = new PhoneNumberInfo();
        JSONObject jSONObject = new JSONObject(str);
        phoneNumberInfo.number = jSONObject.optString("number");
        phoneNumberInfo.numberType = jSONObject.optString("number_type");
        return phoneNumberInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("number", this.number);
        jSONObject.put("number_type", this.numberType);
        return jSONObject.toString();
    }
}
