package cerence.ark.assistant.controller.domain.cc.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetActiveStreamTypeResult extends BaseResult {
    private String streamType;

    public void setStreamType(String str) {
        this.streamType = str;
    }

    public String getStreamType() {
        return this.streamType;
    }

    public static GetActiveStreamTypeResult fromJson(String str) throws JSONException {
        GetActiveStreamTypeResult getActiveStreamTypeResult = new GetActiveStreamTypeResult();
        getActiveStreamTypeResult.streamType = new JSONObject(str).optString("stream_type");
        return getActiveStreamTypeResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("stream_type", this.streamType);
        return jSONObject.toString();
    }
}
