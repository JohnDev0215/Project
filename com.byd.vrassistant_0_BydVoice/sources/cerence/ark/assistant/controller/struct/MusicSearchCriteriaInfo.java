package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import cerence.ark.assistant.library.JsonKey;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class MusicSearchCriteriaInfo implements JsonCompliant {
    private String album;
    private String artist;
    private String genre;
    private String mediaProvider;
    private String musicType;
    private String songName;
    private String tag;

    public void setMusicType(String str) {
        this.musicType = str;
    }

    public String getMusicType() {
        return this.musicType;
    }

    public void setSongName(String str) {
        this.songName = str;
    }

    public String getSongName() {
        return this.songName;
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

    public void setGenre(String str) {
        this.genre = str;
    }

    public String getGenre() {
        return this.genre;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public String getTag() {
        return this.tag;
    }

    public void setMediaProvider(String str) {
        this.mediaProvider = str;
    }

    public String getMediaProvider() {
        return this.mediaProvider;
    }

    public static MusicSearchCriteriaInfo fromJson(String str) throws JSONException {
        MusicSearchCriteriaInfo musicSearchCriteriaInfo = new MusicSearchCriteriaInfo();
        JSONObject jSONObject = new JSONObject(str);
        musicSearchCriteriaInfo.musicType = jSONObject.optString("music_type");
        musicSearchCriteriaInfo.songName = jSONObject.optString("song_name");
        musicSearchCriteriaInfo.album = jSONObject.optString("album");
        musicSearchCriteriaInfo.artist = jSONObject.optString("artist");
        musicSearchCriteriaInfo.genre = jSONObject.optString("genre");
        musicSearchCriteriaInfo.tag = jSONObject.optString(JsonKey.VERSION_TAG);
        musicSearchCriteriaInfo.mediaProvider = jSONObject.optString("media_provider");
        return musicSearchCriteriaInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("music_type", this.musicType);
        jSONObject.put("song_name", this.songName);
        jSONObject.put("album", this.album);
        jSONObject.put("artist", this.artist);
        jSONObject.put("genre", this.genre);
        jSONObject.put(JsonKey.VERSION_TAG, this.tag);
        jSONObject.put("media_provider", this.mediaProvider);
        return jSONObject.toString();
    }
}
