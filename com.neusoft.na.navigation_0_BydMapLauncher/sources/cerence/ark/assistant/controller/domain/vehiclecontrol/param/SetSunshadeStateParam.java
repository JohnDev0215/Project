package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SetSunshadeStateParam implements JsonCompliant {
    private int percentage;

    public void setPercentage(int i) {
        this.percentage = i;
    }

    public int getPercentage() {
        return this.percentage;
    }

    public static SetSunshadeStateParam fromJson(String str) throws JSONException {
        SetSunshadeStateParam setSunshadeStateParam = new SetSunshadeStateParam();
        setSunshadeStateParam.percentage = new JSONObject(str).optInt("percentage");
        return setSunshadeStateParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("percentage", this.percentage);
        return jSONObject.toString();
    }
}
