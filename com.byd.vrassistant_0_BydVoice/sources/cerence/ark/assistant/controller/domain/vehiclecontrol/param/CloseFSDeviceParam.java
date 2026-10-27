package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CloseFSDeviceParam implements JsonCompliant {
    private String deviceType;

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public static CloseFSDeviceParam fromJson(String str) throws JSONException {
        CloseFSDeviceParam closeFSDeviceParam = new CloseFSDeviceParam();
        closeFSDeviceParam.deviceType = new JSONObject(str).optString("device_type");
        return closeFSDeviceParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("device_type", this.deviceType);
        return jSONObject.toString();
    }
}
