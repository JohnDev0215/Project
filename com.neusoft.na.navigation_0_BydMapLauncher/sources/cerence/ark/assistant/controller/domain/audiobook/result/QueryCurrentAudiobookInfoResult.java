package cerence.ark.assistant.controller.domain.audiobook.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.AudiobookInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class QueryCurrentAudiobookInfoResult extends BaseResult {
    private AudiobookInfo audiobookInfo;

    public void setAudiobookInfo(AudiobookInfo audiobookInfo) {
        this.audiobookInfo = audiobookInfo;
    }

    public AudiobookInfo getAudiobookInfo() {
        return this.audiobookInfo;
    }

    public static QueryCurrentAudiobookInfoResult fromJson(String str) throws JSONException {
        QueryCurrentAudiobookInfoResult queryCurrentAudiobookInfoResult = new QueryCurrentAudiobookInfoResult();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("audiobook_info");
        if (jSONObjectOptJSONObject != null) {
            queryCurrentAudiobookInfoResult.audiobookInfo = AudiobookInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return queryCurrentAudiobookInfoResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.audiobookInfo != null) {
            jSONObject.put("audiobook_info", new JSONObject(this.audiobookInfo.toJsonString()));
        }
        return jSONObject.toString();
    }
}
