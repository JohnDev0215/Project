package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SetAmbientLightStateParam implements JsonCompliant {
    private int brightness;

    public void setBrightness(int i) {
        this.brightness = i;
    }

    public int getBrightness() {
        return this.brightness;
    }

    public static SetAmbientLightStateParam fromJson(String str) throws JSONException {
        SetAmbientLightStateParam setAmbientLightStateParam = new SetAmbientLightStateParam();
        setAmbientLightStateParam.brightness = new JSONObject(str).optInt("brightness");
        return setAmbientLightStateParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("brightness", this.brightness);
        return jSONObject.toString();
    }
}
