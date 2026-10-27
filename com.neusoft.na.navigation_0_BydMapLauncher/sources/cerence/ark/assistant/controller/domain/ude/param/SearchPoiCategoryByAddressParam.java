package cerence.ark.assistant.controller.domain.ude.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SearchPoiCategoryByAddressParam implements JsonCompliant {
    private String category;
    private String locationReference;
    private String poiName;

    public void setCategory(String str) {
        this.category = str;
    }

    public String getCategory() {
        return this.category;
    }

    public void setLocationReference(String str) {
        this.locationReference = str;
    }

    public String getLocationReference() {
        return this.locationReference;
    }

    public void setPoiName(String str) {
        this.poiName = str;
    }

    public String getPoiName() {
        return this.poiName;
    }

    public static SearchPoiCategoryByAddressParam fromJson(String str) throws JSONException {
        SearchPoiCategoryByAddressParam searchPoiCategoryByAddressParam = new SearchPoiCategoryByAddressParam();
        JSONObject jSONObject = new JSONObject(str);
        searchPoiCategoryByAddressParam.category = jSONObject.optString("category");
        searchPoiCategoryByAddressParam.locationReference = jSONObject.optString("location_reference");
        searchPoiCategoryByAddressParam.poiName = jSONObject.optString("poi_name");
        return searchPoiCategoryByAddressParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("category", this.category);
        jSONObject.put("location_reference", this.locationReference);
        jSONObject.put("poi_name", this.poiName);
        return jSONObject.toString();
    }
}
