package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.controller.struct.TireStateInfo;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class TireInformationParam implements JsonCompliant {
    private TireStateInfo state;

    public void setState(TireStateInfo tireStateInfo) {
        this.state = tireStateInfo;
    }

    public TireStateInfo getState() {
        return this.state;
    }

    public static TireInformationParam fromJson(String str) throws JSONException {
        TireInformationParam tireInformationParam = new TireInformationParam();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("state");
        if (jSONObjectOptJSONObject != null) {
            tireInformationParam.state = TireStateInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return tireInformationParam;
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
