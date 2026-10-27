package cerence.ark.assistant.controller.domain.phone.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CallOperationParam implements JsonCompliant {
    private String actionType;

    public void setActionType(String str) {
        this.actionType = str;
    }

    public String getActionType() {
        return this.actionType;
    }

    public static CallOperationParam fromJson(String str) throws JSONException {
        CallOperationParam callOperationParam = new CallOperationParam();
        callOperationParam.actionType = new JSONObject(str).optString("action_type");
        return callOperationParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("action_type", this.actionType);
        return jSONObject.toString();
    }
}
