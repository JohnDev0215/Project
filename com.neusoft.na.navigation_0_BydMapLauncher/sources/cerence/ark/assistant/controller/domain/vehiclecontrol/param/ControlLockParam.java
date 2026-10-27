package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import android.car.user.CarUserManager;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ControlLockParam implements JsonCompliant {
    private String action;

    public void setAction(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }

    public static ControlLockParam fromJson(String str) throws JSONException {
        ControlLockParam controlLockParam = new ControlLockParam();
        controlLockParam.action = new JSONObject(str).optString(CarUserManager.BUNDLE_PARAM_ACTION);
        return controlLockParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(CarUserManager.BUNDLE_PARAM_ACTION, this.action);
        return jSONObject.toString();
    }
}
