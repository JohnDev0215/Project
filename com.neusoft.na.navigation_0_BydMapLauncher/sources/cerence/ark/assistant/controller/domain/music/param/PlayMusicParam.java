package cerence.ark.assistant.controller.domain.music.param;

import cerence.ark.assistant.controller.struct.MusicInfo;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class PlayMusicParam implements JsonCompliant {
    private MusicInfo musicInfo;
    private String searchPhrase;
    private String source;

    public void setMusicInfo(MusicInfo musicInfo) {
        this.musicInfo = musicInfo;
    }

    public MusicInfo getMusicInfo() {
        return this.musicInfo;
    }

    public void setSearchPhrase(String str) {
        this.searchPhrase = str;
    }

    public String getSearchPhrase() {
        return this.searchPhrase;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public String getSource() {
        return this.source;
    }

    public static PlayMusicParam fromJson(String str) throws JSONException {
        PlayMusicParam playMusicParam = new PlayMusicParam();
        JSONObject jSONObject = new JSONObject(str);
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("music_info");
        if (jSONObjectOptJSONObject != null) {
            playMusicParam.musicInfo = MusicInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        playMusicParam.searchPhrase = jSONObject.optString("search_phrase");
        playMusicParam.source = jSONObject.optString("source");
        return playMusicParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.musicInfo != null) {
            jSONObject.put("music_info", new JSONObject(this.musicInfo.toJsonString()));
        }
        jSONObject.put("search_phrase", this.searchPhrase);
        jSONObject.put("source", this.source);
        return jSONObject.toString();
    }
}
