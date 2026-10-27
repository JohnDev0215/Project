package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class VehicleInfo implements JsonCompliant {
    private String driverSeatType;
    private int numOfRows;
    private String vehicleCode;

    public void setNumOfRows(int i) {
        this.numOfRows = i;
    }

    public int getNumOfRows() {
        return this.numOfRows;
    }

    public void setDriverSeatType(String str) {
        this.driverSeatType = str;
    }

    public String getDriverSeatType() {
        return this.driverSeatType;
    }

    public void setVehicleCode(String str) {
        this.vehicleCode = str;
    }

    public String getVehicleCode() {
        return this.vehicleCode;
    }

    public static VehicleInfo fromJson(String str) throws JSONException {
        VehicleInfo vehicleInfo = new VehicleInfo();
        JSONObject jSONObject = new JSONObject(str);
        vehicleInfo.numOfRows = jSONObject.optInt("num_of_rows");
        vehicleInfo.driverSeatType = jSONObject.optString("driver_seat_type");
        vehicleInfo.vehicleCode = jSONObject.optString("vehicle_code");
        return vehicleInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("num_of_rows", this.numOfRows);
        jSONObject.put("driver_seat_type", this.driverSeatType);
        jSONObject.put("vehicle_code", this.vehicleCode);
        return jSONObject.toString();
    }
}
