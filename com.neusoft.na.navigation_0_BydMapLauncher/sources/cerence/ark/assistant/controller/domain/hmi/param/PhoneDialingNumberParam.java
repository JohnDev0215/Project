package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class PhoneDialingNumberParam implements JsonCompliant {
    private int highlightEndIndex;
    private int highlightStartIndex;
    private String phoneNumber;

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setHighlightStartIndex(int i) {
        this.highlightStartIndex = i;
    }

    public int getHighlightStartIndex() {
        return this.highlightStartIndex;
    }

    public void setHighlightEndIndex(int i) {
        this.highlightEndIndex = i;
    }

    public int getHighlightEndIndex() {
        return this.highlightEndIndex;
    }

    public static PhoneDialingNumberParam fromJson(String str) throws JSONException {
        PhoneDialingNumberParam phoneDialingNumberParam = new PhoneDialingNumberParam();
        JSONObject jSONObject = new JSONObject(str);
        phoneDialingNumberParam.phoneNumber = jSONObject.optString("phone_number");
        phoneDialingNumberParam.highlightStartIndex = jSONObject.optInt("highlight_start_index");
        phoneDialingNumberParam.highlightEndIndex = jSONObject.optInt("highlight_end_index");
        return phoneDialingNumberParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("phone_number", this.phoneNumber);
        jSONObject.put("highlight_start_index", this.highlightStartIndex);
        jSONObject.put("highlight_end_index", this.highlightEndIndex);
        return jSONObject.toString();
    }
}
