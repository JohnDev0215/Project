package cerence.ark.assistant.controller.domain.music.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CheckMusicAvailabilityResult extends BaseResult {
    private boolean isAvailable;
    private String source;

    public void setIsAvailable(boolean z) {
        this.isAvailable = z;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public void setSource(String str) {
        this.source = str;
    }

    public String getSource() {
        return this.source;
    }

    public static CheckMusicAvailabilityResult fromJson(String str) throws JSONException {
        CheckMusicAvailabilityResult checkMusicAvailabilityResult = new CheckMusicAvailabilityResult();
        JSONObject jSONObject = new JSONObject(str);
        checkMusicAvailabilityResult.isAvailable = jSONObject.optBoolean("is_available");
        checkMusicAvailabilityResult.source = jSONObject.optString("source");
        return checkMusicAvailabilityResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_available", this.isAvailable);
        jSONObject.put("source", this.source);
        return jSONObject.toString();
    }
}
