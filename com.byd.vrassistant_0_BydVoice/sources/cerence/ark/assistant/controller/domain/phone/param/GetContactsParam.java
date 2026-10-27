package cerence.ark.assistant.controller.domain.phone.param;

import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetContactsParam implements JsonCompliant {
    private List<Long> contactIds;

    public void setContactIds(List<Long> list) {
        this.contactIds = list;
    }

    public List<Long> getContactIds() {
        return this.contactIds;
    }

    public static GetContactsParam fromJson(String str) throws JSONException {
        GetContactsParam getContactsParam = new GetContactsParam();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("contact_ids");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(Long.valueOf(jSONArrayOptJSONArray.optLong(i)));
            }
        }
        getContactsParam.contactIds = arrayList;
        return getContactsParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.contactIds != null) {
            for (int i = 0; i < this.contactIds.size(); i++) {
                jSONArray.put(this.contactIds.get(i));
            }
        }
        jSONObject.put("contact_ids", jSONArray);
        return jSONObject.toString();
    }
}
