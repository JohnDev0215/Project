package cerence.ark.assistant.controller.domain.audiobook.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class PlayControlResult extends BaseResult {
    private int playRate;

    public void setPlayRate(int i) {
        this.playRate = i;
    }

    public int getPlayRate() {
        return this.playRate;
    }

    public static PlayControlResult fromJson(String str) throws JSONException {
        PlayControlResult playControlResult = new PlayControlResult();
        playControlResult.playRate = new JSONObject(str).optInt("play_rate");
        return playControlResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("play_rate", this.playRate);
        return jSONObject.toString();
    }
}
