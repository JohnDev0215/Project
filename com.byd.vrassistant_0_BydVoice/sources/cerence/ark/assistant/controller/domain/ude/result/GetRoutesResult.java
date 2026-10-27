package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.RouteInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetRoutesResult extends BaseResult {
    private List<RouteInfo> routeList;

    public void setRouteList(List<RouteInfo> list) {
        this.routeList = list;
    }

    public List<RouteInfo> getRouteList() {
        return this.routeList;
    }

    public static GetRoutesResult fromJson(String str) throws JSONException {
        GetRoutesResult getRoutesResult = new GetRoutesResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("route_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(RouteInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        getRoutesResult.routeList = arrayList;
        return getRoutesResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.routeList != null) {
            for (int i = 0; i < this.routeList.size(); i++) {
                jSONArray.put(new JSONObject(this.routeList.get(i).toJsonString()));
            }
        }
        jSONObject.put("route_list", jSONArray);
        return jSONObject.toString();
    }
}
