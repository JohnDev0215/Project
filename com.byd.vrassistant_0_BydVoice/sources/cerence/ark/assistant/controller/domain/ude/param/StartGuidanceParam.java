package cerence.ark.assistant.controller.domain.ude.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class StartGuidanceParam implements JsonCompliant {
    private String routeId;

    public void setRouteId(String str) {
        this.routeId = str;
    }

    public String getRouteId() {
        return this.routeId;
    }

    public static StartGuidanceParam fromJson(String str) throws JSONException {
        StartGuidanceParam startGuidanceParam = new StartGuidanceParam();
        startGuidanceParam.routeId = new JSONObject(str).optString("route_id");
        return startGuidanceParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("route_id", this.routeId);
        return jSONObject.toString();
    }
}
