package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetFavoritesListStatusResult extends BaseResult {
    private boolean isEmpty;
    private boolean isOpen;

    public void setIsEmpty(boolean z) {
        this.isEmpty = z;
    }

    public boolean getIsEmpty() {
        return this.isEmpty;
    }

    public void setIsOpen(boolean z) {
        this.isOpen = z;
    }

    public boolean getIsOpen() {
        return this.isOpen;
    }

    public static GetFavoritesListStatusResult fromJson(String str) throws JSONException {
        GetFavoritesListStatusResult getFavoritesListStatusResult = new GetFavoritesListStatusResult();
        JSONObject jSONObject = new JSONObject(str);
        getFavoritesListStatusResult.isEmpty = jSONObject.optBoolean("is_empty");
        getFavoritesListStatusResult.isOpen = jSONObject.optBoolean("is_open");
        return getFavoritesListStatusResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_empty", this.isEmpty);
        jSONObject.put("is_open", this.isOpen);
        return jSONObject.toString();
    }
}
