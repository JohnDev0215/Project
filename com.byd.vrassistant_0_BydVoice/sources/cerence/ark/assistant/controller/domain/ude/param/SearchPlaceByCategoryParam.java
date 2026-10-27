package cerence.ark.assistant.controller.domain.ude.param;

import cerence.ark.assistant.core.JsonCompliant;
import com.byd.mediacenter.api.media.MediaControl;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SearchPlaceByCategoryParam implements JsonCompliant {
    private String category;
    private String categoryLiteral;
    private String locationReference;
    private String placeName;

    public void setCategory(String str) {
        this.category = str;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategoryLiteral(String str) {
        this.categoryLiteral = str;
    }

    public String getCategoryLiteral() {
        return this.categoryLiteral;
    }

    public void setLocationReference(String str) {
        this.locationReference = str;
    }

    public String getLocationReference() {
        return this.locationReference;
    }

    public void setPlaceName(String str) {
        this.placeName = str;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public static SearchPlaceByCategoryParam fromJson(String str) throws JSONException {
        SearchPlaceByCategoryParam searchPlaceByCategoryParam = new SearchPlaceByCategoryParam();
        JSONObject jSONObject = new JSONObject(str);
        searchPlaceByCategoryParam.category = jSONObject.optString(MediaControl.PARAM_CATEGORY);
        searchPlaceByCategoryParam.categoryLiteral = jSONObject.optString("category_literal");
        searchPlaceByCategoryParam.locationReference = jSONObject.optString("location_reference");
        searchPlaceByCategoryParam.placeName = jSONObject.optString("place_name");
        return searchPlaceByCategoryParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(MediaControl.PARAM_CATEGORY, this.category);
        jSONObject.put("category_literal", this.categoryLiteral);
        jSONObject.put("location_reference", this.locationReference);
        jSONObject.put("place_name", this.placeName);
        return jSONObject.toString();
    }
}
