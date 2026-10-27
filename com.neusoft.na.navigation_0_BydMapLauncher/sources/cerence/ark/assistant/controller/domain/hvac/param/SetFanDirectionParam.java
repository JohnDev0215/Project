package cerence.ark.assistant.controller.domain.hvac.param;

import android.car.user.CarUserManager;
import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SetFanDirectionParam implements JsonCompliant {
    private String action;
    private List<String> windDirectionList;

    public void setAction(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
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
        setFanDirectionParam.action = jSONObject.optString(CarUserManager.BUNDLE_PARAM_ACTION);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("wind_direction_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(jSONArrayOptJSONArray.get(i).toString());
            }
        }
        setFanDirectionParam.windDirectionList = arrayList;
        return setFanDirectionParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(CarUserManager.BUNDLE_PARAM_ACTION, this.action);
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
