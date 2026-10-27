package cerence.ark.assistant.controller.domain.radio.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class FavoriteControlResult extends BaseResult {
    private String radioTitle;

    public void setRadioTitle(String str) {
        this.radioTitle = str;
    }

    public String getRadioTitle() {
        return this.radioTitle;
    }

    public static FavoriteControlResult fromJson(String str) throws JSONException {
        FavoriteControlResult favoriteControlResult = new FavoriteControlResult();
        favoriteControlResult.radioTitle = new JSONObject(str).optString("radio_title");
        return favoriteControlResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("radio_title", this.radioTitle);
        return jSONObject.toString();
    }
}
