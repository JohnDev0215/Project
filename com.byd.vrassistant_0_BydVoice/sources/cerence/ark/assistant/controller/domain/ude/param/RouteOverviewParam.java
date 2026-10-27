package cerence.ark.assistant.controller.domain.ude.param;

import cerence.ark.assistant.controller.struct.RouteInfo;
import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class RouteOverviewParam implements JsonCompliant {
    private int highlightIndex;
    private List<RouteInfo> routeList;

    public void setRouteList(List<RouteInfo> list) {
        this.routeList = list;
    }

    public List<RouteInfo> getRouteList() {
        return this.routeList;
    }

    public void setHighlightIndex(int i) {
        this.highlightIndex = i;
    }

    public int getHighlightIndex() {
        return this.highlightIndex;
    }

    public static RouteOverviewParam fromJson(String str) throws JSONException {
        RouteOverviewParam routeOverviewParam = new RouteOverviewParam();
        JSONObject jSONObject = new JSONObject(str);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("route_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(RouteInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        routeOverviewParam.routeList = arrayList;
        routeOverviewParam.highlightIndex = jSONObject.optInt("highlight_index");
        return routeOverviewParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.routeList != null) {
            for (int i = 0; i < this.routeList.size(); i++) {
                jSONArray.put(new JSONObject(this.routeList.get(i).toJsonString()));
            }
        }
        jSONObject.put("route_list", jSONArray);
        jSONObject.put("highlight_index", this.highlightIndex);
        return jSONObject.toString();
    }
}
