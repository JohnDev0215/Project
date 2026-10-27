package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.LightStateInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetInteriorLightStateResult extends BaseResult {
    private boolean isControlledGlobally;
    private List<LightStateInfo> states;

    public void setStates(List<LightStateInfo> list) {
        this.states = list;
    }

    public List<LightStateInfo> getStates() {
        return this.states;
    }

    public void setIsControlledGlobally(boolean z) {
        this.isControlledGlobally = z;
    }

    public boolean getIsControlledGlobally() {
        return this.isControlledGlobally;
    }

    public static GetInteriorLightStateResult fromJson(String str) throws JSONException {
        GetInteriorLightStateResult getInteriorLightStateResult = new GetInteriorLightStateResult();
        JSONObject jSONObject = new JSONObject(str);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("states");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(LightStateInfo.fromJson(jSONArrayOptJSONArray.optString(i)));
            }
        }
        getInteriorLightStateResult.states = arrayList;
        getInteriorLightStateResult.isControlledGlobally = jSONObject.optBoolean("is_controlled_globally");
        return getInteriorLightStateResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.states != null) {
            for (int i = 0; i < this.states.size(); i++) {
                jSONArray.put(new JSONObject(this.states.get(i).toJsonString()));
            }
        }
        jSONObject.put("states", jSONArray);
        jSONObject.put("is_controlled_globally", this.isControlledGlobally);
        return jSONObject.toString();
    }
}
