package cerence.ark.assistant.controller.domain.system.param;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ShowScreenParam implements JsonCompliant {
    private String screenName;

    public void setScreenName(String str) {
        this.screenName = str;
    }

    public String getScreenName() {
        return this.screenName;
    }

    public static ShowScreenParam fromJson(String str) throws JSONException {
        ShowScreenParam showScreenParam = new ShowScreenParam();
        showScreenParam.screenName = new JSONObject(str).optString("screen_name");
        return showScreenParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("screen_name", this.screenName);
        return jSONObject.toString();
    }
}
