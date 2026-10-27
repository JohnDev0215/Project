package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.AreaInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetWindowStateResult extends BaseResult {
    private List<AreaInfo> values;

    public void setValues(List<AreaInfo> list) {
        this.values = list;
    }

    public List<AreaInfo> getValues() {
        return this.values;
    }

    public static GetWindowStateResult fromJson(String str) throws JSONException {
        GetWindowStateResult getWindowStateResult = new GetWindowStateResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("values");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(AreaInfo.fromJson(jSONArrayOptJSONArray.get(i).toString()));
            }
        }
        getWindowStateResult.values = arrayList;
        return getWindowStateResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.values != null) {
            for (int i = 0; i < this.values.size(); i++) {
                jSONArray.put(new JSONObject(this.values.get(i).toJsonString()));
            }
        }
        jSONObject.put("values", jSONArray);
        return jSONObject.toString();
    }
}
