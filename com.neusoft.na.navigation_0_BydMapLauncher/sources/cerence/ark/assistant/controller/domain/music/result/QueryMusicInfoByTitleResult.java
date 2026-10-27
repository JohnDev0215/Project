package cerence.ark.assistant.controller.domain.music.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.MusicInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class QueryMusicInfoByTitleResult extends BaseResult {
    private MusicInfo musicInfo;

    public void setMusicInfo(MusicInfo musicInfo) {
        this.musicInfo = musicInfo;
    }

    public MusicInfo getMusicInfo() {
        return this.musicInfo;
    }

    public static QueryMusicInfoByTitleResult fromJson(String str) throws JSONException {
        QueryMusicInfoByTitleResult queryMusicInfoByTitleResult = new QueryMusicInfoByTitleResult();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("music_info");
        if (jSONObjectOptJSONObject != null) {
            queryMusicInfoByTitleResult.musicInfo = MusicInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return queryMusicInfoByTitleResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.musicInfo != null) {
            jSONObject.put("music_info", new JSONObject(this.musicInfo.toJsonString()));
        }
        return jSONObject.toString();
    }
}
