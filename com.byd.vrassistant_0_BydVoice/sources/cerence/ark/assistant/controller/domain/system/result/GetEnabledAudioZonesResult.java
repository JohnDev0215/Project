package cerence.ark.assistant.controller.domain.system.result;

import cerence.ark.assistant.api.BaseResult;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetEnabledAudioZonesResult extends BaseResult {
    private List<String> enabledZones;

    public void setEnabledZones(List<String> list) {
        this.enabledZones = list;
    }

    public List<String> getEnabledZones() {
        return this.enabledZones;
    }

    public static GetEnabledAudioZonesResult fromJson(String str) throws JSONException {
        GetEnabledAudioZonesResult getEnabledAudioZonesResult = new GetEnabledAudioZonesResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("enabled_zones");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(jSONArrayOptJSONArray.optString(i));
            }
        }
        getEnabledAudioZonesResult.enabledZones = arrayList;
        return getEnabledAudioZonesResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.enabledZones != null) {
            for (int i = 0; i < this.enabledZones.size(); i++) {
                jSONArray.put(this.enabledZones.get(i));
            }
        }
        jSONObject.put("enabled_zones", jSONArray);
        return jSONObject.toString();
    }
}
