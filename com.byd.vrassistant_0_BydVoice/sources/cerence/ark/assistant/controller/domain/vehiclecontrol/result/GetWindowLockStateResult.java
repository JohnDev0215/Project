package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.WindowLockStateInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetWindowLockStateResult extends BaseResult {
    private List<WindowLockStateInfo> windowlockStates;

    public void setWindowlockStates(List<WindowLockStateInfo> list) {
        this.windowlockStates = list;
    }

    public List<WindowLockStateInfo> getWindowlockStates() {
        return this.windowlockStates;
    }

    public static GetWindowLockStateResult fromJson(String str) throws JSONException {
        GetWindowLockStateResult getWindowLockStateResult = new GetWindowLockStateResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("windowlock_states");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(WindowLockStateInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        getWindowLockStateResult.windowlockStates = arrayList;
        return getWindowLockStateResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.windowlockStates != null) {
            for (int i = 0; i < this.windowlockStates.size(); i++) {
                jSONArray.put(new JSONObject(this.windowlockStates.get(i).toJsonString()));
            }
        }
        jSONObject.put("windowlock_states", jSONArray);
        return jSONObject.toString();
    }
}
