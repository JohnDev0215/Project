package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CallLogInfo implements JsonCompliant {
    private ContactInfo contact;
    private PhoneNumberInfo phoneNumber;
    private long timestamp;

    public void setContact(ContactInfo contactInfo) {
        this.contact = contactInfo;
    }

    public ContactInfo getContact() {
        return this.contact;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setPhoneNumber(PhoneNumberInfo phoneNumberInfo) {
        this.phoneNumber = phoneNumberInfo;
    }

    public PhoneNumberInfo getPhoneNumber() {
        return this.phoneNumber;
    }

    public static CallLogInfo fromJson(String str) throws JSONException {
        CallLogInfo callLogInfo = new CallLogInfo();
        JSONObject jSONObject = new JSONObject(str);
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("contact");
        if (jSONObjectOptJSONObject != null) {
            callLogInfo.contact = ContactInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        callLogInfo.timestamp = jSONObject.optLong("timestamp");
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("phone_number");
        if (jSONObjectOptJSONObject2 != null) {
            callLogInfo.phoneNumber = PhoneNumberInfo.fromJson(jSONObjectOptJSONObject2.toString());
        }
        return callLogInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.contact != null) {
            jSONObject.put("contact", new JSONObject(this.contact.toJsonString()));
        }
        jSONObject.put("timestamp", this.timestamp);
        if (this.phoneNumber != null) {
            jSONObject.put("phone_number", new JSONObject(this.phoneNumber.toJsonString()));
        }
        return jSONObject.toString();
    }
}
