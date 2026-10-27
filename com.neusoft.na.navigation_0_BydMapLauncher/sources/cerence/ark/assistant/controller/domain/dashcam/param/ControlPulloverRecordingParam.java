package cerence.ark.assistant.controller.domain.dashcam.param;

import android.car.user.CarUserManager;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ControlPulloverRecordingParam implements JsonCompliant {
    private String action;

    public void setAction(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }

    public static ControlPulloverRecordingParam fromJson(String str) throws JSONException {
        ControlPulloverRecordingParam controlPulloverRecordingParam = new ControlPulloverRecordingParam();
        controlPulloverRecordingParam.action = new JSONObject(str).optString(CarUserManager.BUNDLE_PARAM_ACTION);
        return controlPulloverRecordingParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(CarUserManager.BUNDLE_PARAM_ACTION, this.action);
        return jSONObject.toString();
    }
}
