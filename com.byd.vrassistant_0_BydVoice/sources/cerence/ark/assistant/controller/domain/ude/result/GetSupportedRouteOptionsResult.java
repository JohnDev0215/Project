package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.RouteOptionInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetSupportedRouteOptionsResult extends BaseResult {
    private List<RouteOptionInfo> routeOptionList;

    public void setRouteOptionList(List<RouteOptionInfo> list) {
        this.routeOptionList = list;
    }

    public List<RouteOptionInfo> getRouteOptionList() {
        return this.routeOptionList;
    }

    public static GetSupportedRouteOptionsResult fromJson(String str) throws JSONException {
        GetSupportedRouteOptionsResult getSupportedRouteOptionsResult = new GetSupportedRouteOptionsResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("route_option_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(RouteOptionInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        getSupportedRouteOptionsResult.routeOptionList = arrayList;
        return getSupportedRouteOptionsResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.routeOptionList != null) {
            for (int i = 0; i < this.routeOptionList.size(); i++) {
                jSONArray.put(new JSONObject(this.routeOptionList.get(i).toJsonString()));
            }
        }
        jSONObject.put("route_option_list", jSONArray);
        return jSONObject.toString();
    }
}
