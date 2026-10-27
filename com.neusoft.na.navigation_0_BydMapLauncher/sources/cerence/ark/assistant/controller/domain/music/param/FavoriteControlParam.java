package cerence.ark.assistant.controller.domain.music.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class FavoriteControlParam implements JsonCompliant {
    private String controlType;

    public void setControlType(String str) {
        this.controlType = str;
    }

    public String getControlType() {
        return this.controlType;
    }

    public static FavoriteControlParam fromJson(String str) throws JSONException {
        FavoriteControlParam favoriteControlParam = new FavoriteControlParam();
        favoriteControlParam.controlType = new JSONObject(str).optString("control_type");
        return favoriteControlParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("control_type", this.controlType);
        return jSONObject.toString();
    }
}
