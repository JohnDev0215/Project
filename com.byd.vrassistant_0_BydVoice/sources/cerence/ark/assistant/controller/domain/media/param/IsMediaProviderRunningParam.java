package cerence.ark.assistant.controller.domain.media.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class IsMediaProviderRunningParam implements JsonCompliant {
    private String mediaProvider;

    public void setMediaProvider(String str) {
        this.mediaProvider = str;
    }

    public String getMediaProvider() {
        return this.mediaProvider;
    }

    public static IsMediaProviderRunningParam fromJson(String str) throws JSONException {
        IsMediaProviderRunningParam isMediaProviderRunningParam = new IsMediaProviderRunningParam();
        isMediaProviderRunningParam.mediaProvider = new JSONObject(str).optString("media_provider");
        return isMediaProviderRunningParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("media_provider", this.mediaProvider);
        return jSONObject.toString();
    }
}
