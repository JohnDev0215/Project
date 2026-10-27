package cerence.ark.assistant.controller.domain.system.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetActiveMediaTypeBeforeConversationResult extends BaseResult {
    private String mediaType;

    public void setMediaType(String str) {
        this.mediaType = str;
    }

    public String getMediaType() {
        return this.mediaType;
    }

    public static GetActiveMediaTypeBeforeConversationResult fromJson(String str) throws JSONException {
        GetActiveMediaTypeBeforeConversationResult getActiveMediaTypeBeforeConversationResult = new GetActiveMediaTypeBeforeConversationResult();
        getActiveMediaTypeBeforeConversationResult.mediaType = new JSONObject(str).optString("media_type");
        return getActiveMediaTypeBeforeConversationResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("media_type", this.mediaType);
        return jSONObject.toString();
    }
}
