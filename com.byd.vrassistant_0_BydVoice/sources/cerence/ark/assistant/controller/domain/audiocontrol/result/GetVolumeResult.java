package cerence.ark.assistant.controller.domain.audiocontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetVolumeResult extends BaseResult {
    private int currentValue;
    private int maxValue;
    private int minValue;

    public void setMinValue(int i) {
        this.minValue = i;
    }

    public int getMinValue() {
        return this.minValue;
    }

    public void setCurrentValue(int i) {
        this.currentValue = i;
    }

    public int getCurrentValue() {
        return this.currentValue;
    }

    public void setMaxValue(int i) {
        this.maxValue = i;
    }

    public int getMaxValue() {
        return this.maxValue;
    }

    public static GetVolumeResult fromJson(String str) throws JSONException {
        GetVolumeResult getVolumeResult = new GetVolumeResult();
        JSONObject jSONObject = new JSONObject(str);
        getVolumeResult.minValue = jSONObject.optInt("min_value");
        getVolumeResult.currentValue = jSONObject.optInt("current_value");
        getVolumeResult.maxValue = jSONObject.optInt("max_value");
        return getVolumeResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("min_value", this.minValue);
        jSONObject.put("current_value", this.currentValue);
        jSONObject.put("max_value", this.maxValue);
        return jSONObject.toString();
    }
}
