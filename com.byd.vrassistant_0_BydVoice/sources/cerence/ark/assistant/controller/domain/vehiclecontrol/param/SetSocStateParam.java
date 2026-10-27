package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class SetSocStateParam implements JsonCompliant {
    private int socValue;

    public void setSocValue(int i) {
        this.socValue = i;
    }

    public int getSocValue() {
        return this.socValue;
    }

    public static SetSocStateParam fromJson(String str) throws JSONException {
        SetSocStateParam setSocStateParam = new SetSocStateParam();
        setSocStateParam.socValue = new JSONObject(str).optInt("soc_value");
        return setSocStateParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("soc_value", this.socValue);
        return jSONObject.toString();
    }
}
