package cerence.ark.assistant.controller.domain.ude.param;

import cerence.ark.assistant.controller.struct.PlaceInfo;
import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class NavigateToDestinationParam implements JsonCompliant {
    private List<PlaceInfo> avoidancePoints;
    private PlaceInfo destination;
    private String routeOption;
    private List<PlaceInfo> wayPoints;

    public void setDestination(PlaceInfo placeInfo) {
        this.destination = placeInfo;
    }

    public PlaceInfo getDestination() {
        return this.destination;
    }

    public void setRouteOption(String str) {
        this.routeOption = str;
    }

    public String getRouteOption() {
        return this.routeOption;
    }

    public void setWayPoints(List<PlaceInfo> list) {
        this.wayPoints = list;
    }

    public List<PlaceInfo> getWayPoints() {
        return this.wayPoints;
    }

    public void setAvoidancePoints(List<PlaceInfo> list) {
        this.avoidancePoints = list;
    }

    public List<PlaceInfo> getAvoidancePoints() {
        return this.avoidancePoints;
    }

    public static NavigateToDestinationParam fromJson(String str) throws JSONException {
        NavigateToDestinationParam navigateToDestinationParam = new NavigateToDestinationParam();
        JSONObject jSONObject = new JSONObject(str);
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("destination");
        if (jSONObjectOptJSONObject != null) {
            navigateToDestinationParam.destination = PlaceInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        navigateToDestinationParam.routeOption = jSONObject.optString("route_option");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("way_points");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(PlaceInfo.fromJson(jSONArrayOptJSONArray.get(i).toString()));
            }
        }
        navigateToDestinationParam.wayPoints = arrayList;
        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray("avoidance_points");
        ArrayList arrayList2 = new ArrayList();
        if (jSONArrayOptJSONArray2 != null) {
            for (int i2 = 0; i2 < jSONArrayOptJSONArray2.length(); i2++) {
                arrayList2.add(PlaceInfo.fromJson(jSONArrayOptJSONArray2.get(i2).toString()));
            }
        }
        navigateToDestinationParam.avoidancePoints = arrayList2;
        return navigateToDestinationParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.destination != null) {
            jSONObject.put("destination", new JSONObject(this.destination.toJsonString()));
        }
        jSONObject.put("route_option", this.routeOption);
        JSONArray jSONArray = new JSONArray();
        if (this.wayPoints != null) {
            for (int i = 0; i < this.wayPoints.size(); i++) {
                jSONArray.put(new JSONObject(this.wayPoints.get(i).toJsonString()));
            }
        }
        jSONObject.put("way_points", jSONArray);
        JSONArray jSONArray2 = new JSONArray();
        if (this.avoidancePoints != null) {
            for (int i2 = 0; i2 < this.avoidancePoints.size(); i2++) {
                jSONArray2.put(new JSONObject(this.avoidancePoints.get(i2).toJsonString()));
            }
        }
        jSONObject.put("avoidance_points", jSONArray2);
        return jSONObject.toString();
    }
}
