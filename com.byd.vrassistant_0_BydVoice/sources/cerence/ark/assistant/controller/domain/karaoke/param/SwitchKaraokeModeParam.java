package cerence.ark.assistant.controller.domain.karaoke.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SwitchKaraokeModeParam implements JsonCompliant {
    private String mode;

    public void setMode(String str) {
        this.mode = str;
    }

    public String getMode() {
        return this.mode;
    }

    public static SwitchKaraokeModeParam fromJson(String str) throws JSONException {
        SwitchKaraokeModeParam switchKaraokeModeParam = new SwitchKaraokeModeParam();
        switchKaraokeModeParam.mode = new JSONObject(str).optString("mode");
        return switchKaraokeModeParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("mode", this.mode);
        return jSONObject.toString();
    }
}
