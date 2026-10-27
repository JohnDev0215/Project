package cerence.ark.assistant.controller.domain.cc.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SetScreenBrightnessParam implements JsonCompliant {
    private int percentage;

    public void setPercentage(int i) {
        this.percentage = i;
    }

    public int getPercentage() {
        return this.percentage;
    }

    public static SetScreenBrightnessParam fromJson(String str) throws JSONException {
        SetScreenBrightnessParam setScreenBrightnessParam = new SetScreenBrightnessParam();
        setScreenBrightnessParam.percentage = new JSONObject(str).optInt("percentage");
        return setScreenBrightnessParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("percentage", this.percentage);
        return jSONObject.toString();
    }
}
