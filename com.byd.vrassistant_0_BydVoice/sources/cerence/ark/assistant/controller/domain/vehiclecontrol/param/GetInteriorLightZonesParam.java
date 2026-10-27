package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetInteriorLightZonesParam implements JsonCompliant {
    private String lightType;

    public void setLightType(String str) {
        this.lightType = str;
    }

    public String getLightType() {
        return this.lightType;
    }

    public static GetInteriorLightZonesParam fromJson(String str) throws JSONException {
        GetInteriorLightZonesParam getInteriorLightZonesParam = new GetInteriorLightZonesParam();
        getInteriorLightZonesParam.lightType = new JSONObject(str).optString("light_type");
        return getInteriorLightZonesParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("light_type", this.lightType);
        return jSONObject.toString();
    }
}
