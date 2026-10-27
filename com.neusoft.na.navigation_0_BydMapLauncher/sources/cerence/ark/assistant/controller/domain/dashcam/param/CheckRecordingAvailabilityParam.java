package cerence.ark.assistant.controller.domain.dashcam.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CheckRecordingAvailabilityParam implements JsonCompliant {
    private String date;
    private String recordingType;

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

    public static CheckRecordingAvailabilityParam fromJson(String str) throws JSONException {
        CheckRecordingAvailabilityParam checkRecordingAvailabilityParam = new CheckRecordingAvailabilityParam();
        JSONObject jSONObject = new JSONObject(str);
        checkRecordingAvailabilityParam.date = jSONObject.optString("date");
        checkRecordingAvailabilityParam.recordingType = jSONObject.optString("recording_type");
        return checkRecordingAvailabilityParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("date", this.date);
        jSONObject.put("recording_type", this.recordingType);
        return jSONObject.toString();
    }
}
