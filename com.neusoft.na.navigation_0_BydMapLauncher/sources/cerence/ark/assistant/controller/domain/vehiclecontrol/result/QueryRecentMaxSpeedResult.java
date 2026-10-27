package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class QueryRecentMaxSpeedResult extends BaseResult {
    private boolean isDangerous;
    private int maxSpeed;
    private String unit;

    public void setMaxSpeed(int i) {
        this.maxSpeed = i;
    }

    public int getMaxSpeed() {
        return this.maxSpeed;
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

    public static QueryRecentMaxSpeedResult fromJson(String str) throws JSONException {
        QueryRecentMaxSpeedResult queryRecentMaxSpeedResult = new QueryRecentMaxSpeedResult();
        JSONObject jSONObject = new JSONObject(str);
        queryRecentMaxSpeedResult.maxSpeed = jSONObject.optInt("max_speed");
        queryRecentMaxSpeedResult.isDangerous = jSONObject.optBoolean("is_dangerous");
        queryRecentMaxSpeedResult.unit = jSONObject.optString("unit");
        return queryRecentMaxSpeedResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("max_speed", this.maxSpeed);
        jSONObject.put("is_dangerous", this.isDangerous);
        jSONObject.put("unit", this.unit);
        return jSONObject.toString();
    }
}
