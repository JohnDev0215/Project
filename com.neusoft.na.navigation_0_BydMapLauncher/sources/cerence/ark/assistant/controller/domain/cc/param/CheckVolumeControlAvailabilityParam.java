package cerence.ark.assistant.controller.domain.cc.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CheckVolumeControlAvailabilityParam implements JsonCompliant {
    private String streamType;

    public void setStreamType(String str) {
        this.streamType = str;
    }

    public String getStreamType() {
        return this.streamType;
    }

    public static CheckVolumeControlAvailabilityParam fromJson(String str) throws JSONException {
        CheckVolumeControlAvailabilityParam checkVolumeControlAvailabilityParam = new CheckVolumeControlAvailabilityParam();
        checkVolumeControlAvailabilityParam.streamType = new JSONObject(str).optString("stream_type");
        return checkVolumeControlAvailabilityParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("stream_type", this.streamType);
        return jSONObject.toString();
    }
}
