package cerence.ark.assistant.controller.domain.music.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.MusicInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class QueryCurrentMusicInfoResult extends BaseResult {
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

    public static QueryCurrentMusicInfoResult fromJson(String str) throws JSONException {
        QueryCurrentMusicInfoResult queryCurrentMusicInfoResult = new QueryCurrentMusicInfoResult();
        JSONObject jSONObject = new JSONObject(str);
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("music_info");
        if (jSONObjectOptJSONObject != null) {
            queryCurrentMusicInfoResult.musicInfo = MusicInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        queryCurrentMusicInfoResult.source = jSONObject.optString("source");
        return queryCurrentMusicInfoResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.musicInfo != null) {
            jSONObject.put("music_info", new JSONObject(this.musicInfo.toJsonString()));
        }
        jSONObject.put("source", this.source);
        return jSONObject.toString();
    }
}
