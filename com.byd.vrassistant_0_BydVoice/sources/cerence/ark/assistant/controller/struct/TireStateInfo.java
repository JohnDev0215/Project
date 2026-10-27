package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class TireStateInfo implements JsonCompliant {
    private String unit;
    private List<TireInfo> values;

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

    public static TireStateInfo fromJson(String str) throws JSONException {
        TireStateInfo tireStateInfo = new TireStateInfo();
        JSONObject jSONObject = new JSONObject(str);
        tireStateInfo.unit = jSONObject.optString("unit");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("values");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(TireInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        tireStateInfo.values = arrayList;
        return tireStateInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
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
