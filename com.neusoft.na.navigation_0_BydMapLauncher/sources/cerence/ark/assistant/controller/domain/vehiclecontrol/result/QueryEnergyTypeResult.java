package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class QueryEnergyTypeResult extends BaseResult {
    private String energyType;

    public void setEnergyType(String str) {
        this.energyType = str;
    }

    public String getEnergyType() {
        return this.energyType;
    }

    public static QueryEnergyTypeResult fromJson(String str) throws JSONException {
        QueryEnergyTypeResult queryEnergyTypeResult = new QueryEnergyTypeResult();
        queryEnergyTypeResult.energyType = new JSONObject(str).optString("energy_type");
        return queryEnergyTypeResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("energy_type", this.energyType);
        return jSONObject.toString();
    }
}
