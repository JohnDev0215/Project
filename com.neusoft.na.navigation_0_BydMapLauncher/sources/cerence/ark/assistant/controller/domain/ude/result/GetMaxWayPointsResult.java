package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetMaxWayPointsResult extends BaseResult {
    private int number;

    public void setNumber(int i) {
        this.number = i;
    }

    public int getNumber() {
        return this.number;
    }

    public static GetMaxWayPointsResult fromJson(String str) throws JSONException {
        GetMaxWayPointsResult getMaxWayPointsResult = new GetMaxWayPointsResult();
        getMaxWayPointsResult.number = new JSONObject(str).optInt("number");
        return getMaxWayPointsResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("number", this.number);
        return jSONObject.toString();
    }
}
