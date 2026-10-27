package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class DefrosterStateInfo implements JsonCompliant {
    private String state;
    private String windshieldType;

    public void setWindshieldType(String str) {
        this.windshieldType = str;
    }

    public String getWindshieldType() {
        return this.windshieldType;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String getState() {
        return this.state;
    }

    public static DefrosterStateInfo fromJson(String str) throws JSONException {
        DefrosterStateInfo defrosterStateInfo = new DefrosterStateInfo();
        JSONObject jSONObject = new JSONObject(str);
        defrosterStateInfo.windshieldType = jSONObject.optString("windshield_type");
        defrosterStateInfo.state = jSONObject.optString("state");
        return defrosterStateInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("windshield_type", this.windshieldType);
        jSONObject.put("state", this.state);
        return jSONObject.toString();
    }
}
