package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetSunroofStateResult extends BaseResult {
    private boolean isTilted;
    private int percentage;

    public void setPercentage(int i) {
        this.percentage = i;
    }

    public int getPercentage() {
        return this.percentage;
    }

    public void setIsTilted(boolean z) {
        this.isTilted = z;
    }

    public boolean getIsTilted() {
        return this.isTilted;
    }

    public static GetSunroofStateResult fromJson(String str) throws JSONException {
        GetSunroofStateResult getSunroofStateResult = new GetSunroofStateResult();
        JSONObject jSONObject = new JSONObject(str);
        getSunroofStateResult.percentage = jSONObject.optInt("percentage");
        getSunroofStateResult.isTilted = jSONObject.optBoolean("is_tilted");
        return getSunroofStateResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("percentage", this.percentage);
        jSONObject.put("is_tilted", this.isTilted);
        return jSONObject.toString();
    }
}
