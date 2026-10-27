package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SongListParam implements JsonCompliant {
    private String artist;
    private String songName;

    public void setSongName(String str) {
        this.songName = str;
    }

    public String getSongName() {
        return this.songName;
    }

    public void setArtist(String str) {
        this.artist = str;
    }

    public String getArtist() {
        return this.artist;
    }

    public static SongListParam fromJson(String str) throws JSONException {
        SongListParam songListParam = new SongListParam();
        JSONObject jSONObject = new JSONObject(str);
        songListParam.songName = jSONObject.optString("song_name");
        songListParam.artist = jSONObject.optString("artist");
        return songListParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("song_name", this.songName);
        jSONObject.put("artist", this.artist);
        return jSONObject.toString();
    }
}
