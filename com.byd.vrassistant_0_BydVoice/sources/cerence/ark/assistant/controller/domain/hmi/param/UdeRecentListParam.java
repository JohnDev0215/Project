package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.controller.struct.PlaceInfo;
import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class UdeRecentListParam implements JsonCompliant {
    private List<PlaceInfo> recentList;

    public void setRecentList(List<PlaceInfo> list) {
        this.recentList = list;
    }

    public List<PlaceInfo> getRecentList() {
        return this.recentList;
    }

    public static UdeRecentListParam fromJson(String str) throws JSONException {
        UdeRecentListParam udeRecentListParam = new UdeRecentListParam();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("recent_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(PlaceInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        udeRecentListParam.recentList = arrayList;
        return udeRecentListParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.recentList != null) {
            for (int i = 0; i < this.recentList.size(); i++) {
                jSONArray.put(new JSONObject(this.recentList.get(i).toJsonString()));
            }
        }
        jSONObject.put("recent_list", jSONArray);
        return jSONObject.toString();
    }
}
