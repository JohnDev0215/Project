package cerence.ark.assistant.controller.domain.system.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetEnabledAudioZonesParam implements JsonCompliant {
    private String activeZone;
    private String mode;

    public void setActiveZone(String str) {
        this.activeZone = str;
    }

    public String getActiveZone() {
        return this.activeZone;
    }

    public void setMode(String str) {
        this.mode = str;
    }

    public String getMode() {
        return this.mode;
    }

    public static GetEnabledAudioZonesParam fromJson(String str) throws JSONException {
        GetEnabledAudioZonesParam getEnabledAudioZonesParam = new GetEnabledAudioZonesParam();
        JSONObject jSONObject = new JSONObject(str);
        getEnabledAudioZonesParam.activeZone = jSONObject.optString("active_zone");
        getEnabledAudioZonesParam.mode = jSONObject.optString("mode");
        return getEnabledAudioZonesParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("active_zone", this.activeZone);
        jSONObject.put("mode", this.mode);
        return jSONObject.toString();
    }
}
