package cerence.ark.assistant.controller.domain.ude.param;

import cerence.ark.assistant.controller.struct.PlaceInfo;
import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetRoutesParam implements JsonCompliant {
    private PlaceInfo destination;
    private String routeOption;
    private List<PlaceInfo> wayPoints;

    public void setDestination(PlaceInfo placeInfo) {
        this.destination = placeInfo;
    }

    public PlaceInfo getDestination() {
        return this.destination;
    }

    public void setWayPoints(List<PlaceInfo> list) {
        this.wayPoints = list;
    }

    public List<PlaceInfo> getWayPoints() {
        return this.wayPoints;
    }

    public void setRouteOption(String str) {
        this.routeOption = str;
    }

    public String getRouteOption() {
        return this.routeOption;
    }

    public static GetRoutesParam fromJson(String str) throws JSONException {
        GetRoutesParam getRoutesParam = new GetRoutesParam();
        JSONObject jSONObject = new JSONObject(str);
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("destination");
        if (jSONObjectOptJSONObject != null) {
            getRoutesParam.destination = PlaceInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("way_points");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(PlaceInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        getRoutesParam.wayPoints = arrayList;
        getRoutesParam.routeOption = jSONObject.optString("route_option");
        return getRoutesParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.destination != null) {
            jSONObject.put("destination", new JSONObject(this.destination.toJsonString()));
        }
        JSONArray jSONArray = new JSONArray();
        if (this.wayPoints != null) {
            for (int i = 0; i < this.wayPoints.size(); i++) {
                jSONArray.put(new JSONObject(this.wayPoints.get(i).toJsonString()));
            }
        }
        jSONObject.put("way_points", jSONArray);
        jSONObject.put("route_option", this.routeOption);
        return jSONObject.toString();
    }
}
