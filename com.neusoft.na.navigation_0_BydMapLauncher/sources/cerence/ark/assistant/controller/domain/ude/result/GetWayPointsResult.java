package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.PlaceInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetWayPointsResult extends BaseResult {
    private List<PlaceInfo> wayPointsList;

    public void setWayPointsList(List<PlaceInfo> list) {
        this.wayPointsList = list;
    }

    public List<PlaceInfo> getWayPointsList() {
        return this.wayPointsList;
    }

    public static GetWayPointsResult fromJson(String str) throws JSONException {
        GetWayPointsResult getWayPointsResult = new GetWayPointsResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("way_points_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(PlaceInfo.fromJson(jSONArrayOptJSONArray.get(i).toString()));
            }
        }
        getWayPointsResult.wayPointsList = arrayList;
        return getWayPointsResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.wayPointsList != null) {
            for (int i = 0; i < this.wayPointsList.size(); i++) {
                jSONArray.put(new JSONObject(this.wayPointsList.get(i).toJsonString()));
            }
        }
        jSONObject.put("way_points_list", jSONArray);
        return jSONObject.toString();
    }
}
