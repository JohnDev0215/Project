package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.controller.struct.CallLogInfo;
import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class PhoneCallLogParam implements JsonCompliant {
    private List<CallLogInfo> callLogList;
    private String callLogType;

    public void setCallLogType(String str) {
        this.callLogType = str;
    }

    public String getCallLogType() {
        return this.callLogType;
    }

    public void setCallLogList(List<CallLogInfo> list) {
        this.callLogList = list;
    }

    public List<CallLogInfo> getCallLogList() {
        return this.callLogList;
    }

    public static PhoneCallLogParam fromJson(String str) throws JSONException {
        PhoneCallLogParam phoneCallLogParam = new PhoneCallLogParam();
        JSONObject jSONObject = new JSONObject(str);
        phoneCallLogParam.callLogType = jSONObject.optString("call_log_type");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("call_log_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(CallLogInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        phoneCallLogParam.callLogList = arrayList;
        return phoneCallLogParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("call_log_type", this.callLogType);
        JSONArray jSONArray = new JSONArray();
        if (this.callLogList != null) {
            for (int i = 0; i < this.callLogList.size(); i++) {
                jSONArray.put(new JSONObject(this.callLogList.get(i).toJsonString()));
            }
        }
        jSONObject.put("call_log_list", jSONArray);
        return jSONObject.toString();
    }
}
