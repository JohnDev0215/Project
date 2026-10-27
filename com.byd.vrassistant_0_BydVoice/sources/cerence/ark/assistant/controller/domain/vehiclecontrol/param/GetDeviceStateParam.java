package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetDeviceStateParam implements JsonCompliant {
    private String action;
    private String deviceType;

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }

    public static GetDeviceStateParam fromJson(String str) throws JSONException {
        GetDeviceStateParam getDeviceStateParam = new GetDeviceStateParam();
        JSONObject jSONObject = new JSONObject(str);
        getDeviceStateParam.deviceType = jSONObject.optString("device_type");
        getDeviceStateParam.action = jSONObject.optString("action");
        return getDeviceStateParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("device_type", this.deviceType);
        jSONObject.put("action", this.action);
        return jSONObject.toString();
    }
}
