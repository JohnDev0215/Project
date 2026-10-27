package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import android.car.user.CarUserManager;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ControlPanoramicViewParam implements JsonCompliant {
    private String action;
    private String viewMode;

    public void setViewMode(String str) {
        this.viewMode = str;
    }

    public String getViewMode() {
        return this.viewMode;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }

    public static ControlPanoramicViewParam fromJson(String str) throws JSONException {
        ControlPanoramicViewParam controlPanoramicViewParam = new ControlPanoramicViewParam();
        JSONObject jSONObject = new JSONObject(str);
        controlPanoramicViewParam.viewMode = jSONObject.optString("view_mode");
        controlPanoramicViewParam.action = jSONObject.optString(CarUserManager.BUNDLE_PARAM_ACTION);
        return controlPanoramicViewParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("view_mode", this.viewMode);
        jSONObject.put(CarUserManager.BUNDLE_PARAM_ACTION, this.action);
        return jSONObject.toString();
    }
}
