package cerence.ark.assistant.controller.domain.hvac.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class HvacCheckisHeatPumpResult extends BaseResult {
    private boolean isPump;

    public void setIsPump(boolean z) {
        this.isPump = z;
    }

    public boolean getIsPump() {
        return this.isPump;
    }

    public static HvacCheckisHeatPumpResult fromJson(String str) throws JSONException {
        HvacCheckisHeatPumpResult hvacCheckisHeatPumpResult = new HvacCheckisHeatPumpResult();
        hvacCheckisHeatPumpResult.isPump = new JSONObject(str).optBoolean("is_pump");
        return hvacCheckisHeatPumpResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_pump", this.isPump);
        return jSONObject.toString();
    }
}
