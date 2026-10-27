package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.bouncycastle.i18n.MessageBundle;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class MusicInfo implements JsonCompliant {
    private String album;
    private String artist;
    private String genre;
    private String title;

    public void setTitle(String str) {
        this.title = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setGenre(String str) {
        this.genre = str;
    }

    public String getGenre() {
        return this.genre;
    }

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

    public static MusicInfo fromJson(String str) throws JSONException {
        MusicInfo musicInfo = new MusicInfo();
        JSONObject jSONObject = new JSONObject(str);
        musicInfo.title = jSONObject.optString(MessageBundle.TITLE_ENTRY);
        musicInfo.genre = jSONObject.optString("genre");
        musicInfo.album = jSONObject.optString("album");
        musicInfo.artist = jSONObject.optString("artist");
        return musicInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(MessageBundle.TITLE_ENTRY, this.title);
        jSONObject.put("genre", this.genre);
        jSONObject.put("album", this.album);
        jSONObject.put("artist", this.artist);
        return jSONObject.toString();
    }
}
