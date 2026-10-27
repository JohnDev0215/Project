package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.controller.struct.RouteOptionInfo;
import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class NaviRouteOptionListParam implements JsonCompliant {
    private List<RouteOptionInfo> routeOptionList;

    public void setRouteOptionList(List<RouteOptionInfo> list) {
        this.routeOptionList = list;
    }

    public List<RouteOptionInfo> getRouteOptionList() {
        return this.routeOptionList;
    }

    public static NaviRouteOptionListParam fromJson(String str) throws JSONException {
        NaviRouteOptionListParam naviRouteOptionListParam = new NaviRouteOptionListParam();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("route_option_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(RouteOptionInfo.fromJson(jSONArrayOptJSONArray.get(i).toString()));
            }
        }
        naviRouteOptionListParam.routeOptionList = arrayList;
        return naviRouteOptionListParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
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
