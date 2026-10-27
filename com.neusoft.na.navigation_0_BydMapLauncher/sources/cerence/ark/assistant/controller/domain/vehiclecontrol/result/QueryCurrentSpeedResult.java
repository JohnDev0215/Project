package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class QueryCurrentSpeedResult extends BaseResult {
    private int currentSpeed;
    private boolean isDangerous;
    private String unit;

    public void setCurrentSpeed(int i) {
        this.currentSpeed = i;
    }

    public int getCurrentSpeed() {
        return this.currentSpeed;
    }

    public void setIsDangerous(boolean z) {
        this.isDangerous = z;
    }

    public boolean getIsDangerous() {
        return this.isDangerous;
    }

    public void setUnit(String str) {
        this.unit = str;
    }

    public String getUnit() {
        return this.unit;
    }

    public static QueryCurrentSpeedResult fromJson(String str) throws JSONException {
        QueryCurrentSpeedResult queryCurrentSpeedResult = new QueryCurrentSpeedResult();
        JSONObject jSONObject = new JSONObject(str);
        queryCurrentSpeedResult.currentSpeed = jSONObject.optInt("current_speed");
        queryCurrentSpeedResult.isDangerous = jSONObject.optBoolean("is_dangerous");
        queryCurrentSpeedResult.unit = jSONObject.optString("unit");
        return queryCurrentSpeedResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("current_speed", this.currentSpeed);
        jSONObject.put("is_dangerous", this.isDangerous);
        jSONObject.put("unit", this.unit);
        return jSONObject.toString();
    }
}
