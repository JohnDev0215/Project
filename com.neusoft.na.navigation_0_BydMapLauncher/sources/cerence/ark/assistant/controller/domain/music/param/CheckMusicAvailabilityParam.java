package cerence.ark.assistant.controller.domain.music.param;

import cerence.ark.assistant.controller.struct.MusicInfo;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CheckMusicAvailabilityParam implements JsonCompliant {
    private MusicInfo musicInfo;
    private String source;

    public void setMusicInfo(MusicInfo musicInfo) {
        this.musicInfo = musicInfo;
    }

    public MusicInfo getMusicInfo() {
        return this.musicInfo;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public String getSource() {
        return this.source;
    }

    public static CheckMusicAvailabilityParam fromJson(String str) throws JSONException {
        CheckMusicAvailabilityParam checkMusicAvailabilityParam = new CheckMusicAvailabilityParam();
        JSONObject jSONObject = new JSONObject(str);
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("music_info");
        if (jSONObjectOptJSONObject != null) {
            checkMusicAvailabilityParam.musicInfo = MusicInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        checkMusicAvailabilityParam.source = jSONObject.optString("source");
        return checkMusicAvailabilityParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.musicInfo != null) {
            jSONObject.put("music_info", new JSONObject(this.musicInfo.toJsonString()));
        }
        jSONObject.put("source", this.source);
        return jSONObject.toString();
    }
}
