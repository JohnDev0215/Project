package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetAmbientLightStateResult extends BaseResult {
    private int brightness;

    public void setBrightness(int i) {
        this.brightness = i;
    }

    public int getBrightness() {
        return this.brightness;
    }

    public static GetAmbientLightStateResult fromJson(String str) throws JSONException {
        GetAmbientLightStateResult getAmbientLightStateResult = new GetAmbientLightStateResult();
        getAmbientLightStateResult.brightness = new JSONObject(str).optInt("brightness");
        return getAmbientLightStateResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("brightness", this.brightness);
        return jSONObject.toString();
    }
}
