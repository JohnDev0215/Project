package cerence.ark.assistant.controller.domain.audiobook.param;

import cerence.ark.assistant.controller.struct.AudiobookInfo;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class PlayAudiobookParam implements JsonCompliant {
    private AudiobookInfo audiobookInfo;

    public void setAudiobookInfo(AudiobookInfo audiobookInfo) {
        this.audiobookInfo = audiobookInfo;
    }

    public AudiobookInfo getAudiobookInfo() {
        return this.audiobookInfo;
    }

    public static PlayAudiobookParam fromJson(String str) throws JSONException {
        PlayAudiobookParam playAudiobookParam = new PlayAudiobookParam();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("audiobook_info");
        if (jSONObjectOptJSONObject != null) {
            playAudiobookParam.audiobookInfo = AudiobookInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return playAudiobookParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.audiobookInfo != null) {
            jSONObject.put("audiobook_info", new JSONObject(this.audiobookInfo.toJsonString()));
        }
        return jSONObject.toString();
    }
}
