package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class VolumeChangeParam implements JsonCompliant {
    private int currentValue;
    private int originValue;
    private String streamType;

    public void setStreamType(String str) {
        this.streamType = str;
    }

    public String getStreamType() {
        return this.streamType;
    }

    public void setOriginValue(int i) {
        this.originValue = i;
    }

    public int getOriginValue() {
        return this.originValue;
    }

    public void setCurrentValue(int i) {
        this.currentValue = i;
    }

    public int getCurrentValue() {
        return this.currentValue;
    }

    public static VolumeChangeParam fromJson(String str) throws JSONException {
        VolumeChangeParam volumeChangeParam = new VolumeChangeParam();
        JSONObject jSONObject = new JSONObject(str);
        volumeChangeParam.streamType = jSONObject.optString("stream_type");
        volumeChangeParam.originValue = jSONObject.optInt("origin_value");
        volumeChangeParam.currentValue = jSONObject.optInt("current_value");
        return volumeChangeParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("stream_type", this.streamType);
        jSONObject.put("origin_value", this.originValue);
        jSONObject.put("current_value", this.currentValue);
        return jSONObject.toString();
    }
}
