package cerence.ark.assistant.controller.domain.phone.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetCallLogParam implements JsonCompliant {
    private String callLogType;

    public void setCallLogType(String str) {
        this.callLogType = str;
    }

    public String getCallLogType() {
        return this.callLogType;
    }

    public static GetCallLogParam fromJson(String str) throws JSONException {
        GetCallLogParam getCallLogParam = new GetCallLogParam();
        getCallLogParam.callLogType = new JSONObject(str).optString("call_log_type");
        return getCallLogParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("call_log_type", this.callLogType);
        return jSONObject.toString();
    }
}
