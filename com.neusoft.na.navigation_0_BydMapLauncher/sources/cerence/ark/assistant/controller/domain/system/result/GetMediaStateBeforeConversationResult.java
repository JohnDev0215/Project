package cerence.ark.assistant.controller.domain.system.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetMediaStateBeforeConversationResult extends BaseResult {
    private String mediaState;

    public void setMediaState(String str) {
        this.mediaState = str;
    }

    public String getMediaState() {
        return this.mediaState;
    }

    public static GetMediaStateBeforeConversationResult fromJson(String str) throws JSONException {
        GetMediaStateBeforeConversationResult getMediaStateBeforeConversationResult = new GetMediaStateBeforeConversationResult();
        getMediaStateBeforeConversationResult.mediaState = new JSONObject(str).optString("media_state");
        return getMediaStateBeforeConversationResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("media_state", this.mediaState);
        return jSONObject.toString();
    }
}
