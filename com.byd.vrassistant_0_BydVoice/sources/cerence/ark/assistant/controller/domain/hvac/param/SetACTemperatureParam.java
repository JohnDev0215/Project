package cerence.ark.assistant.controller.domain.hvac.param;

import cerence.ark.assistant.core.JsonCompliant;
import com.byd.common.bean.WuwInfoBean;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetACTemperatureParam implements JsonCompliant {
    private String action;
    private String unitOfTemperature;
    private double value;
    private String vehicleArea;

    public void setAction(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }

    public void setVehicleArea(String str) {
        this.vehicleArea = str;
    }

    public String getVehicleArea() {
        return this.vehicleArea;
    }

    public void setValue(double d) {
        this.value = d;
    }

    public double getValue() {
        return this.value;
    }

    public void setUnitOfTemperature(String str) {
        this.unitOfTemperature = str;
    }

    public String getUnitOfTemperature() {
        return this.unitOfTemperature;
    }

    public static SetACTemperatureParam fromJson(String str) throws JSONException {
        SetACTemperatureParam setACTemperatureParam = new SetACTemperatureParam();
        JSONObject jSONObject = new JSONObject(str);
        setACTemperatureParam.action = jSONObject.optString("action");
        setACTemperatureParam.vehicleArea = jSONObject.optString("vehicle_area");
        setACTemperatureParam.value = jSONObject.optDouble(WuwInfoBean.ValueKey);
        setACTemperatureParam.unitOfTemperature = jSONObject.optString("unit_of_temperature");
        return setACTemperatureParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("action", this.action);
        jSONObject.put("vehicle_area", this.vehicleArea);
        jSONObject.put(WuwInfoBean.ValueKey, this.value);
        jSONObject.put("unit_of_temperature", this.unitOfTemperature);
        return jSONObject.toString();
    }
}
