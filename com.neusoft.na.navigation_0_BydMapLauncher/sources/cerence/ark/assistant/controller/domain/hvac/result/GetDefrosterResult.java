package cerence.ark.assistant.controller.domain.hvac.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.DefrosterStateInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetDefrosterResult extends BaseResult {
    private List<DefrosterStateInfo> defrosterStateList;

    public void setDefrosterStateList(List<DefrosterStateInfo> list) {
        this.defrosterStateList = list;
    }

    public List<DefrosterStateInfo> getDefrosterStateList() {
        return this.defrosterStateList;
    }

    public static GetDefrosterResult fromJson(String str) throws JSONException {
        GetDefrosterResult getDefrosterResult = new GetDefrosterResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("defroster_state_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(DefrosterStateInfo.fromJson(jSONArrayOptJSONArray.get(i).toString()));
            }
        }
        getDefrosterResult.defrosterStateList = arrayList;
        return getDefrosterResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.defrosterStateList != null) {
            for (int i = 0; i < this.defrosterStateList.size(); i++) {
                jSONArray.put(new JSONObject(this.defrosterStateList.get(i).toJsonString()));
            }
        }
        jSONObject.put("defroster_state_list", jSONArray);
        return jSONObject.toString();
    }
}
