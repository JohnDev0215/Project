package cerence.ark.assistant.controller.domain.cc.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetScreenBrightnessResult extends BaseResult {
    private int percentage;

    public void setPercentage(int i) {
        this.percentage = i;
    }

    public int getPercentage() {
        return this.percentage;
    }

    public static GetScreenBrightnessResult fromJson(String str) throws JSONException {
        GetScreenBrightnessResult getScreenBrightnessResult = new GetScreenBrightnessResult();
        getScreenBrightnessResult.percentage = new JSONObject(str).optInt("percentage");
        return getScreenBrightnessResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("percentage", this.percentage);
        return jSONObject.toString();
    }
}
