package cerence.ark.assistant.controller.domain.ude.param;

import cerence.ark.assistant.controller.struct.PlaceInfo;
import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CheckDuplicateRoutePlaceParam implements JsonCompliant {
    private PlaceInfo place;
    private List<PlaceInfo> routePlaceList;

    public void setRoutePlaceList(List<PlaceInfo> list) {
        this.routePlaceList = list;
    }

    public List<PlaceInfo> getRoutePlaceList() {
        return this.routePlaceList;
    }

    public void setPlace(PlaceInfo placeInfo) {
        this.place = placeInfo;
    }

    public PlaceInfo getPlace() {
        return this.place;
    }

    public static CheckDuplicateRoutePlaceParam fromJson(String str) throws JSONException {
        CheckDuplicateRoutePlaceParam checkDuplicateRoutePlaceParam = new CheckDuplicateRoutePlaceParam();
        JSONObject jSONObject = new JSONObject(str);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("route_place_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(PlaceInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        checkDuplicateRoutePlaceParam.routePlaceList = arrayList;
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("place");
        if (jSONObjectOptJSONObject != null) {
            checkDuplicateRoutePlaceParam.place = PlaceInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return checkDuplicateRoutePlaceParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.routePlaceList != null) {
            for (int i = 0; i < this.routePlaceList.size(); i++) {
                jSONArray.put(new JSONObject(this.routePlaceList.get(i).toJsonString()));
            }
        }
        jSONObject.put("route_place_list", jSONArray);
        if (this.place != null) {
            jSONObject.put("place", new JSONObject(this.place.toJsonString()));
        }
        return jSONObject.toString();
    }
}
