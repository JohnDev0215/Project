package cerence.ark.assistant.controller.domain.cc.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CheckFunctionAvailabilityParam implements JsonCompliant {
    private String ccFunction;

    public void setCcFunction(String str) {
        this.ccFunction = str;
    }

    public String getCcFunction() {
        return this.ccFunction;
    }

    public static CheckFunctionAvailabilityParam fromJson(String str) throws JSONException {
        CheckFunctionAvailabilityParam checkFunctionAvailabilityParam = new CheckFunctionAvailabilityParam();
        checkFunctionAvailabilityParam.ccFunction = new JSONObject(str).optString("cc_function");
        return checkFunctionAvailabilityParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cc_function", this.ccFunction);
        return jSONObject.toString();
    }
}
