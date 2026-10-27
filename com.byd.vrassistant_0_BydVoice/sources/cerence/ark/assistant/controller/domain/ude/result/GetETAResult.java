package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetETAResult extends BaseResult {
    private long duration;

    public void setDuration(long j) {
        this.duration = j;
    }

    public long getDuration() {
        return this.duration;
    }

    public static GetETAResult fromJson(String str) throws JSONException {
        GetETAResult getETAResult = new GetETAResult();
        getETAResult.duration = new JSONObject(str).optLong("duration");
        return getETAResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("duration", this.duration);
        return jSONObject.toString();
    }
}
