package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetFragranceStateResult extends BaseResult {
    private String state;

    public void setState(String str) {
        this.state = str;
    }

    public String getState() {
        return this.state;
    }

    public static GetFragranceStateResult fromJson(String str) throws JSONException {
        GetFragranceStateResult getFragranceStateResult = new GetFragranceStateResult();
        getFragranceStateResult.state = new JSONObject(str).optString("state");
        return getFragranceStateResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("state", this.state);
        return jSONObject.toString();
    }
}
