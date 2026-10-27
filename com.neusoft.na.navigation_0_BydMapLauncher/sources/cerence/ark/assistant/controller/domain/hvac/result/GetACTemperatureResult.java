package cerence.ark.assistant.controller.domain.hvac.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.ACTemperatureInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetACTemperatureResult extends BaseResult {
    private List<ACTemperatureInfo> temperatureInfoList;

    public void setTemperatureInfoList(List<ACTemperatureInfo> list) {
        this.temperatureInfoList = list;
    }

    public List<ACTemperatureInfo> getTemperatureInfoList() {
        return this.temperatureInfoList;
    }

    public static GetACTemperatureResult fromJson(String str) throws JSONException {
        GetACTemperatureResult getACTemperatureResult = new GetACTemperatureResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("temperature_info_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(ACTemperatureInfo.fromJson(jSONArrayOptJSONArray.get(i).toString()));
            }
        }
        getACTemperatureResult.temperatureInfoList = arrayList;
        return getACTemperatureResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.temperatureInfoList != null) {
            for (int i = 0; i < this.temperatureInfoList.size(); i++) {
                jSONArray.put(new JSONObject(this.temperatureInfoList.get(i).toJsonString()));
            }
        }
        jSONObject.put("temperature_info_list", jSONArray);
        return jSONObject.toString();
    }
}
