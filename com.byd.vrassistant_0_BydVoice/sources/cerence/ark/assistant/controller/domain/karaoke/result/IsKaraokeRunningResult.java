package cerence.ark.assistant.controller.domain.karaoke.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class IsKaraokeRunningResult extends BaseResult {
    private boolean isRunning;

    public void setIsRunning(boolean z) {
        this.isRunning = z;
    }

    public boolean getIsRunning() {
        return this.isRunning;
    }

    public static IsKaraokeRunningResult fromJson(String str) throws JSONException {
        IsKaraokeRunningResult isKaraokeRunningResult = new IsKaraokeRunningResult();
        isKaraokeRunningResult.isRunning = new JSONObject(str).optBoolean("is_running");
        return isKaraokeRunningResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_running", this.isRunning);
        return jSONObject.toString();
    }
}
