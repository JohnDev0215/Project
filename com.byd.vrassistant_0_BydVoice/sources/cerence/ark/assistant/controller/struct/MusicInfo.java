package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class MusicInfo implements JsonCompliant {
    private String album;
    private String artist;
    private String genre;
    private String id;
    private String songName;

    public void setId(String str) {
        this.id = str;
    }

    public String getId() {
        return this.id;
    }

    public void setSongName(String str) {
        this.songName = str;
    }

    public String getSongName() {
        return this.songName;
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
        musicInfo.id = jSONObject.optString("id");
        musicInfo.songName = jSONObject.optString("song_name");
        musicInfo.genre = jSONObject.optString("genre");
        musicInfo.album = jSONObject.optString("album");
        musicInfo.artist = jSONObject.optString("artist");
        return musicInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.id);
        jSONObject.put("song_name", this.songName);
        jSONObject.put("genre", this.genre);
        jSONObject.put("album", this.album);
        jSONObject.put("artist", this.artist);
        return jSONObject.toString();
    }
}
