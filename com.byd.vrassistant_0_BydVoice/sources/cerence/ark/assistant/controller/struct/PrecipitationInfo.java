package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class PrecipitationInfo implements JsonCompliant {
    private MeasurementInfo amount;
    private int ice;
    private int probability;
    private int rain;
    private int snow;
    private MeasurementInfo snowAmount;

    public void setAmount(MeasurementInfo measurementInfo) {
        this.amount = measurementInfo;
    }

    public MeasurementInfo getAmount() {
        return this.amount;
    }

    public void setProbability(int i) {
        this.probability = i;
    }

    public int getProbability() {
        return this.probability;
    }

    public void setRain(int i) {
        this.rain = i;
    }

    public int getRain() {
        return this.rain;
    }

    public void setSnow(int i) {
        this.snow = i;
    }

    public int getSnow() {
        return this.snow;
    }

    public void setIce(int i) {
        this.ice = i;
    }

    public int getIce() {
        return this.ice;
    }

    public void setSnowAmount(MeasurementInfo measurementInfo) {
        this.snowAmount = measurementInfo;
    }

    public MeasurementInfo getSnowAmount() {
        return this.snowAmount;
    }

    public static PrecipitationInfo fromJson(String str) throws JSONException {
        PrecipitationInfo precipitationInfo = new PrecipitationInfo();
        JSONObject jSONObject = new JSONObject(str);
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("amount");
        if (jSONObjectOptJSONObject != null) {
            precipitationInfo.amount = MeasurementInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        precipitationInfo.probability = jSONObject.optInt("probability");
        precipitationInfo.rain = jSONObject.optInt("rain");
        precipitationInfo.snow = jSONObject.optInt("snow");
        precipitationInfo.ice = jSONObject.optInt("ice");
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("snowAmount");
        if (jSONObjectOptJSONObject2 != null) {
            precipitationInfo.snowAmount = MeasurementInfo.fromJson(jSONObjectOptJSONObject2.toString());
        }
        return precipitationInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.amount != null) {
            jSONObject.put("amount", new JSONObject(this.amount.toJsonString()));
        }
        jSONObject.put("probability", this.probability);
        jSONObject.put("rain", this.rain);
        jSONObject.put("snow", this.snow);
        jSONObject.put("ice", this.ice);
        if (this.snowAmount != null) {
            jSONObject.put("snowAmount", new JSONObject(this.snowAmount.toJsonString()));
        }
        return jSONObject.toString();
    }
}
