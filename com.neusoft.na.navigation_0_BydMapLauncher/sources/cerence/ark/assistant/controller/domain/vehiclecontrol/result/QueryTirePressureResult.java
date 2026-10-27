package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.TireInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class QueryTirePressureResult extends BaseResult {
    private String unit;
    private List<TireInfo> values;
    private String vehicleType;

    public void setVehicleType(String str) {
        this.vehicleType = str;
    }

    public String getVehicleType() {
        return this.vehicleType;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setValues(List<TireInfo> list) {
        this.values = list;
    }

    public List<TireInfo> getValues() {
        return this.values;
    }

    public static QueryTirePressureResult fromJson(String str) throws JSONException {
        QueryTirePressureResult queryTirePressureResult = new QueryTirePressureResult();
        JSONObject jSONObject = new JSONObject(str);
        queryTirePressureResult.vehicleType = jSONObject.optString("vehicle_type");
        queryTirePressureResult.unit = jSONObject.optString("unit");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("values");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(TireInfo.fromJson(jSONArrayOptJSONArray.get(i).toString()));
            }
        }
        queryTirePressureResult.values = arrayList;
        return queryTirePressureResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("vehicle_type", this.vehicleType);
        jSONObject.put("unit", this.unit);
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
