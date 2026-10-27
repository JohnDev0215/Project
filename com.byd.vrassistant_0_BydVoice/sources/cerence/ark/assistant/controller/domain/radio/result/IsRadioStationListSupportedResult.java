package cerence.ark.assistant.controller.domain.radio.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class IsRadioStationListSupportedResult extends BaseResult {
    private boolean isSupported;

    public void setIsSupported(boolean z) {
        this.isSupported = z;
    }

    public boolean getIsSupported() {
        return this.isSupported;
    }

    public static IsRadioStationListSupportedResult fromJson(String str) throws JSONException {
        IsRadioStationListSupportedResult isRadioStationListSupportedResult = new IsRadioStationListSupportedResult();
        isRadioStationListSupportedResult.isSupported = new JSONObject(str).optBoolean("is_supported");
        return isRadioStationListSupportedResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_supported", this.isSupported);
        return jSONObject.toString();
    }
}
