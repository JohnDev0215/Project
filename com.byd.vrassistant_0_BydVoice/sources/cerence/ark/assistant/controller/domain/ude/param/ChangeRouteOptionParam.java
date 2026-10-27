package cerence.ark.assistant.controller.domain.ude.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ChangeRouteOptionParam implements JsonCompliant {
    private String routeOption;

    public void setRouteOption(String str) {
        this.routeOption = str;
    }

    public String getRouteOption() {
        return this.routeOption;
    }

    public static ChangeRouteOptionParam fromJson(String str) throws JSONException {
        ChangeRouteOptionParam changeRouteOptionParam = new ChangeRouteOptionParam();
        changeRouteOptionParam.routeOption = new JSONObject(str).optString("route_option");
        return changeRouteOptionParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("route_option", this.routeOption);
        return jSONObject.toString();
    }
}
