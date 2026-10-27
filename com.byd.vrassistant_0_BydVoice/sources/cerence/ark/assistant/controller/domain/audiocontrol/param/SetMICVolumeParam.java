package cerence.ark.assistant.controller.domain.audiocontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import com.byd.common.bean.WuwInfoBean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetMICVolumeParam implements JsonCompliant {
    private int value;

    public void setValue(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static SetMICVolumeParam fromJson(String str) throws JSONException {
        SetMICVolumeParam setMICVolumeParam = new SetMICVolumeParam();
        setMICVolumeParam.value = new JSONObject(str).optInt(WuwInfoBean.ValueKey);
        return setMICVolumeParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(WuwInfoBean.ValueKey, this.value);
        return jSONObject.toString();
    }
}
