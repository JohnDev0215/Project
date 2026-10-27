package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.WindowStateInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetWindowStateResult extends BaseResult {
    private List<WindowStateInfo> windowStates;

    public void setWindowStates(List<WindowStateInfo> list) {
        this.windowStates = list;
    }

    public List<WindowStateInfo> getWindowStates() {
        return this.windowStates;
    }

    public static GetWindowStateResult fromJson(String str) throws JSONException {
        GetWindowStateResult getWindowStateResult = new GetWindowStateResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("window_states");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(WindowStateInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        getWindowStateResult.windowStates = arrayList;
        return getWindowStateResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.windowStates != null) {
            for (int i = 0; i < this.windowStates.size(); i++) {
                jSONArray.put(new JSONObject(this.windowStates.get(i).toJsonString()));
            }
        }
        jSONObject.put("window_states", jSONArray);
        return jSONObject.toString();
    }
}
