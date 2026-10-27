package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetScreenBrightnessParam implements JsonCompliant {
    private int screenBrightness;
    private String screenDevice;

    public void setScreenDevice(String str) {
        this.screenDevice = str;
    }

    public String getScreenDevice() {
        return this.screenDevice;
    }

    public void setScreenBrightness(int i) {
        this.screenBrightness = i;
    }

    public int getScreenBrightness() {
        return this.screenBrightness;
    }

    public static SetScreenBrightnessParam fromJson(String str) throws JSONException {
        SetScreenBrightnessParam setScreenBrightnessParam = new SetScreenBrightnessParam();
        JSONObject jSONObject = new JSONObject(str);
        setScreenBrightnessParam.screenDevice = jSONObject.optString("screen_device");
        setScreenBrightnessParam.screenBrightness = jSONObject.optInt(BYDAutoSysPropertiesDevice.SCREEN_BRIGHTNESS);
        return setScreenBrightnessParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("screen_device", this.screenDevice);
        jSONObject.put(BYDAutoSysPropertiesDevice.SCREEN_BRIGHTNESS, this.screenBrightness);
        return jSONObject.toString();
    }
}
