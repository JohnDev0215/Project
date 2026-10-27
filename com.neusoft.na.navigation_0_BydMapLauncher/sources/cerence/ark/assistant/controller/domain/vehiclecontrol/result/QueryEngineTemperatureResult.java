package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class QueryEngineTemperatureResult extends BaseResult {
    private int temperature;
    private String unit;

    public void setTemperature(int i) {
        this.temperature = i;
    }

    public int getTemperature() {
        return this.temperature;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public String getUnit() {
        return this.unit;
    }

    public static QueryEngineTemperatureResult fromJson(String str) throws JSONException {
        QueryEngineTemperatureResult queryEngineTemperatureResult = new QueryEngineTemperatureResult();
        JSONObject jSONObject = new JSONObject(str);
        queryEngineTemperatureResult.temperature = jSONObject.optInt("temperature");
        queryEngineTemperatureResult.unit = jSONObject.optString("unit");
        return queryEngineTemperatureResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("temperature", this.temperature);
        jSONObject.put("unit", this.unit);
        return jSONObject.toString();
    }
}
