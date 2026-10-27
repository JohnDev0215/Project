package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetDistanceResult extends BaseResult {
    private double distance;
    private String promptText;
    private String unitOfDistance;

    public void setDistance(double d) {
        this.distance = d;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setUnitOfDistance(String str) {
        this.unitOfDistance = str;
    }

    public String getUnitOfDistance() {
        return this.unitOfDistance;
    }

    public void setPromptText(String str) {
        this.promptText = str;
    }

    public String getPromptText() {
        return this.promptText;
    }

    public static GetDistanceResult fromJson(String str) throws JSONException {
        GetDistanceResult getDistanceResult = new GetDistanceResult();
        JSONObject jSONObject = new JSONObject(str);
        getDistanceResult.distance = jSONObject.optDouble("distance");
        getDistanceResult.unitOfDistance = jSONObject.optString("unit_of_distance");
        getDistanceResult.promptText = jSONObject.optString("prompt_text");
        return getDistanceResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("distance", this.distance);
        jSONObject.put("unit_of_distance", this.unitOfDistance);
        jSONObject.put("prompt_text", this.promptText);
        return jSONObject.toString();
    }
}
