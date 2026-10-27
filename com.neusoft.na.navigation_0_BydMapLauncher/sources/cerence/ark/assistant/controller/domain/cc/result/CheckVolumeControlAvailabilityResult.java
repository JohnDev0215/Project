package cerence.ark.assistant.controller.domain.cc.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CheckVolumeControlAvailabilityResult extends BaseResult {
    private boolean isAvailable;

    public void setIsAvailable(boolean z) {
        this.isAvailable = z;
    }

    public boolean getIsAvailable() {
        return this.isAvailable;
    }

    public static CheckVolumeControlAvailabilityResult fromJson(String str) throws JSONException {
        CheckVolumeControlAvailabilityResult checkVolumeControlAvailabilityResult = new CheckVolumeControlAvailabilityResult();
        checkVolumeControlAvailabilityResult.isAvailable = new JSONObject(str).optBoolean("is_available");
        return checkVolumeControlAvailabilityResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_available", this.isAvailable);
        return jSONObject.toString();
    }
}
