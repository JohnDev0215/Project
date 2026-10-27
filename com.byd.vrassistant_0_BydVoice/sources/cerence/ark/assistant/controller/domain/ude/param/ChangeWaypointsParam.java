package cerence.ark.assistant.controller.domain.ude.param;

import cerence.ark.assistant.controller.struct.PlaceInfo;
import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ChangeWaypointsParam implements JsonCompliant {
    private String action;
    private List<PlaceInfo> waypoints;

    public void setAction(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }

    public void setWaypoints(List<PlaceInfo> list) {
        this.waypoints = list;
    }

    public List<PlaceInfo> getWaypoints() {
        return this.waypoints;
    }

    public static ChangeWaypointsParam fromJson(String str) throws JSONException {
        ChangeWaypointsParam changeWaypointsParam = new ChangeWaypointsParam();
        JSONObject jSONObject = new JSONObject(str);
        changeWaypointsParam.action = jSONObject.optString("action");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("waypoints");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(PlaceInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        changeWaypointsParam.waypoints = arrayList;
        return changeWaypointsParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("action", this.action);
        JSONArray jSONArray = new JSONArray();
        if (this.waypoints != null) {
            for (int i = 0; i < this.waypoints.size(); i++) {
                jSONArray.put(new JSONObject(this.waypoints.get(i).toJsonString()));
            }
        }
        jSONObject.put("waypoints", jSONArray);
        return jSONObject.toString();
    }
}
