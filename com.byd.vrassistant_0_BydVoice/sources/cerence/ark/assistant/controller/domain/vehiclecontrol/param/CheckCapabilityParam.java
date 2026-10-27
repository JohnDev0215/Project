package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import com.byd.common.bean.WuwInfoBean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CheckCapabilityParam implements JsonCompliant {
    private int value;
    private String vehicleFunction;

    public void setVehicleFunction(String str) {
        this.vehicleFunction = str;
    }

    public String getVehicleFunction() {
        return this.vehicleFunction;
    }

    public void setValue(int i) {
        this.value = i;
    }

    public int getValue() {
        return this.value;
    }

    public static CheckCapabilityParam fromJson(String str) throws JSONException {
        CheckCapabilityParam checkCapabilityParam = new CheckCapabilityParam();
        JSONObject jSONObject = new JSONObject(str);
        checkCapabilityParam.vehicleFunction = jSONObject.optString("vehicle_function");
        checkCapabilityParam.value = jSONObject.optInt(WuwInfoBean.ValueKey);
        return checkCapabilityParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("vehicle_function", this.vehicleFunction);
        jSONObject.put(WuwInfoBean.ValueKey, this.value);
        return jSONObject.toString();
    }
}
