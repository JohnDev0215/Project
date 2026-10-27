package cerence.ark.assistant.controller.domain.vehiclecontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class IsDarkOutsideResult extends BaseResult {
    private String isDark;

    public void setIsDark(String str) {
        this.isDark = str;
    }

    public String getIsDark() {
        return this.isDark;
    }

    public static IsDarkOutsideResult fromJson(String str) throws JSONException {
        IsDarkOutsideResult isDarkOutsideResult = new IsDarkOutsideResult();
        isDarkOutsideResult.isDark = new JSONObject(str).optString("is_dark");
        return isDarkOutsideResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("is_dark", this.isDark);
        return jSONObject.toString();
    }
}
