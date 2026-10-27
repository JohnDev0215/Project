package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.PlaceInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetRecentListResult extends BaseResult {
    private List<PlaceInfo> recentList;

    public void setRecentList(List<PlaceInfo> list) {
        this.recentList = list;
    }

    public List<PlaceInfo> getRecentList() {
        return this.recentList;
    }

    public static GetRecentListResult fromJson(String str) throws JSONException {
        GetRecentListResult getRecentListResult = new GetRecentListResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("recent_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(PlaceInfo.fromJson(jSONArrayOptJSONArray.get(i).toString()));
            }
        }
        getRecentListResult.recentList = arrayList;
        return getRecentListResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
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
