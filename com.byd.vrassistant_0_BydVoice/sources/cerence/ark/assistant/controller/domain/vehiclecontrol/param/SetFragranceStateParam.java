package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetFragranceStateParam implements JsonCompliant {
    private String state;

    public void setState(String str) {
        this.state = str;
    }

    public String getState() {
        return this.state;
    }

    public static SetFragranceStateParam fromJson(String str) throws JSONException {
        SetFragranceStateParam setFragranceStateParam = new SetFragranceStateParam();
        setFragranceStateParam.state = new JSONObject(str).optString("state");
        return setFragranceStateParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("state", this.state);
        return jSONObject.toString();
    }
}
