package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetScreenOnOffParam implements JsonCompliant {
    private String screenDevice;
    private String state;

    public void setScreenDevice(String str) {
        this.screenDevice = str;
    }

    public String getScreenDevice() {
        return this.screenDevice;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String getState() {
        return this.state;
    }

    public static SetScreenOnOffParam fromJson(String str) throws JSONException {
        SetScreenOnOffParam setScreenOnOffParam = new SetScreenOnOffParam();
        JSONObject jSONObject = new JSONObject(str);
        setScreenOnOffParam.screenDevice = jSONObject.optString("screen_device");
        setScreenOnOffParam.state = jSONObject.optString("state");
        return setScreenOnOffParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("screen_device", this.screenDevice);
        jSONObject.put("state", this.state);
        return jSONObject.toString();
    }
}
