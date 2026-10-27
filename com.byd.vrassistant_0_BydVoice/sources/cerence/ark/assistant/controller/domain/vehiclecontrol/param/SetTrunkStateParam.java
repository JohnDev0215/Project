package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.controller.struct.TrunkStateInfo;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetTrunkStateParam implements JsonCompliant {
    private TrunkStateInfo trunkState;

    public void setTrunkState(TrunkStateInfo trunkStateInfo) {
        this.trunkState = trunkStateInfo;
    }

    public TrunkStateInfo getTrunkState() {
        return this.trunkState;
    }

    public static SetTrunkStateParam fromJson(String str) throws JSONException {
        SetTrunkStateParam setTrunkStateParam = new SetTrunkStateParam();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("trunk_state");
        if (jSONObjectOptJSONObject != null) {
            setTrunkStateParam.trunkState = TrunkStateInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return setTrunkStateParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.trunkState != null) {
            jSONObject.put("trunk_state", new JSONObject(this.trunkState.toJsonString()));
        }
        return jSONObject.toString();
    }
}
