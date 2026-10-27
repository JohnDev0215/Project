package cerence.ark.assistant.controller.domain.audiocontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetMICVolumeResult extends BaseResult {
    private int currentValue;

    public void setCurrentValue(int i) {
        this.currentValue = i;
    }

    public int getCurrentValue() {
        return this.currentValue;
    }

    public static GetMICVolumeResult fromJson(String str) throws JSONException {
        GetMICVolumeResult getMICVolumeResult = new GetMICVolumeResult();
        getMICVolumeResult.currentValue = new JSONObject(str).optInt("current_value");
        return getMICVolumeResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("current_value", this.currentValue);
        return jSONObject.toString();
    }
}
