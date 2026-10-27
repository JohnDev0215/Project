package cerence.ark.assistant.controller.domain.music.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CheckSourceAvailabilityParam implements JsonCompliant {
    private String source;

    public void setSource(String str) {
        this.source = str;
    }

    public String getSource() {
        return this.source;
    }

    public static CheckSourceAvailabilityParam fromJson(String str) throws JSONException {
        CheckSourceAvailabilityParam checkSourceAvailabilityParam = new CheckSourceAvailabilityParam();
        checkSourceAvailabilityParam.source = new JSONObject(str).optString("source");
        return checkSourceAvailabilityParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("source", this.source);
        return jSONObject.toString();
    }
}
