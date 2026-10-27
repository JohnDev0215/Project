package cerence.ark.assistant.controller.domain.dashcam.param;

import android.car.user.CarUserManager;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ManageRecordingsParam implements JsonCompliant {
    private String action;
    private String date;
    private String recordingType;

    public void setAction(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getDate() {
        return this.date;
    }

    public void setRecordingType(String str) {
        this.recordingType = str;
    }

    public String getRecordingType() {
        return this.recordingType;
    }

    public static ManageRecordingsParam fromJson(String str) throws JSONException {
        ManageRecordingsParam manageRecordingsParam = new ManageRecordingsParam();
        JSONObject jSONObject = new JSONObject(str);
        manageRecordingsParam.action = jSONObject.optString(CarUserManager.BUNDLE_PARAM_ACTION);
        manageRecordingsParam.date = jSONObject.optString("date");
        manageRecordingsParam.recordingType = jSONObject.optString("recording_type");
        return manageRecordingsParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(CarUserManager.BUNDLE_PARAM_ACTION, this.action);
        jSONObject.put("date", this.date);
        jSONObject.put("recording_type", this.recordingType);
        return jSONObject.toString();
    }
}
