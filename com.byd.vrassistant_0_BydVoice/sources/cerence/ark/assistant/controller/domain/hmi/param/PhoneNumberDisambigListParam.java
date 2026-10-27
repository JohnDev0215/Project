package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.controller.struct.ContactInfo;
import cerence.ark.assistant.controller.struct.PhoneNumberInfo;
import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class PhoneNumberDisambigListParam implements JsonCompliant {
    private ContactInfo contact;
    private List<PhoneNumberInfo> phoneNumberList;

    public void setContact(ContactInfo contactInfo) {
        this.contact = contactInfo;
    }

    public ContactInfo getContact() {
        return this.contact;
    }

    public void setPhoneNumberList(List<PhoneNumberInfo> list) {
        this.phoneNumberList = list;
    }

    public List<PhoneNumberInfo> getPhoneNumberList() {
        return this.phoneNumberList;
    }

    public static PhoneNumberDisambigListParam fromJson(String str) throws JSONException {
        PhoneNumberDisambigListParam phoneNumberDisambigListParam = new PhoneNumberDisambigListParam();
        JSONObject jSONObject = new JSONObject(str);
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("contact");
        if (jSONObjectOptJSONObject != null) {
            phoneNumberDisambigListParam.contact = ContactInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("phone_number_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(PhoneNumberInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        phoneNumberDisambigListParam.phoneNumberList = arrayList;
        return phoneNumberDisambigListParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.contact != null) {
            jSONObject.put("contact", new JSONObject(this.contact.toJsonString()));
        }
        JSONArray jSONArray = new JSONArray();
        if (this.phoneNumberList != null) {
            for (int i = 0; i < this.phoneNumberList.size(); i++) {
                jSONArray.put(new JSONObject(this.phoneNumberList.get(i).toJsonString()));
            }
        }
        jSONObject.put("phone_number_list", jSONArray);
        return jSONObject.toString();
    }
}
