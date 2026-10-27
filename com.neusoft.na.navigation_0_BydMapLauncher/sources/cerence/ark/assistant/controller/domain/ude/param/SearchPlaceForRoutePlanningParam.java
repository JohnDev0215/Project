package cerence.ark.assistant.controller.domain.ude.param;

import cerence.ark.assistant.controller.struct.PlaceInfo;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SearchPlaceForRoutePlanningParam implements JsonCompliant {
    private PlaceInfo destination;
    private String placeName;

    public void setPlaceName(String str) {
        this.placeName = str;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public void setDestination(PlaceInfo placeInfo) {
        this.destination = placeInfo;
    }

    public PlaceInfo getDestination() {
        return this.destination;
    }

    public static SearchPlaceForRoutePlanningParam fromJson(String str) throws JSONException {
        SearchPlaceForRoutePlanningParam searchPlaceForRoutePlanningParam = new SearchPlaceForRoutePlanningParam();
        JSONObject jSONObject = new JSONObject(str);
        searchPlaceForRoutePlanningParam.placeName = jSONObject.optString("place_name");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("destination");
        if (jSONObjectOptJSONObject != null) {
            searchPlaceForRoutePlanningParam.destination = PlaceInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return searchPlaceForRoutePlanningParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("place_name", this.placeName);
        if (this.destination != null) {
            jSONObject.put("destination", new JSONObject(this.destination.toJsonString()));
        }
        return jSONObject.toString();
    }
}
