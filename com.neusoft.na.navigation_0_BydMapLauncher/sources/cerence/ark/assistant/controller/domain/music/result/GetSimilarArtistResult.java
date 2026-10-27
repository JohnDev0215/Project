package cerence.ark.assistant.controller.domain.music.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetSimilarArtistResult extends BaseResult {
    private String artist;

    public void setArtist(String str) {
        this.artist = str;
    }

    public String getArtist() {
        return this.artist;
    }

    public static GetSimilarArtistResult fromJson(String str) throws JSONException {
        GetSimilarArtistResult getSimilarArtistResult = new GetSimilarArtistResult();
        getSimilarArtistResult.artist = new JSONObject(str).optString("artist");
        return getSimilarArtistResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("artist", this.artist);
        return jSONObject.toString();
    }
}
