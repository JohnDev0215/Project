package cerence.ark.assistant.controller.domain.audiocontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import com.byd.common.bean.WuwInfoBean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetVolumeParam implements JsonCompliant {
    private String streamType;
    private int value;

    public void setStreamType(String str) {
        this.streamType = str;
    }

    public String getStreamType() {
        return this.streamType;
    }

    public void setValue(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static SetVolumeParam fromJson(String str) throws JSONException {
        SetVolumeParam setVolumeParam = new SetVolumeParam();
        JSONObject jSONObject = new JSONObject(str);
        setVolumeParam.streamType = jSONObject.optString("stream_type");
        setVolumeParam.value = jSONObject.optInt(WuwInfoBean.ValueKey);
        return setVolumeParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("stream_type", this.streamType);
        jSONObject.put(WuwInfoBean.ValueKey, this.value);
        return jSONObject.toString();
    }
}
