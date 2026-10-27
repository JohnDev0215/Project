package cerence.ark.assistant.controller.domain.system.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetExternalVoiceAssistantStatusParam implements JsonCompliant {
    private String externalVrType;

    public void setExternalVrType(String str) {
        this.externalVrType = str;
    }

    public String getExternalVrType() {
        return this.externalVrType;
    }

    public static GetExternalVoiceAssistantStatusParam fromJson(String str) throws JSONException {
        GetExternalVoiceAssistantStatusParam getExternalVoiceAssistantStatusParam = new GetExternalVoiceAssistantStatusParam();
        getExternalVoiceAssistantStatusParam.externalVrType = new JSONObject(str).optString("external_vr_type");
        return getExternalVoiceAssistantStatusParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("external_vr_type", this.externalVrType);
        return jSONObject.toString();
    }
}
