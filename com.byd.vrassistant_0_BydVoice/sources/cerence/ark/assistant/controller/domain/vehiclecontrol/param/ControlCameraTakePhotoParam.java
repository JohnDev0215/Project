package cerence.ark.assistant.controller.domain.vehiclecontrol.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ControlCameraTakePhotoParam implements JsonCompliant {
    private String name;

    public void setName(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public static ControlCameraTakePhotoParam fromJson(String str) throws JSONException {
        ControlCameraTakePhotoParam controlCameraTakePhotoParam = new ControlCameraTakePhotoParam();
        controlCameraTakePhotoParam.name = new JSONObject(str).optString("name");
        return controlCameraTakePhotoParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("name", this.name);
        return jSONObject.toString();
    }
}
