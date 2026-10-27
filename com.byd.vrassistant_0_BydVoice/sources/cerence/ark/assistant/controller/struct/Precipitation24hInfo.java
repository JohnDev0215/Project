package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class Precipitation24hInfo implements JsonCompliant {
    private MeasurementInfo snowAmount;

    public void setSnowAmount(MeasurementInfo measurementInfo) {
        this.snowAmount = measurementInfo;
    }

    public MeasurementInfo getSnowAmount() {
        return this.snowAmount;
    }

    public static Precipitation24hInfo fromJson(String str) throws JSONException {
        Precipitation24hInfo precipitation24hInfo = new Precipitation24hInfo();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("snowAmount");
        if (jSONObjectOptJSONObject != null) {
            precipitation24hInfo.snowAmount = MeasurementInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return precipitation24hInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.snowAmount != null) {
            jSONObject.put("snowAmount", new JSONObject(this.snowAmount.toJsonString()));
        }
        return jSONObject.toString();
    }
}
