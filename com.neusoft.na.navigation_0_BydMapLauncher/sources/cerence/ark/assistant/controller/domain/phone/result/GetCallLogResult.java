package cerence.ark.assistant.controller.domain.phone.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.CallLogInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetCallLogResult extends BaseResult {
    private List<CallLogInfo> callLogList;

    public void setCallLogList(List<CallLogInfo> list) {
        this.callLogList = list;
    }

    public List<CallLogInfo> getCallLogList() {
        return this.callLogList;
    }

    public static GetCallLogResult fromJson(String str) throws JSONException {
        GetCallLogResult getCallLogResult = new GetCallLogResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("call_log_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(CallLogInfo.fromJson(jSONArrayOptJSONArray.get(i).toString()));
            }
        }
        getCallLogResult.callLogList = arrayList;
        return getCallLogResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
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
