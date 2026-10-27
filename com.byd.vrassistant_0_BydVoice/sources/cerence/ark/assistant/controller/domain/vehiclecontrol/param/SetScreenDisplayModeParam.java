package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetScreenDisplayModeParam implements JsonCompliant {
    private String screenDevice;
    private String screenDisplayMode;

    public void setScreenDevice(String str) {
        this.screenDevice = str;
    }

    public String getScreenDevice() {
        return this.screenDevice;
    }

    public void setScreenDisplayMode(String str) {
        this.screenDisplayMode = str;
    }

    public String getScreenDisplayMode() {
        return this.screenDisplayMode;
    }

    public static SetScreenDisplayModeParam fromJson(String str) throws JSONException {
        SetScreenDisplayModeParam setScreenDisplayModeParam = new SetScreenDisplayModeParam();
        JSONObject jSONObject = new JSONObject(str);
        setScreenDisplayModeParam.screenDevice = jSONObject.optString("screen_device");
        setScreenDisplayModeParam.screenDisplayMode = jSONObject.optString("screen_display_mode");
        return setScreenDisplayModeParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("screen_device", this.screenDevice);
        jSONObject.put("screen_display_mode", this.screenDisplayMode);
        return jSONObject.toString();
    }
}
