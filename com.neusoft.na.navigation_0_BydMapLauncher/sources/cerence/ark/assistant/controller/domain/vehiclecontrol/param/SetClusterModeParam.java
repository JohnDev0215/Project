package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import android.car.user.CarUserManager;
import cerence.ark.assistant.core.JsonCompliant;
import com.neusoft.na.common.ServiceBase;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SetClusterModeParam implements JsonCompliant {
    private String action;
    private String mode;

    public void setMode(String str) {
        this.mode = str;
    }

    public String getMode() {
        return this.mode;
    }

    public void setAction(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }

    public static SetClusterModeParam fromJson(String str) throws JSONException {
        SetClusterModeParam setClusterModeParam = new SetClusterModeParam();
        JSONObject jSONObject = new JSONObject(str);
        setClusterModeParam.mode = jSONObject.optString(ServiceBase.PAYLOAD_MODE);
        setClusterModeParam.action = jSONObject.optString(CarUserManager.BUNDLE_PARAM_ACTION);
        return setClusterModeParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ServiceBase.PAYLOAD_MODE, this.mode);
        jSONObject.put(CarUserManager.BUNDLE_PARAM_ACTION, this.action);
        return jSONObject.toString();
    }
}
