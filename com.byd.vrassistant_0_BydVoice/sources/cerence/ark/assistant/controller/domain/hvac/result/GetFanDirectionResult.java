package cerence.ark.assistant.controller.domain.hvac.result;

import cerence.ark.assistant.api.BaseResult;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetFanDirectionResult extends BaseResult {
    private List<String> windDirectionList;

    public void setWindDirectionList(List<String> list) {
        this.windDirectionList = list;
    }

    public List<String> getWindDirectionList() {
        return this.windDirectionList;
    }

    public static GetFanDirectionResult fromJson(String str) throws JSONException {
        GetFanDirectionResult getFanDirectionResult = new GetFanDirectionResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("wind_direction_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(jSONArrayOptJSONArray.optString(i));
            }
        }
        getFanDirectionResult.windDirectionList = arrayList;
        return getFanDirectionResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.windDirectionList != null) {
            for (int i = 0; i < this.windDirectionList.size(); i++) {
                jSONArray.put(this.windDirectionList.get(i));
            }
        }
        jSONObject.put("wind_direction_list", jSONArray);
        return jSONObject.toString();
    }
}
