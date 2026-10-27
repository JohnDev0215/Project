package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetScreenBrightnessResult extends BaseResult {
    private int curBrightness;
    private int maxBrightness;
    private int minBrightness;

    public void setCurBrightness(int i) {
        this.curBrightness = i;
    }

    public int getCurBrightness() {
        return this.curBrightness;
    }

    public void setMaxBrightness(int i) {
        this.maxBrightness = i;
    }

    public int getMaxBrightness() {
        return this.maxBrightness;
    }

    public void setMinBrightness(int i) {
        this.minBrightness = i;
    }

    public int getMinBrightness() {
        return this.minBrightness;
    }

    public static GetScreenBrightnessResult fromJson(String str) throws JSONException {
        GetScreenBrightnessResult getScreenBrightnessResult = new GetScreenBrightnessResult();
        JSONObject jSONObject = new JSONObject(str);
        getScreenBrightnessResult.curBrightness = jSONObject.optInt("cur_brightness");
        getScreenBrightnessResult.maxBrightness = jSONObject.optInt("max_brightness");
        getScreenBrightnessResult.minBrightness = jSONObject.optInt("min_brightness");
        return getScreenBrightnessResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cur_brightness", this.curBrightness);
        jSONObject.put("max_brightness", this.maxBrightness);
        jSONObject.put("min_brightness", this.minBrightness);
        return jSONObject.toString();
    }
}
