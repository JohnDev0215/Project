package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CurrentVolumeParam implements JsonCompliant {
    private String streamType;

    public void setStreamType(String str) {
        this.streamType = str;
    }

    public String getStreamType() {
        return this.streamType;
    }

    public static CurrentVolumeParam fromJson(String str) throws JSONException {
        CurrentVolumeParam currentVolumeParam = new CurrentVolumeParam();
        currentVolumeParam.streamType = new JSONObject(str).optString("stream_type");
        return currentVolumeParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("stream_type", this.streamType);
        return jSONObject.toString();
    }
}
