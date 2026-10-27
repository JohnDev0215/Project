package cerence.ark.assistant.controller.domain.hvac.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetDefrosterParam implements JsonCompliant {
    private String windshield;

    public void setWindshield(String str) {
        this.windshield = str;
    }

    public String getWindshield() {
        return this.windshield;
    }

    public static GetDefrosterParam fromJson(String str) throws JSONException {
        GetDefrosterParam getDefrosterParam = new GetDefrosterParam();
        getDefrosterParam.windshield = new JSONObject(str).optString("windshield");
        return getDefrosterParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("windshield", this.windshield);
        return jSONObject.toString();
    }
}
