package cerence.ark.assistant.controller.domain.ude.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SearchPlaceByNameParam implements JsonCompliant {
    private String placeName;

    public void setPlaceName(String str) {
        this.placeName = str;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public static SearchPlaceByNameParam fromJson(String str) throws JSONException {
        SearchPlaceByNameParam searchPlaceByNameParam = new SearchPlaceByNameParam();
        searchPlaceByNameParam.placeName = new JSONObject(str).optString("place_name");
        return searchPlaceByNameParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("place_name", this.placeName);
        return jSONObject.toString();
    }
}
