package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.PlaceInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SearchPlaceByCategoryResult extends BaseResult {
    private List<PlaceInfo> placeList;

    public void setPlaceList(List<PlaceInfo> list) {
        this.placeList = list;
    }

    public List<PlaceInfo> getPlaceList() {
        return this.placeList;
    }

    public static SearchPlaceByCategoryResult fromJson(String str) throws JSONException {
        SearchPlaceByCategoryResult searchPlaceByCategoryResult = new SearchPlaceByCategoryResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("place_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(PlaceInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        searchPlaceByCategoryResult.placeList = arrayList;
        return searchPlaceByCategoryResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.placeList != null) {
            for (int i = 0; i < this.placeList.size(); i++) {
                jSONArray.put(new JSONObject(this.placeList.get(i).toJsonString()));
            }
        }
        jSONObject.put("place_list", jSONArray);
        return jSONObject.toString();
    }
}
