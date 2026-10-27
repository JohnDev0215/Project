package cerence.ark.assistant.controller.domain.karaoke.param;

import cerence.ark.assistant.controller.struct.MusicInfo;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SingSongParam implements JsonCompliant {
    private MusicInfo songInfo;

    public void setSongInfo(MusicInfo musicInfo) {
        this.songInfo = musicInfo;
    }

    public MusicInfo getSongInfo() {
        return this.songInfo;
    }

    public static SingSongParam fromJson(String str) throws JSONException {
        SingSongParam singSongParam = new SingSongParam();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("song_info");
        if (jSONObjectOptJSONObject != null) {
            singSongParam.songInfo = MusicInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return singSongParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.songInfo != null) {
            jSONObject.put("song_info", new JSONObject(this.songInfo.toJsonString()));
        }
        return jSONObject.toString();
    }
}
