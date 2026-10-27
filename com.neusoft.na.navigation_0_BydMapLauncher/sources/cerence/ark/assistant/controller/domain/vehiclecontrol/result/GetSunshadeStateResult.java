package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetSunshadeStateResult extends BaseResult {
    private int percentage;

    public void setPercentage(int i) {
        this.percentage = i;
    }

    public int getPercentage() {
        return this.percentage;
    }

    public static GetSunshadeStateResult fromJson(String str) throws JSONException {
        GetSunshadeStateResult getSunshadeStateResult = new GetSunshadeStateResult();
        getSunshadeStateResult.percentage = new JSONObject(str).optInt("percentage");
        return getSunshadeStateResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("percentage", this.percentage);
        return jSONObject.toString();
    }
}
