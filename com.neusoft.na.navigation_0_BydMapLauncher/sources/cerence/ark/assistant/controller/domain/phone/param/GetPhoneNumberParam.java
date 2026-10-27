package cerence.ark.assistant.controller.domain.phone.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetPhoneNumberParam implements JsonCompliant {
    private long contactId;

    public void setContactId(long j) {
        this.contactId = j;
    }

    public long getContactId() {
        return this.contactId;
    }

    public static GetPhoneNumberParam fromJson(String str) throws JSONException {
        GetPhoneNumberParam getPhoneNumberParam = new GetPhoneNumberParam();
        getPhoneNumberParam.contactId = new JSONObject(str).optLong("contact_id");
        return getPhoneNumberParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("contact_id", this.contactId);
        return jSONObject.toString();
    }
}
