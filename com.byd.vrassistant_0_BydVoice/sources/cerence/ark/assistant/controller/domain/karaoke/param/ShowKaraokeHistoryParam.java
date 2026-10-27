package cerence.ark.assistant.controller.domain.karaoke.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ShowKaraokeHistoryParam implements JsonCompliant {
    private String action;

    public void setAction(String str) {
        this.action = str;
    }

    public String getAction() {
        return this.action;
    }

    public static ShowKaraokeHistoryParam fromJson(String str) throws JSONException {
        ShowKaraokeHistoryParam showKaraokeHistoryParam = new ShowKaraokeHistoryParam();
        showKaraokeHistoryParam.action = new JSONObject(str).optString("action");
        return showKaraokeHistoryParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("action", this.action);
        return jSONObject.toString();
    }
}
