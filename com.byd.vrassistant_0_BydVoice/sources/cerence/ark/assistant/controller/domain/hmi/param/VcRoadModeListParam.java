package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.controller.struct.RoadModeInfo;
import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class VcRoadModeListParam implements JsonCompliant {
    private List<RoadModeInfo> roadModeList;

    public void setRoadModeList(List<RoadModeInfo> list) {
        this.roadModeList = list;
    }

    public List<RoadModeInfo> getRoadModeList() {
        return this.roadModeList;
    }

    public static VcRoadModeListParam fromJson(String str) throws JSONException {
        VcRoadModeListParam vcRoadModeListParam = new VcRoadModeListParam();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("road_mode_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(RoadModeInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        vcRoadModeListParam.roadModeList = arrayList;
        return vcRoadModeListParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.roadModeList != null) {
            for (int i = 0; i < this.roadModeList.size(); i++) {
                jSONArray.put(new JSONObject(this.roadModeList.get(i).toJsonString()));
            }
        }
        jSONObject.put("road_mode_list", jSONArray);
        return jSONObject.toString();
    }
}
