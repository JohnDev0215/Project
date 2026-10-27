package cerence.ark.assistant.controller.domain.radio.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class IsFrequencyOutOfRangeResult extends BaseResult {
    private boolean isOutOfRange;

    public void setIsOutOfRange(boolean z) {
        this.isOutOfRange = z;
    }

    public boolean getIsOutOfRange() {
        return this.isOutOfRange;
    }

    public static IsFrequencyOutOfRangeResult fromJson(String str) throws JSONException {
        IsFrequencyOutOfRangeResult isFrequencyOutOfRangeResult = new IsFrequencyOutOfRangeResult();
        isFrequencyOutOfRangeResult.isOutOfRange = new JSONObject(str).optBoolean("is_out_of_range");
        return isFrequencyOutOfRangeResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_out_of_range", this.isOutOfRange);
        return jSONObject.toString();
    }
}
