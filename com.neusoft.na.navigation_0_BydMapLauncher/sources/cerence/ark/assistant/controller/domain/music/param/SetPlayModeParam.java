package cerence.ark.assistant.controller.domain.music.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SetPlayModeParam implements JsonCompliant {
    private String playMode;

    public void setPlayMode(String str) {
        this.playMode = str;
    }

    public String getPlayMode() {
        return this.playMode;
    }

    public static SetPlayModeParam fromJson(String str) throws JSONException {
        SetPlayModeParam setPlayModeParam = new SetPlayModeParam();
        setPlayModeParam.playMode = new JSONObject(str).optString("play_mode");
        return setPlayModeParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("play_mode", this.playMode);
        return jSONObject.toString();
    }
}
