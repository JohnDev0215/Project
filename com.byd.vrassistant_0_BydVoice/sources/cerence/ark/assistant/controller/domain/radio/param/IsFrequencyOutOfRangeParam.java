package cerence.ark.assistant.controller.domain.radio.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class IsFrequencyOutOfRangeParam implements JsonCompliant {
    private double freq;

    public void setFreq(double d) {
        this.freq = d;
    }

    public double getFreq() {
        return this.freq;
    }

    public static IsFrequencyOutOfRangeParam fromJson(String str) throws JSONException {
        IsFrequencyOutOfRangeParam isFrequencyOutOfRangeParam = new IsFrequencyOutOfRangeParam();
        isFrequencyOutOfRangeParam.freq = new JSONObject(str).optDouble("freq");
        return isFrequencyOutOfRangeParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("freq", this.freq);
        return jSONObject.toString();
    }
}
