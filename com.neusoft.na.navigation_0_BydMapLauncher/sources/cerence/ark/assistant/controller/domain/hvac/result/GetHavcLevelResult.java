package cerence.ark.assistant.controller.domain.hvac.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.HVACFunctionLevelInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetHavcLevelResult extends BaseResult {
    private List<HVACFunctionLevelInfo> functionLevelList;

    public void setFunctionLevelList(List<HVACFunctionLevelInfo> list) {
        this.functionLevelList = list;
    }

    public List<HVACFunctionLevelInfo> getFunctionLevelList() {
        return this.functionLevelList;
    }

    public static GetHavcLevelResult fromJson(String str) throws JSONException {
        GetHavcLevelResult getHavcLevelResult = new GetHavcLevelResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("function_level_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(HVACFunctionLevelInfo.fromJson(jSONArrayOptJSONArray.get(i).toString()));
            }
        }
        getHavcLevelResult.functionLevelList = arrayList;
        return getHavcLevelResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.functionLevelList != null) {
            for (int i = 0; i < this.functionLevelList.size(); i++) {
                jSONArray.put(new JSONObject(this.functionLevelList.get(i).toJsonString()));
            }
        }
        jSONObject.put("function_level_list", jSONArray);
        return jSONObject.toString();
    }
}
