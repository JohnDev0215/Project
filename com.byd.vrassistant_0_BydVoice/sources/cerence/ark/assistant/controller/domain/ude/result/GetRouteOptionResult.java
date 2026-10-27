package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetRouteOptionResult extends BaseResult {
    private String routeOption;

    public void setRouteOption(String str) {
        this.routeOption = str;
    }

    public String getRouteOption() {
        return this.routeOption;
    }

    public static GetRouteOptionResult fromJson(String str) throws JSONException {
        GetRouteOptionResult getRouteOptionResult = new GetRouteOptionResult();
        getRouteOptionResult.routeOption = new JSONObject(str).optString("route_option");
        return getRouteOptionResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("route_option", this.routeOption);
        return jSONObject.toString();
    }
}
