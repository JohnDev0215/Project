package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.bouncycastle.jcajce.util.AnnotatedPrivateKey;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class RouteOptionInfo implements JsonCompliant {
    private String label;
    private String routeOption;

    public void setRouteOption(String str) {
        this.routeOption = str;
    }

    public String getRouteOption() {
        return this.routeOption;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public String getLabel() {
        return this.label;
    }

    public static RouteOptionInfo fromJson(String str) throws JSONException {
        RouteOptionInfo routeOptionInfo = new RouteOptionInfo();
        JSONObject jSONObject = new JSONObject(str);
        routeOptionInfo.routeOption = jSONObject.optString("route_option");
        routeOptionInfo.label = jSONObject.optString(AnnotatedPrivateKey.LABEL);
        return routeOptionInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("route_option", this.routeOption);
        jSONObject.put(AnnotatedPrivateKey.LABEL, this.label);
        return jSONObject.toString();
    }
}
