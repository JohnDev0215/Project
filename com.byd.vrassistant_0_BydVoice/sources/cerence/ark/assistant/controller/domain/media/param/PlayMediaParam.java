package cerence.ark.assistant.controller.domain.media.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class PlayMediaParam implements JsonCompliant {
    private String mediaProvider;

    public void setMediaProvider(String str) {
        this.mediaProvider = str;
    }

    public String getMediaProvider() {
        return this.mediaProvider;
    }

    public static PlayMediaParam fromJson(String str) throws JSONException {
        PlayMediaParam playMediaParam = new PlayMediaParam();
        playMediaParam.mediaProvider = new JSONObject(str).optString("media_provider");
        return playMediaParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("media_provider", this.mediaProvider);
        return jSONObject.toString();
    }
}
