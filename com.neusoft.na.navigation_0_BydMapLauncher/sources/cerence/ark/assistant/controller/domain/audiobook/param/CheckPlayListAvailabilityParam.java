package cerence.ark.assistant.controller.domain.audiobook.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CheckPlayListAvailabilityParam implements JsonCompliant {
    private String playListType;

    public void setPlayListType(String str) {
        this.playListType = str;
    }

    public String getPlayListType() {
        return this.playListType;
    }

    public static CheckPlayListAvailabilityParam fromJson(String str) throws JSONException {
        CheckPlayListAvailabilityParam checkPlayListAvailabilityParam = new CheckPlayListAvailabilityParam();
        checkPlayListAvailabilityParam.playListType = new JSONObject(str).optString("play_list_type");
        return checkPlayListAvailabilityParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("play_list_type", this.playListType);
        return jSONObject.toString();
    }
}
