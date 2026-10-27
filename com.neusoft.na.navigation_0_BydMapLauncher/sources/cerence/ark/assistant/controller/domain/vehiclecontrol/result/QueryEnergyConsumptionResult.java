package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class QueryEnergyConsumptionResult extends BaseResult {
    private String unit;
    private double value;

    public void setValue(double d) {
        this.value = d;
    }

    public double getValue() {
        return this.value;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public String getUnit() {
        return this.unit;
    }

    public static QueryEnergyConsumptionResult fromJson(String str) throws JSONException {
        QueryEnergyConsumptionResult queryEnergyConsumptionResult = new QueryEnergyConsumptionResult();
        JSONObject jSONObject = new JSONObject(str);
        queryEnergyConsumptionResult.value = jSONObject.optDouble(MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE);
        queryEnergyConsumptionResult.unit = jSONObject.optString("unit");
        return queryEnergyConsumptionResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, this.value);
        jSONObject.put("unit", this.unit);
        return jSONObject.toString();
    }
}
