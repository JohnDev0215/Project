package cerence.ark.assistant.controller.domain.radio.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class PlayRadioResult extends BaseResult {
    private double actualFreq;

    public void setActualFreq(double d) {
        this.actualFreq = d;
    }

    public double getActualFreq() {
        return this.actualFreq;
    }

    public static PlayRadioResult fromJson(String str) throws JSONException {
        PlayRadioResult playRadioResult = new PlayRadioResult();
        playRadioResult.actualFreq = new JSONObject(str).optDouble("actual_freq");
        return playRadioResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("actual_freq", this.actualFreq);
        return jSONObject.toString();
    }
}
