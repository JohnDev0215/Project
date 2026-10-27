package cerence.ark.assistant.controller.domain.audiocontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class FavoriteControlParam implements JsonCompliant {
    private String controlType;
    private String mediaType;

    public void setControlType(String str) {
        this.controlType = str;
    }

    public String getControlType() {
        return this.controlType;
    }

    public void setMediaType(String str) {
        this.mediaType = str;
    }

    public String getMediaType() {
        return this.mediaType;
    }

    public static FavoriteControlParam fromJson(String str) throws JSONException {
        FavoriteControlParam favoriteControlParam = new FavoriteControlParam();
        JSONObject jSONObject = new JSONObject(str);
        favoriteControlParam.controlType = jSONObject.optString("control_type");
        favoriteControlParam.mediaType = jSONObject.optString("media_type");
        return favoriteControlParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("control_type", this.controlType);
        jSONObject.put("media_type", this.mediaType);
        return jSONObject.toString();
    }
}
