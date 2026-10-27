package cerence.ark.assistant.controller.domain.audiocontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetRecentPlayedMediaProviderParam implements JsonCompliant {
    private String mediaType;

    public void setMediaType(String str) {
        this.mediaType = str;
    }

    public String getMediaType() {
        return this.mediaType;
    }

    public static GetRecentPlayedMediaProviderParam fromJson(String str) throws JSONException {
        GetRecentPlayedMediaProviderParam getRecentPlayedMediaProviderParam = new GetRecentPlayedMediaProviderParam();
        getRecentPlayedMediaProviderParam.mediaType = new JSONObject(str).optString("media_type");
        return getRecentPlayedMediaProviderParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("media_type", this.mediaType);
        return jSONObject.toString();
    }
}
