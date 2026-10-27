package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ACTemperatureInfo implements JsonCompliant {
    private String unitOfTemperature;
    private double value;
    private String vehicleArea;

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

    public static ACTemperatureInfo fromJson(String str) throws JSONException {
        ACTemperatureInfo aCTemperatureInfo = new ACTemperatureInfo();
        JSONObject jSONObject = new JSONObject(str);
        aCTemperatureInfo.vehicleArea = jSONObject.optString("vehicle_area");
        aCTemperatureInfo.value = jSONObject.optDouble(MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE);
        aCTemperatureInfo.unitOfTemperature = jSONObject.optString("unit_of_temperature");
        return aCTemperatureInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("vehicle_area", this.vehicleArea);
        jSONObject.put(MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, this.value);
        jSONObject.put("unit_of_temperature", this.unitOfTemperature);
        return jSONObject.toString();
    }
}
