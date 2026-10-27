package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetInteriorLightZonesResult extends BaseResult {
    private List<String> vehicleAreaList;

    public void setVehicleAreaList(List<String> list) {
        this.vehicleAreaList = list;
    }

    public List<String> getVehicleAreaList() {
        return this.vehicleAreaList;
    }

    public static GetInteriorLightZonesResult fromJson(String str) throws JSONException {
        GetInteriorLightZonesResult getInteriorLightZonesResult = new GetInteriorLightZonesResult();
        JSONArray jSONArrayOptJSONArray = new JSONObject(str).optJSONArray("vehicle_area_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(jSONArrayOptJSONArray.optString(i));
            }
        }
        getInteriorLightZonesResult.vehicleAreaList = arrayList;
        return getInteriorLightZonesResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.vehicleAreaList != null) {
            for (int i = 0; i < this.vehicleAreaList.size(); i++) {
                jSONArray.put(this.vehicleAreaList.get(i));
            }
        }
        jSONObject.put("vehicle_area_list", jSONArray);
        return jSONObject.toString();
    }
}
