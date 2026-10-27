package cerence.ark.assistant.controller.domain.hvac.param;

import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetFanDirectionParam implements JsonCompliant {
    private String action;
    private String vehicleArea;
    private List<String> windDirectionList;

    public void setAction(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }

    public void setVehicleArea(String str) {
        this.vehicleArea = str;
    }

    public String getVehicleArea() {
        return this.vehicleArea;
    }

    public void setWindDirectionList(List<String> list) {
        this.windDirectionList = list;
    }

    public List<String> getWindDirectionList() {
        return this.windDirectionList;
    }

    public static SetFanDirectionParam fromJson(String str) throws JSONException {
        SetFanDirectionParam setFanDirectionParam = new SetFanDirectionParam();
        JSONObject jSONObject = new JSONObject(str);
        setFanDirectionParam.action = jSONObject.optString("action");
        setFanDirectionParam.vehicleArea = jSONObject.optString("vehicle_area");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("wind_direction_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(jSONArrayOptJSONArray.optString(i));
            }
        }
        setFanDirectionParam.windDirectionList = arrayList;
        return setFanDirectionParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("action", this.action);
        jSONObject.put("vehicle_area", this.vehicleArea);
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
