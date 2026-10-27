package cerence.ark.assistant.controller.domain.music.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetSimilarArtistParam implements JsonCompliant {
    private String artist;

    public void setArtist(String str) {
        this.artist = str;
    }

    public String getArtist() {
        return this.artist;
    }

    public static GetSimilarArtistParam fromJson(String str) throws JSONException {
        GetSimilarArtistParam getSimilarArtistParam = new GetSimilarArtistParam();
        getSimilarArtistParam.artist = new JSONObject(str).optString("artist");
        return getSimilarArtistParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("artist", this.artist);
        return jSONObject.toString();
    }
}
