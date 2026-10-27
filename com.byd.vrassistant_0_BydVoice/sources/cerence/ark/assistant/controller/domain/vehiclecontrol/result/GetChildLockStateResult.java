package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.ChildLockStateInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetChildLockStateResult extends BaseResult {
    private List<ChildLockStateInfo> childlockStates;

    public void setChildlockStates(List<ChildLockStateInfo> list) {
        this.childlockStates = list;
    }

    public List<ChildLockStateInfo> getChildlockStates() {
        return this.childlockStates;
    }

    public static GetChildLockStateResult fromJson(String str) throws JSONException {
        GetChildLockStateResult getChildLockStateResult = new GetChildLockStateResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("childlock_states");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(ChildLockStateInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        getChildLockStateResult.childlockStates = arrayList;
        return getChildLockStateResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.childlockStates != null) {
            for (int i = 0; i < this.childlockStates.size(); i++) {
                jSONArray.put(new JSONObject(this.childlockStates.get(i).toJsonString()));
            }
        }
        jSONObject.put("childlock_states", jSONArray);
        return jSONObject.toString();
    }
}
