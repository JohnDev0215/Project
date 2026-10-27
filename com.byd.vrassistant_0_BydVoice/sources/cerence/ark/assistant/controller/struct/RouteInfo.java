package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class RouteInfo implements JsonCompliant {
    private double distance;
    private long duration;
    private String id;
    private String label;
    private String routeOption;
    private String unitOfDistance;

    public void setRouteOption(String str) {
        this.routeOption = str;
    }

    public String getRouteOption() {
        return this.routeOption;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getId() {
        return this.id;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public String getLabel() {
        return this.label;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDistance(double d) {
        this.distance = d;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setUnitOfDistance(String str) {
        this.unitOfDistance = str;
    }

    public String getUnitOfDistance() {
        return this.unitOfDistance;
    }

    public static RouteInfo fromJson(String str) throws JSONException {
        RouteInfo routeInfo = new RouteInfo();
        JSONObject jSONObject = new JSONObject(str);
        routeInfo.routeOption = jSONObject.optString("route_option");
        routeInfo.id = jSONObject.optString("id");
        routeInfo.label = jSONObject.optString("label");
        routeInfo.duration = jSONObject.optLong("duration");
        routeInfo.distance = jSONObject.optDouble("distance");
        routeInfo.unitOfDistance = jSONObject.optString("unit_of_distance");
        return routeInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("route_option", this.routeOption);
        jSONObject.put("id", this.id);
        jSONObject.put("label", this.label);
        jSONObject.put("duration", this.duration);
        jSONObject.put("distance", this.distance);
        jSONObject.put("unit_of_distance", this.unitOfDistance);
        return jSONObject.toString();
    }
}
