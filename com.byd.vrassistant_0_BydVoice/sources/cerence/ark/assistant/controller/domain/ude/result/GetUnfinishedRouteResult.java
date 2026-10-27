package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.PlaceInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetUnfinishedRouteResult extends BaseResult {
    private PlaceInfo destination;
    private String routeOption;
    private List<PlaceInfo> waypoints;

    public void setDestination(PlaceInfo placeInfo) {
        this.destination = placeInfo;
    }

    public PlaceInfo getDestination() {
        return this.destination;
    }

    public void setWaypoints(List<PlaceInfo> list) {
        this.waypoints = list;
    }

    public List<PlaceInfo> getWaypoints() {
        return this.waypoints;
    }

    public void setRouteOption(String str) {
        this.routeOption = str;
    }

    public String getRouteOption() {
        return this.routeOption;
    }

    public static GetUnfinishedRouteResult fromJson(String str) throws JSONException {
        GetUnfinishedRouteResult getUnfinishedRouteResult = new GetUnfinishedRouteResult();
        JSONObject jSONObject = new JSONObject(str);
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("destination");
        if (jSONObjectOptJSONObject != null) {
            getUnfinishedRouteResult.destination = PlaceInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("waypoints");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(PlaceInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        getUnfinishedRouteResult.waypoints = arrayList;
        getUnfinishedRouteResult.routeOption = jSONObject.optString("route_option");
        return getUnfinishedRouteResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.destination != null) {
            jSONObject.put("destination", new JSONObject(this.destination.toJsonString()));
        }
        JSONArray jSONArray = new JSONArray();
        if (this.waypoints != null) {
            for (int i = 0; i < this.waypoints.size(); i++) {
                jSONArray.put(new JSONObject(this.waypoints.get(i).toJsonString()));
            }
        }
        jSONObject.put("waypoints", jSONArray);
        jSONObject.put("route_option", this.routeOption);
        return jSONObject.toString();
    }
}
