package cerence.ark.assistant.controller.domain.hvac.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetACTemperatureResult extends BaseResult {
    private double currentValue;

    public void setCurrentValue(double d) {
        this.currentValue = d;
    }

    public double getCurrentValue() {
        return this.currentValue;
    }

    public static SetACTemperatureResult fromJson(String str) throws JSONException {
        SetACTemperatureResult setACTemperatureResult = new SetACTemperatureResult();
        setACTemperatureResult.currentValue = new JSONObject(str).optDouble("current_value");
        return setACTemperatureResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("current_value", this.currentValue);
        return jSONObject.toString();
    }
}
