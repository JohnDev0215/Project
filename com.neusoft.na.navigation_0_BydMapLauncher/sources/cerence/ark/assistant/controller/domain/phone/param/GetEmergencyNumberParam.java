package cerence.ark.assistant.controller.domain.phone.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetEmergencyNumberParam implements JsonCompliant {
    private String emergencyType;

    public void setEmergencyType(String str) {
        this.emergencyType = str;
    }

    public String getEmergencyType() {
        return this.emergencyType;
    }

    public static GetEmergencyNumberParam fromJson(String str) throws JSONException {
        GetEmergencyNumberParam getEmergencyNumberParam = new GetEmergencyNumberParam();
        getEmergencyNumberParam.emergencyType = new JSONObject(str).optString("emergency_type");
        return getEmergencyNumberParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("emergency_type", this.emergencyType);
        return jSONObject.toString();
    }
}
