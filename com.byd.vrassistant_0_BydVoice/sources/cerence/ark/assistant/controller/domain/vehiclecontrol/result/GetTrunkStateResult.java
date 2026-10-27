package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.TrunkStateInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetTrunkStateResult extends BaseResult {
    private TrunkStateInfo trunkStates;

    public void setTrunkStates(TrunkStateInfo trunkStateInfo) {
        this.trunkStates = trunkStateInfo;
    }

    public TrunkStateInfo getTrunkStates() {
        return this.trunkStates;
    }

    public static GetTrunkStateResult fromJson(String str) throws JSONException {
        GetTrunkStateResult getTrunkStateResult = new GetTrunkStateResult();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("trunk_states");
        if (jSONObjectOptJSONObject != null) {
            getTrunkStateResult.trunkStates = TrunkStateInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return getTrunkStateResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.trunkStates != null) {
            jSONObject.put("trunk_states", new JSONObject(this.trunkStates.toJsonString()));
        }
        return jSONObject.toString();
    }
}
