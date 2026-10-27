package cerence.ark.assistant.controller.domain.music.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class QueryArtistByAlbumParam implements JsonCompliant {
    private String album;

    public void setAlbum(String str) {
        this.album = str;
    }

    public String getAlbum() {
        return this.album;
    }

    public static QueryArtistByAlbumParam fromJson(String str) throws JSONException {
        QueryArtistByAlbumParam queryArtistByAlbumParam = new QueryArtistByAlbumParam();
        queryArtistByAlbumParam.album = new JSONObject(str).optString("album");
        return queryArtistByAlbumParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("album", this.album);
        return jSONObject.toString();
    }
}
