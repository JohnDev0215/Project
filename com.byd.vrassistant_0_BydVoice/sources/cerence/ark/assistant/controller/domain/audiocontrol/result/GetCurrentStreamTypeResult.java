package cerence.ark.assistant.controller.domain.audiocontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetCurrentStreamTypeResult extends BaseResult {
    private String streamType;

    public void setStreamType(String str) {
        this.streamType = str;
    }

    public String getStreamType() {
        return this.streamType;
    }

    public static GetCurrentStreamTypeResult fromJson(String str) throws JSONException {
        GetCurrentStreamTypeResult getCurrentStreamTypeResult = new GetCurrentStreamTypeResult();
        getCurrentStreamTypeResult.streamType = new JSONObject(str).optString("stream_type");
        return getCurrentStreamTypeResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("stream_type", this.streamType);
        return jSONObject.toString();
    }
}
