package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetBlindsStateResult extends BaseResult {
    private int openPercentage;

    public void setOpenPercentage(int i) {
        this.openPercentage = i;
    }

    public int getOpenPercentage() {
        return this.openPercentage;
    }

    public static GetBlindsStateResult fromJson(String str) throws JSONException {
        GetBlindsStateResult getBlindsStateResult = new GetBlindsStateResult();
        getBlindsStateResult.openPercentage = new JSONObject(str).optInt("open_percentage");
        return getBlindsStateResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("open_percentage", this.openPercentage);
        return jSONObject.toString();
    }
}
