package cerence.ark.assistant.controller.domain.hvac.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CheckHvacFunctionAvailabilityParam implements JsonCompliant {
    private String hvacFunction;

    public void setHvacFunction(String str) {
        this.hvacFunction = str;
    }

    public String getHvacFunction() {
        return this.hvacFunction;
    }

    public static CheckHvacFunctionAvailabilityParam fromJson(String str) throws JSONException {
        CheckHvacFunctionAvailabilityParam checkHvacFunctionAvailabilityParam = new CheckHvacFunctionAvailabilityParam();
        checkHvacFunctionAvailabilityParam.hvacFunction = new JSONObject(str).optString("hvac_function");
        return checkHvacFunctionAvailabilityParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("hvac_function", this.hvacFunction);
        return jSONObject.toString();
    }
}
