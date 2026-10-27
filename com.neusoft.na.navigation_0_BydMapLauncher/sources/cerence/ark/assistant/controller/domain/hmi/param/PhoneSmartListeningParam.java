package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.controller.struct.ContactInfo;
import cerence.ark.assistant.controller.struct.PhoneNumberInfo;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class PhoneSmartListeningParam implements JsonCompliant {
    private ContactInfo contact;
    private PhoneNumberInfo phoneNumber;

    public void setContact(ContactInfo contactInfo) {
        this.contact = contactInfo;
    }

    public ContactInfo getContact() {
        return this.contact;
    }

    public void setPhoneNumber(PhoneNumberInfo phoneNumberInfo) {
        this.phoneNumber = phoneNumberInfo;
    }

    public PhoneNumberInfo getPhoneNumber() {
        return this.phoneNumber;
    }

    public static PhoneSmartListeningParam fromJson(String str) throws JSONException {
        PhoneSmartListeningParam phoneSmartListeningParam = new PhoneSmartListeningParam();
        JSONObject jSONObject = new JSONObject(str);
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("contact");
        if (jSONObjectOptJSONObject != null) {
            phoneSmartListeningParam.contact = ContactInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("phone_number");
        if (jSONObjectOptJSONObject2 != null) {
            phoneSmartListeningParam.phoneNumber = PhoneNumberInfo.fromJson(jSONObjectOptJSONObject2.toString());
        }
        return phoneSmartListeningParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.contact != null) {
            jSONObject.put("contact", new JSONObject(this.contact.toJsonString()));
        }
        if (this.phoneNumber != null) {
            jSONObject.put("phone_number", new JSONObject(this.phoneNumber.toJsonString()));
        }
        return jSONObject.toString();
    }
}
