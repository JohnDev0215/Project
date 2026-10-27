package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.DrivingModeInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetSupportedDrivingModeResult extends BaseResult {
    private List<DrivingModeInfo> modeList;

    public void setModeList(List<DrivingModeInfo> list) {
        this.modeList = list;
    }

    public List<DrivingModeInfo> getModeList() {
        return this.modeList;
    }

    public static GetSupportedDrivingModeResult fromJson(String str) throws JSONException {
        GetSupportedDrivingModeResult getSupportedDrivingModeResult = new GetSupportedDrivingModeResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("mode_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(DrivingModeInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        getSupportedDrivingModeResult.modeList = arrayList;
        return getSupportedDrivingModeResult;
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
