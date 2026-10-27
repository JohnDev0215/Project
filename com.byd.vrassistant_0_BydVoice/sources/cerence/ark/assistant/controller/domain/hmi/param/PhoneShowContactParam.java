package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.controller.struct.ContactInfo;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class PhoneShowContactParam implements JsonCompliant {
    private ContactInfo contact;

    public void setContact(ContactInfo contactInfo) {
        this.contact = contactInfo;
    }

    public ContactInfo getContact() {
        return this.contact;
    }

    public static PhoneShowContactParam fromJson(String str) throws JSONException {
        PhoneShowContactParam phoneShowContactParam = new PhoneShowContactParam();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("contact");
        if (jSONObjectOptJSONObject != null) {
            phoneShowContactParam.contact = ContactInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return phoneShowContactParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.contact != null) {
            jSONObject.put("contact", new JSONObject(this.contact.toJsonString()));
        }
        return jSONObject.toString();
    }
}
