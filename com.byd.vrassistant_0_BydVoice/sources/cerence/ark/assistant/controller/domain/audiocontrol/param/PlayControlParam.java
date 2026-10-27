package cerence.ark.assistant.controller.domain.audiocontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import com.byd.common.bean.WuwInfoBean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class PlayControlParam implements JsonCompliant {
    private String controlType;
    private String mediaProvider;
    private String mediaType;
    private int value;

    public void setControlType(String str) {
        this.controlType = str;
    }

    public String getControlType() {
        return this.controlType;
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

    public void setValue(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static PlayControlParam fromJson(String str) throws JSONException {
        PlayControlParam playControlParam = new PlayControlParam();
        JSONObject jSONObject = new JSONObject(str);
        playControlParam.controlType = jSONObject.optString("control_type");
        playControlParam.mediaType = jSONObject.optString("media_type");
        playControlParam.mediaProvider = jSONObject.optString("media_provider");
        playControlParam.value = jSONObject.optInt(WuwInfoBean.ValueKey);
        return playControlParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("control_type", this.controlType);
        jSONObject.put("media_type", this.mediaType);
        jSONObject.put("media_provider", this.mediaProvider);
        jSONObject.put(WuwInfoBean.ValueKey, this.value);
        return jSONObject.toString();
    }
}
