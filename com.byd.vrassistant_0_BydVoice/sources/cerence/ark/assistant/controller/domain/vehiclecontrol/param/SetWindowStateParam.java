package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.controller.struct.WindowStateInfo;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetWindowStateParam implements JsonCompliant {
    private WindowStateInfo windowStates;

    public void setWindowStates(WindowStateInfo windowStateInfo) {
        this.windowStates = windowStateInfo;
    }

    public WindowStateInfo getWindowStates() {
        return this.windowStates;
    }

    public static SetWindowStateParam fromJson(String str) throws JSONException {
        SetWindowStateParam setWindowStateParam = new SetWindowStateParam();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("window_states");
        if (jSONObjectOptJSONObject != null) {
            setWindowStateParam.windowStates = WindowStateInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return setWindowStateParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.windowStates != null) {
            jSONObject.put("window_states", new JSONObject(this.windowStates.toJsonString()));
        }
        return jSONObject.toString();
    }
}
