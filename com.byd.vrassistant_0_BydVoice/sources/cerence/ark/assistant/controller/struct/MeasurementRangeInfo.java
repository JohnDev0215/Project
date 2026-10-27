package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class MeasurementRangeInfo implements JsonCompliant {
    private MeasurementInfo high;
    private MeasurementInfo low;

    public void setHigh(MeasurementInfo measurementInfo) {
        this.high = measurementInfo;
    }

    public MeasurementInfo getHigh() {
        return this.high;
    }

    public void setLow(MeasurementInfo measurementInfo) {
        this.low = measurementInfo;
    }

    public MeasurementInfo getLow() {
        return this.low;
    }

    public static MeasurementRangeInfo fromJson(String str) throws JSONException {
        MeasurementRangeInfo measurementRangeInfo = new MeasurementRangeInfo();
        JSONObject jSONObject = new JSONObject(str);
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("high");
        if (jSONObjectOptJSONObject != null) {
            measurementRangeInfo.high = MeasurementInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("low");
        if (jSONObjectOptJSONObject2 != null) {
            measurementRangeInfo.low = MeasurementInfo.fromJson(jSONObjectOptJSONObject2.toString());
        }
        return measurementRangeInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.high != null) {
            jSONObject.put("high", new JSONObject(this.high.toJsonString()));
        }
        if (this.low != null) {
            jSONObject.put("low", new JSONObject(this.low.toJsonString()));
        }
        return jSONObject.toString();
    }
}
