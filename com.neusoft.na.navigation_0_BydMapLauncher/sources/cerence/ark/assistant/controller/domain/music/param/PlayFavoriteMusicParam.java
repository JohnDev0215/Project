package cerence.ark.assistant.controller.domain.music.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class PlayFavoriteMusicParam implements JsonCompliant {
    private String source;

    public void setSource(String str) {
        this.source = str;
    }

    public String getSource() {
        return this.source;
    }

    public static PlayFavoriteMusicParam fromJson(String str) throws JSONException {
        PlayFavoriteMusicParam playFavoriteMusicParam = new PlayFavoriteMusicParam();
        playFavoriteMusicParam.source = new JSONObject(str).optString("source");
        return playFavoriteMusicParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("source", this.source);
        return jSONObject.toString();
    }
}
