package cerence.ark.assistant.controller.domain.hvac.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetDefrosterStateParam implements JsonCompliant {
    private String action;
    private String windshield;

    public void setWindshield(String str) {
        this.windshield = str;
    }

    public String getWindshield() {
        return this.windshield;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }

    public static SetDefrosterStateParam fromJson(String str) throws JSONException {
        SetDefrosterStateParam setDefrosterStateParam = new SetDefrosterStateParam();
        JSONObject jSONObject = new JSONObject(str);
        setDefrosterStateParam.windshield = jSONObject.optString("windshield");
        setDefrosterStateParam.action = jSONObject.optString("action");
        return setDefrosterStateParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("windshield", this.windshield);
        jSONObject.put("action", this.action);
        return jSONObject.toString();
    }
}
