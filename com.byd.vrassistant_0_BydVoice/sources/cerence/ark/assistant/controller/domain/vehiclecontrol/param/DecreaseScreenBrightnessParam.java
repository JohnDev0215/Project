package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class DecreaseScreenBrightnessParam implements JsonCompliant {
    private String screenDevice;

    public void setScreenDevice(String str) {
        this.screenDevice = str;
    }

    public String getScreenDevice() {
        return this.screenDevice;
    }

    public static DecreaseScreenBrightnessParam fromJson(String str) throws JSONException {
        DecreaseScreenBrightnessParam decreaseScreenBrightnessParam = new DecreaseScreenBrightnessParam();
        decreaseScreenBrightnessParam.screenDevice = new JSONObject(str).optString("screen_device");
        return decreaseScreenBrightnessParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("screen_device", this.screenDevice);
        return jSONObject.toString();
    }
}
