package cerence.ark.assistant.controller.domain.hmi.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class KnowledgeParam implements JsonCompliant {
    private String systemResponse;
    private String userUtterance;

    public void setUserUtterance(String str) {
        this.userUtterance = str;
    }

    public String getUserUtterance() {
        return this.userUtterance;
    }

    public void setSystemResponse(String str) {
        this.systemResponse = str;
    }

    public String getSystemResponse() {
        return this.systemResponse;
    }

    public static KnowledgeParam fromJson(String str) throws JSONException {
        KnowledgeParam knowledgeParam = new KnowledgeParam();
        JSONObject jSONObject = new JSONObject(str);
        knowledgeParam.userUtterance = jSONObject.optString("user_utterance");
        knowledgeParam.systemResponse = jSONObject.optString("system_response");
        return knowledgeParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("user_utterance", this.userUtterance);
        jSONObject.put("system_response", this.systemResponse);
        return jSONObject.toString();
    }
}
