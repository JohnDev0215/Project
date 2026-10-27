package cerence.ark.assistant.controller.domain.cc.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SetVolumeParam implements JsonCompliant {
    private int percentage;
    private String streamType;

    public void setPercentage(int i) {
        this.percentage = i;
    }

    public int getPercentage() {
        return this.percentage;
    }

    public void setStreamType(String str) {
        this.streamType = str;
    }

    public String getStreamType() {
        return this.streamType;
    }

    public static SetVolumeParam fromJson(String str) throws JSONException {
        SetVolumeParam setVolumeParam = new SetVolumeParam();
        JSONObject jSONObject = new JSONObject(str);
        setVolumeParam.percentage = jSONObject.optInt("percentage");
        setVolumeParam.streamType = jSONObject.optString("stream_type");
        return setVolumeParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("percentage", this.percentage);
        jSONObject.put("stream_type", this.streamType);
        return jSONObject.toString();
    }
}
