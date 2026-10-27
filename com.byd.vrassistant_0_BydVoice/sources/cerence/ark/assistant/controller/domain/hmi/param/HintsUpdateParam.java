package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class HintsUpdateParam implements JsonCompliant {
    private List<String> hintsList;

    public void setHintsList(List<String> list) {
        this.hintsList = list;
    }

    public List<String> getHintsList() {
        return this.hintsList;
    }

    public static HintsUpdateParam fromJson(String str) throws JSONException {
        HintsUpdateParam hintsUpdateParam = new HintsUpdateParam();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("hints_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(jSONArrayOptJSONArray.optString(i));
            }
        }
        hintsUpdateParam.hintsList = arrayList;
        return hintsUpdateParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.hintsList != null) {
            for (int i = 0; i < this.hintsList.size(); i++) {
                jSONArray.put(this.hintsList.get(i));
            }
        }
        jSONObject.put("hints_list", jSONArray);
        return jSONObject.toString();
    }
}
