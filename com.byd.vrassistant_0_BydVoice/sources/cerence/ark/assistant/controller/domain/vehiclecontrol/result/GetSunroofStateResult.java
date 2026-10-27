package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetSunroofStateResult extends BaseResult {
    private boolean isTilted;
    private int openPercentage;

    public void setOpenPercentage(int i) {
        this.openPercentage = i;
    }

    public int getOpenPercentage() {
        return this.openPercentage;
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
        getSunroofStateResult.openPercentage = jSONObject.optInt("open_percentage");
        getSunroofStateResult.isTilted = jSONObject.optBoolean("is_tilted");
        return getSunroofStateResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("open_percentage", this.openPercentage);
        jSONObject.put("is_tilted", this.isTilted);
        return jSONObject.toString();
    }
}
