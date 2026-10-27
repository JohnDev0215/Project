package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.TireStateInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class QueryTirePressureResult extends BaseResult {
    private TireStateInfo state;

    public void setState(TireStateInfo tireStateInfo) {
        this.state = tireStateInfo;
    }

    public TireStateInfo getState() {
        return this.state;
    }

    public static QueryTirePressureResult fromJson(String str) throws JSONException {
        QueryTirePressureResult queryTirePressureResult = new QueryTirePressureResult();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("state");
        if (jSONObjectOptJSONObject != null) {
            queryTirePressureResult.state = TireStateInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return queryTirePressureResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.state != null) {
            jSONObject.put("state", new JSONObject(this.state.toJsonString()));
        }
        return jSONObject.toString();
    }
}
