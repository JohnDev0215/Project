package cerence.ark.assistant.controller.domain.ude.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetETAResult extends BaseResult {
    private long duration;
    private String promptText;

    public void setDuration(long j) {
        this.duration = j;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setPromptText(String str) {
        this.promptText = str;
    }

    public String getPromptText() {
        return this.promptText;
    }

    public static GetETAResult fromJson(String str) throws JSONException {
        GetETAResult getETAResult = new GetETAResult();
        JSONObject jSONObject = new JSONObject(str);
        getETAResult.duration = jSONObject.optLong("duration");
        getETAResult.promptText = jSONObject.optString("prompt_text");
        return getETAResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("duration", this.duration);
        jSONObject.put("prompt_text", this.promptText);
        return jSONObject.toString();
    }
}
