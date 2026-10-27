package cerence.ark.assistant.controller.domain.news.param;

import cerence.ark.assistant.controller.struct.NewsInfo;
import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CheckNewsAvailabilityParam implements JsonCompliant {
    private NewsInfo newsInfo;

    public void setNewsInfo(NewsInfo newsInfo) {
        this.newsInfo = newsInfo;
    }

    public NewsInfo getNewsInfo() {
        return this.newsInfo;
    }

    public static CheckNewsAvailabilityParam fromJson(String str) throws JSONException {
        CheckNewsAvailabilityParam checkNewsAvailabilityParam = new CheckNewsAvailabilityParam();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("news_info");
        if (jSONObjectOptJSONObject != null) {
            checkNewsAvailabilityParam.newsInfo = NewsInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return checkNewsAvailabilityParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.newsInfo != null) {
            jSONObject.put("news_info", new JSONObject(this.newsInfo.toJsonString()));
        }
        return jSONObject.toString();
    }
}
