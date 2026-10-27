package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class DrivingModeInfo implements JsonCompliant {
    private String label;
    private String mode;

    public void setMode(String str) {
        this.mode = str;
    }

    public String getMode() {
        return this.mode;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public String getLabel() {
        return this.label;
    }

    public static DrivingModeInfo fromJson(String str) throws JSONException {
        DrivingModeInfo drivingModeInfo = new DrivingModeInfo();
        JSONObject jSONObject = new JSONObject(str);
        drivingModeInfo.mode = jSONObject.optString("mode");
        drivingModeInfo.label = jSONObject.optString("label");
        return drivingModeInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("mode", this.mode);
        jSONObject.put("label", this.label);
        return jSONObject.toString();
    }
}
