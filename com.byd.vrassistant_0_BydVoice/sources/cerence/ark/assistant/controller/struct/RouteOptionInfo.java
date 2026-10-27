package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class RouteOptionInfo implements JsonCompliant {
    private String routeOption;

    public void setRouteOption(String str) {
        this.routeOption = str;
    }

    public String getRouteOption() {
        return this.routeOption;
    }

    public static RouteOptionInfo fromJson(String str) throws JSONException {
        RouteOptionInfo routeOptionInfo = new RouteOptionInfo();
        routeOptionInfo.routeOption = new JSONObject(str).optString("route_option");
        return routeOptionInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("route_option", this.routeOption);
        return jSONObject.toString();
    }
}
