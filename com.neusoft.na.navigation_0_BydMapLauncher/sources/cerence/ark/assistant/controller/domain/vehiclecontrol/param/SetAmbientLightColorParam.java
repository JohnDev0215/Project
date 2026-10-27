package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SetAmbientLightColorParam implements JsonCompliant {
    private String color;

    public void setColor(String str) {
        this.color = str;
    }

    public String getColor() {
        return this.color;
    }

    public static SetAmbientLightColorParam fromJson(String str) throws JSONException {
        SetAmbientLightColorParam setAmbientLightColorParam = new SetAmbientLightColorParam();
        setAmbientLightColorParam.color = new JSONObject(str).optString("color");
        return setAmbientLightColorParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("color", this.color);
        return jSONObject.toString();
    }
}
