package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SetHUDModeParam implements JsonCompliant {
    private String hudMode;

    public void setHudMode(String str) {
        this.hudMode = str;
    }

    public String getHudMode() {
        return this.hudMode;
    }

    public static SetHUDModeParam fromJson(String str) throws JSONException {
        SetHUDModeParam setHUDModeParam = new SetHUDModeParam();
        setHUDModeParam.hudMode = new JSONObject(str).optString("hud_mode");
        return setHUDModeParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("hud_mode", this.hudMode);
        return jSONObject.toString();
    }
}
