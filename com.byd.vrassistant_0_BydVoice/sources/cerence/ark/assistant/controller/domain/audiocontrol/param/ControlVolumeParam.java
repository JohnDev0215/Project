package cerence.ark.assistant.controller.domain.audiocontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ControlVolumeParam implements JsonCompliant {
    private String action;
    private String streamType;

    public void setAction(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }

    public void setStreamType(String str) {
        this.streamType = str;
    }

    public String getStreamType() {
        return this.streamType;
    }

    public static ControlVolumeParam fromJson(String str) throws JSONException {
        ControlVolumeParam controlVolumeParam = new ControlVolumeParam();
        JSONObject jSONObject = new JSONObject(str);
        controlVolumeParam.action = jSONObject.optString("action");
        controlVolumeParam.streamType = jSONObject.optString("stream_type");
        return controlVolumeParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("action", this.action);
        jSONObject.put("stream_type", this.streamType);
        return jSONObject.toString();
    }
}
