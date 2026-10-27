package cerence.ark.assistant.controller.domain.news.param;

import cerence.ark.assistant.controller.struct.NewsInfo;
import cerence.ark.assistant.core.JsonCompliant;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class PlayNewsParam implements JsonCompliant {
    private NewsInfo newsInfo;
    private List<NewsInfo> newsList;

    public void setNewsList(List<NewsInfo> list) {
        this.newsList = list;
    }

    public List<NewsInfo> getNewsList() {
        return this.newsList;
    }

    public void setNewsInfo(NewsInfo newsInfo) {
        this.newsInfo = newsInfo;
    }

    public NewsInfo getNewsInfo() {
        return this.newsInfo;
    }

    public static PlayNewsParam fromJson(String str) throws JSONException {
        PlayNewsParam playNewsParam = new PlayNewsParam();
        JSONObject jSONObject = new JSONObject(str);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("news_list");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                arrayList.add(NewsInfo.fromJson(jSONArrayOptJSONArray.get(i).toString()));
            }
        }
        playNewsParam.newsList = arrayList;
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("news_info");
        if (jSONObjectOptJSONObject != null) {
            playNewsParam.newsInfo = NewsInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        return playNewsParam;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        if (this.newsList != null) {
            for (int i = 0; i < this.newsList.size(); i++) {
                jSONArray.put(new JSONObject(this.newsList.get(i).toJsonString()));
            }
        }
        jSONObject.put("news_list", jSONArray);
        if (this.newsInfo != null) {
            jSONObject.put("news_info", new JSONObject(this.newsInfo.toJsonString()));
        }
        return jSONObject.toString();
    }
}
