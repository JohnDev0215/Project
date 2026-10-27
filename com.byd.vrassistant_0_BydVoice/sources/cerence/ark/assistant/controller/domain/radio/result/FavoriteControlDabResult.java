package cerence.ark.assistant.controller.domain.radio.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class FavoriteControlDabResult extends BaseResult {
    private String dabTitle;

    public void setDabTitle(String str) {
        this.dabTitle = str;
    }

    public String getDabTitle() {
        return this.dabTitle;
    }

    public static FavoriteControlDabResult fromJson(String str) throws JSONException {
        FavoriteControlDabResult favoriteControlDabResult = new FavoriteControlDabResult();
        favoriteControlDabResult.dabTitle = new JSONObject(str).optString("dab_title");
        return favoriteControlDabResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("dab_title", this.dabTitle);
        return jSONObject.toString();
    }
}
