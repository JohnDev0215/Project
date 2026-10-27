package cerence.ark.assistant.controller.domain.audiocontrol.result;

import cerence.ark.assistant.api.BaseResult;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetRecentPlayedMediaProviderResult extends BaseResult {
    private String mediaProvider;

    public void setMediaProvider(String str) {
        this.mediaProvider = str;
    }

    public String getMediaProvider() {
        return this.mediaProvider;
    }

    public static GetRecentPlayedMediaProviderResult fromJson(String str) throws JSONException {
        GetRecentPlayedMediaProviderResult getRecentPlayedMediaProviderResult = new GetRecentPlayedMediaProviderResult();
        getRecentPlayedMediaProviderResult.mediaProvider = new JSONObject(str).optString("media_provider");
        return getRecentPlayedMediaProviderResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("media_provider", this.mediaProvider);
        return jSONObject.toString();
    }
}
