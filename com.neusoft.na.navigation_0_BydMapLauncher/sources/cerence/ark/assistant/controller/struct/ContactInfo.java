package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ContactInfo implements JsonCompliant {
    private long contactId;
    private String contactName;

    public void setContactId(long j) {
        this.contactId = j;
    }

    public long getContactId() {
        return this.contactId;
    }

    public void setContactName(String str) {
        this.contactName = str;
    }

    public String getContactName() {
        return this.contactName;
    }

    public static ContactInfo fromJson(String str) throws JSONException {
        ContactInfo contactInfo = new ContactInfo();
        JSONObject jSONObject = new JSONObject(str);
        contactInfo.contactId = jSONObject.optLong("contact_id");
        contactInfo.contactName = jSONObject.optString("contact_name");
        return contactInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("contact_id", this.contactId);
        jSONObject.put("contact_name", this.contactName);
        return jSONObject.toString();
    }
}
