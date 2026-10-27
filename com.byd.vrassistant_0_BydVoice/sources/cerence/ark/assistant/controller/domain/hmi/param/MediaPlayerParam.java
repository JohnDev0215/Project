package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class MediaPlayerParam implements JsonCompliant {
    private String provider;

    public void setProvider(String str) {
        this.provider = str;
    }

    public String getProvider() {
        return this.provider;
    }

    public static MediaPlayerParam fromJson(String str) throws JSONException {
        MediaPlayerParam mediaPlayerParam = new MediaPlayerParam();
        mediaPlayerParam.provider = new JSONObject(str).optString("provider");
        return mediaPlayerParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("provider", this.provider);
        return jSONObject.toString();
    }
}
