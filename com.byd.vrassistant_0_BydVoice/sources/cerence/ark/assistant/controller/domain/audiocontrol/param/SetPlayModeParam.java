package cerence.ark.assistant.controller.domain.audiocontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetPlayModeParam implements JsonCompliant {
    private String action;
    private String mediaProvider;
    private String mediaType;
    private boolean needPlay;
    private String playMode;

    public void setAction(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }

    public void setPlayMode(String str) {
        this.playMode = str;
    }

    public String getPlayMode() {
        return this.playMode;
    }

    public void setMediaType(String str) {
        this.mediaType = str;
    }

    public String getMediaType() {
        return this.mediaType;
    }

    public void setMediaProvider(String str) {
        this.mediaProvider = str;
    }

    public String getMediaProvider() {
        return this.mediaProvider;
    }

    public void setNeedPlay(boolean z) {
        this.needPlay = z;
    }

    public boolean getNeedPlay() {
        return this.needPlay;
    }

    public static SetPlayModeParam fromJson(String str) throws JSONException {
        SetPlayModeParam setPlayModeParam = new SetPlayModeParam();
        JSONObject jSONObject = new JSONObject(str);
        setPlayModeParam.action = jSONObject.optString("action");
        setPlayModeParam.playMode = jSONObject.optString("play_mode");
        setPlayModeParam.mediaType = jSONObject.optString("media_type");
        setPlayModeParam.mediaProvider = jSONObject.optString("media_provider");
        setPlayModeParam.needPlay = jSONObject.optBoolean("need_play");
        return setPlayModeParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("action", this.action);
        jSONObject.put("play_mode", this.playMode);
        jSONObject.put("media_type", this.mediaType);
        jSONObject.put("media_provider", this.mediaProvider);
        jSONObject.put("need_play", this.needPlay);
        return jSONObject.toString();
    }
}
