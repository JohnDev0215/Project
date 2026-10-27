package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.controller.struct.ChildLockStateInfo;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ControlChildLockParam implements JsonCompliant {
    private ChildLockStateInfo state;

    public void setState(ChildLockStateInfo childLockStateInfo) {
        this.state = childLockStateInfo;
    }

    public ChildLockStateInfo getState() {
        return this.state;
    }

    public static ControlChildLockParam fromJson(String str) throws JSONException {
        ControlChildLockParam controlChildLockParam = new ControlChildLockParam();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("state");
        if (jSONObjectOptJSONObject != null) {
            controlChildLockParam.state = ChildLockStateInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return controlChildLockParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.state != null) {
            jSONObject.put("state", new JSONObject(this.state.toJsonString()));
        }
        return jSONObject.toString();
    }
}
