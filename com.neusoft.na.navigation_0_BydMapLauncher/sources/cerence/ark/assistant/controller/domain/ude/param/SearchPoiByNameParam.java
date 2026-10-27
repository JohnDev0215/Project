package cerence.ark.assistant.controller.domain.ude.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SearchPoiByNameParam implements JsonCompliant {
    private String poiName;

    public void setPoiName(String str) {
        this.poiName = str;
    }

    public String getPoiName() {
        return this.poiName;
    }

    public static SearchPoiByNameParam fromJson(String str) throws JSONException {
        SearchPoiByNameParam searchPoiByNameParam = new SearchPoiByNameParam();
        searchPoiByNameParam.poiName = new JSONObject(str).optString("poi_name");
        return searchPoiByNameParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("poi_name", this.poiName);
        return jSONObject.toString();
    }
}
