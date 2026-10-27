package cerence.ark.assistant.controller.domain.music.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetRecentPlayedSourceResult extends BaseResult {
    private String source;

    public void setSource(String str) {
        this.source = str;
    }

    public String getSource() {
        return this.source;
    }

    public static GetRecentPlayedSourceResult fromJson(String str) throws JSONException {
        GetRecentPlayedSourceResult getRecentPlayedSourceResult = new GetRecentPlayedSourceResult();
        getRecentPlayedSourceResult.source = new JSONObject(str).optString("source");
        return getRecentPlayedSourceResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("source", this.source);
        return jSONObject.toString();
    }
}
