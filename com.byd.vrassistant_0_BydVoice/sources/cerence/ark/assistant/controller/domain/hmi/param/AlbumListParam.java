package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AlbumListParam implements JsonCompliant {
    private String album;
    private String artist;

    public void setAlbum(String str) {
        this.album = str;
    }

    public String getAlbum() {
        return this.album;
    }

    public void setArtist(String str) {
        this.artist = str;
    }

    public String getArtist() {
        return this.artist;
    }

    public static AlbumListParam fromJson(String str) throws JSONException {
        AlbumListParam albumListParam = new AlbumListParam();
        JSONObject jSONObject = new JSONObject(str);
        albumListParam.album = jSONObject.optString("album");
        albumListParam.artist = jSONObject.optString("artist");
        return albumListParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("album", this.album);
        jSONObject.put("artist", this.artist);
        return jSONObject.toString();
    }
}
