package cerence.ark.assistant.controller.domain.hvac.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.HVACStateInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetHvacStateResult extends BaseResult {
    private List<HVACStateInfo> stateList;

    public void setStateList(List<HVACStateInfo> list) {
        this.stateList = list;
    }

    public List<HVACStateInfo> getStateList() {
        return this.stateList;
    }

    public static GetHvacStateResult fromJson(String str) throws JSONException {
        GetHvacStateResult getHvacStateResult = new GetHvacStateResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("state_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(HVACStateInfo.fromJson(jSONArrayOptJSONArray.get(i).toString()));
            }
        }
        getHvacStateResult.stateList = arrayList;
        return getHvacStateResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.stateList != null) {
            for (int i = 0; i < this.stateList.size(); i++) {
                jSONArray.put(new JSONObject(this.stateList.get(i).toJsonString()));
            }
        }
        jSONObject.put("state_list", jSONArray);
        return jSONObject.toString();
    }
}
