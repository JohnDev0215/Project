package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.controller.struct.ContactInfo;
import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class PhoneContactDisambigListParam implements JsonCompliant {
    private List<ContactInfo> contactList;

    public void setContactList(List<ContactInfo> list) {
        this.contactList = list;
    }

    public List<ContactInfo> getContactList() {
        return this.contactList;
    }

    public static PhoneContactDisambigListParam fromJson(String str) throws JSONException {
        PhoneContactDisambigListParam phoneContactDisambigListParam = new PhoneContactDisambigListParam();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("contact_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(ContactInfo.fromJson(jSONArrayOptJSONArray.get(i).toString()));
            }
        }
        phoneContactDisambigListParam.contactList = arrayList;
        return phoneContactDisambigListParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.contactList != null) {
            for (int i = 0; i < this.contactList.size(); i++) {
                jSONArray.put(new JSONObject(this.contactList.get(i).toJsonString()));
            }
        }
        jSONObject.put("contact_list", jSONArray);
        return jSONObject.toString();
    }
}
