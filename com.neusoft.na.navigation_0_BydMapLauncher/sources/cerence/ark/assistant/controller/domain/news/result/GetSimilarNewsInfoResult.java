package cerence.ark.assistant.controller.domain.news.result;

import cerence.ark.assistant.api.BaseResult;
import cerence.ark.assistant.controller.struct.NewsInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class GetSimilarNewsInfoResult extends BaseResult {
    private NewsInfo newsInfo;

    public void setNewsInfo(NewsInfo newsInfo) {
        this.newsInfo = newsInfo;
    }

    public NewsInfo getNewsInfo() {
        return this.newsInfo;
    }

    public static GetSimilarNewsInfoResult fromJson(String str) throws JSONException {
        GetSimilarNewsInfoResult getSimilarNewsInfoResult = new GetSimilarNewsInfoResult();
        JSONObject jSONObjectOptJSONObject = new JSONObject(str).optJSONObject("news_info");
        if (jSONObjectOptJSONObject != null) {
            getSimilarNewsInfoResult.newsInfo = NewsInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return getSimilarNewsInfoResult;
    }

    @Override // cerence.ark.assistant.api.BaseResult, cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.newsInfo != null) {
            jSONObject.put("news_info", new JSONObject(this.newsInfo.toJsonString()));
        }
        return jSONObject.toString();
    }
}
