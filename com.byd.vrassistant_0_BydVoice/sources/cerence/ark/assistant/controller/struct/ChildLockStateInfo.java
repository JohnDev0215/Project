package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ChildLockStateInfo implements JsonCompliant {
    private String state;
    private String vehicleArea;

    public void setVehicleArea(String str) {
        this.vehicleArea = str;
    }

    public String getVehicleArea() {
        return this.vehicleArea;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String getState() {
        return this.state;
    }

    public static ChildLockStateInfo fromJson(String str) throws JSONException {
        ChildLockStateInfo childLockStateInfo = new ChildLockStateInfo();
        JSONObject jSONObject = new JSONObject(str);
        childLockStateInfo.vehicleArea = jSONObject.optString("vehicle_area");
        childLockStateInfo.state = jSONObject.optString("state");
        return childLockStateInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("vehicle_area", this.vehicleArea);
        jSONObject.put("state", this.state);
        return jSONObject.toString();
    }
}
