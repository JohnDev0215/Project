package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.PlaceInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SearchPoiCategoryByAddressResult extends BaseResult {
    private List<PlaceInfo> poiList;

    public void setPoiList(List<PlaceInfo> list) {
        this.poiList = list;
    }

    public List<PlaceInfo> getPoiList() {
        return this.poiList;
    }

    public static SearchPoiCategoryByAddressResult fromJson(String str) throws JSONException {
        SearchPoiCategoryByAddressResult searchPoiCategoryByAddressResult = new SearchPoiCategoryByAddressResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("poi_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(PlaceInfo.fromJson(jSONArrayOptJSONArray.get(i).toString()));
            }
        }
        searchPoiCategoryByAddressResult.poiList = arrayList;
        return searchPoiCategoryByAddressResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.poiList != null) {
            for (int i = 0; i < this.poiList.size(); i++) {
                jSONArray.put(new JSONObject(this.poiList.get(i).toJsonString()));
            }
        }
        jSONObject.put("poi_list", jSONArray);
        return jSONObject.toString();
    }
}
