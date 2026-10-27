package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class QueryEnergyConsumptionParam implements JsonCompliant {
    private String calculationMethod;

    public void setCalculationMethod(String str) {
        this.calculationMethod = str;
    }

    public String getCalculationMethod() {
        return this.calculationMethod;
    }

    public static QueryEnergyConsumptionParam fromJson(String str) throws JSONException {
        QueryEnergyConsumptionParam queryEnergyConsumptionParam = new QueryEnergyConsumptionParam();
        queryEnergyConsumptionParam.calculationMethod = new JSONObject(str).optString("calculation_method");
        return queryEnergyConsumptionParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("calculation_method", this.calculationMethod);
        return jSONObject.toString();
    }
}
