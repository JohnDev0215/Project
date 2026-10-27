package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.controller.struct.LightBrightnessInfo;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetInteriorLightBrightnessParam implements JsonCompliant {
    private LightBrightnessInfo lightBrightness;
    private String lightType;

    public void setLightType(String str) {
        this.lightType = str;
    }

    public String getLightType() {
        return this.lightType;
    }

    public void setLightBrightness(LightBrightnessInfo lightBrightnessInfo) {
        this.lightBrightness = lightBrightnessInfo;
    }

    public LightBrightnessInfo getLightBrightness() {
        return this.lightBrightness;
    }

    public static SetInteriorLightBrightnessParam fromJson(String str) throws JSONException {
        SetInteriorLightBrightnessParam setInteriorLightBrightnessParam = new SetInteriorLightBrightnessParam();
        JSONObject jSONObject = new JSONObject(str);
        setInteriorLightBrightnessParam.lightType = jSONObject.optString("light_type");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("light_brightness");
        if (jSONObjectOptJSONObject != null) {
            setInteriorLightBrightnessParam.lightBrightness = LightBrightnessInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return setInteriorLightBrightnessParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("light_type", this.lightType);
        if (this.lightBrightness != null) {
            jSONObject.put("light_brightness", new JSONObject(this.lightBrightness.toJsonString()));
        }
        return jSONObject.toString();
    }
}
