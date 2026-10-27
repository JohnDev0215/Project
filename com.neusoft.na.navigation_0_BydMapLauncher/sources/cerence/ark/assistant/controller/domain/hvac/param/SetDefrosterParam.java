package cerence.ark.assistant.controller.domain.hvac.param;

import android.car.user.CarUserManager;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SetDefrosterParam implements JsonCompliant {
    private String action;
    private String windshield;

    public void setWindshield(String str) {
        this.windshield = str;
    }

    public String getWindshield() {
        return this.windshield;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }

    public static SetDefrosterParam fromJson(String str) throws JSONException {
        SetDefrosterParam setDefrosterParam = new SetDefrosterParam();
        JSONObject jSONObject = new JSONObject(str);
        setDefrosterParam.windshield = jSONObject.optString("windshield");
        setDefrosterParam.action = jSONObject.optString(CarUserManager.BUNDLE_PARAM_ACTION);
        return setDefrosterParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("windshield", this.windshield);
        jSONObject.put(CarUserManager.BUNDLE_PARAM_ACTION, this.action);
        return jSONObject.toString();
    }
}
