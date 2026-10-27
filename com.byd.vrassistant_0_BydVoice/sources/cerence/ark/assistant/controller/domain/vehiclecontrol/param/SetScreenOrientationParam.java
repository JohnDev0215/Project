package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetScreenOrientationParam implements JsonCompliant {
    private String orientation;
    private String screenDevice;

    public void setScreenDevice(String str) {
        this.screenDevice = str;
    }

    public String getScreenDevice() {
        return this.screenDevice;
    }

    public void setOrientation(String str) {
        this.orientation = str;
    }

    public String getOrientation() {
        return this.orientation;
    }

    public static SetScreenOrientationParam fromJson(String str) throws JSONException {
        SetScreenOrientationParam setScreenOrientationParam = new SetScreenOrientationParam();
        JSONObject jSONObject = new JSONObject(str);
        setScreenOrientationParam.screenDevice = jSONObject.optString("screen_device");
        setScreenOrientationParam.orientation = jSONObject.optString("orientation");
        return setScreenOrientationParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("screen_device", this.screenDevice);
        jSONObject.put("orientation", this.orientation);
        return jSONObject.toString();
    }
}
