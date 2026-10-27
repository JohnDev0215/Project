package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ControlCameraRecordParam implements JsonCompliant {
    private String name;
    private String state;

    public void setName(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String getState() {
        return this.state;
    }

    public static ControlCameraRecordParam fromJson(String str) throws JSONException {
        ControlCameraRecordParam controlCameraRecordParam = new ControlCameraRecordParam();
        JSONObject jSONObject = new JSONObject(str);
        controlCameraRecordParam.name = jSONObject.optString("name");
        controlCameraRecordParam.state = jSONObject.optString("state");
        return controlCameraRecordParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", this.name);
        jSONObject.put("state", this.state);
        return jSONObject.toString();
    }
}
