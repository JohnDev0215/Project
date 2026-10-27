package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.PlaceInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetAvoidPointsResult extends BaseResult {
    private List<PlaceInfo> avoidPointsList;

    public void setAvoidPointsList(List<PlaceInfo> list) {
        this.avoidPointsList = list;
    }

    public List<PlaceInfo> getAvoidPointsList() {
        return this.avoidPointsList;
    }

    public static GetAvoidPointsResult fromJson(String str) throws JSONException {
        GetAvoidPointsResult getAvoidPointsResult = new GetAvoidPointsResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("avoid_points_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(PlaceInfo.fromJson(jSONArrayOptJSONArray.get(i).toString()));
            }
        }
        getAvoidPointsResult.avoidPointsList = arrayList;
        return getAvoidPointsResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.avoidPointsList != null) {
            for (int i = 0; i < this.avoidPointsList.size(); i++) {
                jSONArray.put(new JSONObject(this.avoidPointsList.get(i).toJsonString()));
            }
        }
        jSONObject.put("avoid_points_list", jSONArray);
        return jSONObject.toString();
    }
}
