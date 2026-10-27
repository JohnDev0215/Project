package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.RoadModeInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetSupportedRoadModeResult extends BaseResult {
    private List<RoadModeInfo> modeList;

    public void setModeList(List<RoadModeInfo> list) {
        this.modeList = list;
    }

    public List<RoadModeInfo> getModeList() {
        return this.modeList;
    }

    public static GetSupportedRoadModeResult fromJson(String str) throws JSONException {
        GetSupportedRoadModeResult getSupportedRoadModeResult = new GetSupportedRoadModeResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("mode_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(RoadModeInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        getSupportedRoadModeResult.modeList = arrayList;
        return getSupportedRoadModeResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.modeList != null) {
            for (int i = 0; i < this.modeList.size(); i++) {
                jSONArray.put(new JSONObject(this.modeList.get(i).toJsonString()));
            }
        }
        jSONObject.put("mode_list", jSONArray);
        return jSONObject.toString();
    }
}
