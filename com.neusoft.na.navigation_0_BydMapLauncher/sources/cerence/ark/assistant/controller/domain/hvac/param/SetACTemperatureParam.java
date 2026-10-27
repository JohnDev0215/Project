package cerence.ark.assistant.controller.domain.hvac.param;

import android.car.user.CarUserManager;
import cerence.ark.assistant.core.JsonCompliant;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SetACTemperatureParam implements JsonCompliant {
    private String action;
    private String unitOfTemperature;
    private int value;
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

    public void setValue(int i) {
        this.value = i;
    }

    public int getValue() {
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
        setACTemperatureParam.action = jSONObject.optString(CarUserManager.BUNDLE_PARAM_ACTION);
        setACTemperatureParam.vehicleArea = jSONObject.optString("vehicle_area");
        setACTemperatureParam.value = jSONObject.optInt(MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE);
        setACTemperatureParam.unitOfTemperature = jSONObject.optString("unit_of_temperature");
        return setACTemperatureParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(CarUserManager.BUNDLE_PARAM_ACTION, this.action);
        jSONObject.put("vehicle_area", this.vehicleArea);
        jSONObject.put(MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, this.value);
        jSONObject.put("unit_of_temperature", this.unitOfTemperature);
        return jSONObject.toString();
    }
}
