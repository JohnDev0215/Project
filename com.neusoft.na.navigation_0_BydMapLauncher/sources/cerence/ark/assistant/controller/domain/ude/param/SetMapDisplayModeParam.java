package cerence.ark.assistant.controller.domain.ude.param;

import cerence.ark.assistant.core.JsonCompliant;
import com.neusoft.na.common.ServiceBase;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class SetMapDisplayModeParam implements JsonCompliant {
    private String mode;

    public void setMode(String str) {
        this.mode = str;
    }

    public String getMode() {
        return this.mode;
    }

    public static SetMapDisplayModeParam fromJson(String str) throws JSONException {
        SetMapDisplayModeParam setMapDisplayModeParam = new SetMapDisplayModeParam();
        setMapDisplayModeParam.mode = new JSONObject(str).optString(ServiceBase.PAYLOAD_MODE);
        return setMapDisplayModeParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(ServiceBase.PAYLOAD_MODE, this.mode);
        return jSONObject.toString();
    }
}
