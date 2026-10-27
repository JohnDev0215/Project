package cerence.ark.assistant.controller.domain.cc.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetVolumeResult extends BaseResult {
    private int percentage;

    public void setPercentage(int i) {
        this.percentage = i;
    }

    public int getPercentage() {
        return this.percentage;
    }

    public static GetVolumeResult fromJson(String str) throws JSONException {
        GetVolumeResult getVolumeResult = new GetVolumeResult();
        getVolumeResult.percentage = new JSONObject(str).optInt("percentage");
        return getVolumeResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("percentage", this.percentage);
        return jSONObject.toString();
    }
}
