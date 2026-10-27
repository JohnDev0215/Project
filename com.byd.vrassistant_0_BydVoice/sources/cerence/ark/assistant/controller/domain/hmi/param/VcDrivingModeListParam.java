package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.controller.struct.DrivingModeInfo;
import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class VcDrivingModeListParam implements JsonCompliant {
    private List<DrivingModeInfo> drivingModeList;

    public void setDrivingModeList(List<DrivingModeInfo> list) {
        this.drivingModeList = list;
    }

    public List<DrivingModeInfo> getDrivingModeList() {
        return this.drivingModeList;
    }

    public static VcDrivingModeListParam fromJson(String str) throws JSONException {
        VcDrivingModeListParam vcDrivingModeListParam = new VcDrivingModeListParam();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("driving_mode_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(DrivingModeInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        vcDrivingModeListParam.drivingModeList = arrayList;
        return vcDrivingModeListParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.drivingModeList != null) {
            for (int i = 0; i < this.drivingModeList.size(); i++) {
                jSONArray.put(new JSONObject(this.drivingModeList.get(i).toJsonString()));
            }
        }
        jSONObject.put("driving_mode_list", jSONArray);
        return jSONObject.toString();
    }
}
