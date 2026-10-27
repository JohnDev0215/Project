package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.PlaceInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetWaypointsResult extends BaseResult {
    private List<PlaceInfo> wayPointsList;

    public void setWayPointsList(List<PlaceInfo> list) {
        this.wayPointsList = list;
    }

    public List<PlaceInfo> getWayPointsList() {
        return this.wayPointsList;
    }

    public static GetWaypointsResult fromJson(String str) throws JSONException {
        GetWaypointsResult getWaypointsResult = new GetWaypointsResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("way_points_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(PlaceInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        getWaypointsResult.wayPointsList = arrayList;
        return getWaypointsResult;
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
