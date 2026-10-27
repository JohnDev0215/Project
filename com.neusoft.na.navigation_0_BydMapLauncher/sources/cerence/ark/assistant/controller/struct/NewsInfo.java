package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class NewsInfo implements JsonCompliant {
    private String date;
    private String location;
    private String provider;
    private String searchPhrase;

    public void setDate(String str) {
        this.date = str;
    }

    public String getDate() {
        return this.date;
    }

    public void setProvider(String str) {
        this.provider = str;
    }

    public String getProvider() {
        return this.provider;
    }

    public void setLocation(String str) {
        this.location = str;
    }

    public String getLocation() {
        return this.location;
    }

    public void setSearchPhrase(String str) {
        this.searchPhrase = str;
    }

    public String getSearchPhrase() {
        return this.searchPhrase;
    }

    public static NewsInfo fromJson(String str) throws JSONException {
        NewsInfo newsInfo = new NewsInfo();
        JSONObject jSONObject = new JSONObject(str);
        newsInfo.date = jSONObject.optString("date");
        newsInfo.provider = jSONObject.optString("provider");
        newsInfo.location = jSONObject.optString("location");
        newsInfo.searchPhrase = jSONObject.optString("searchPhrase");
        return newsInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("date", this.date);
        jSONObject.put("provider", this.provider);
        jSONObject.put("location", this.location);
        jSONObject.put("searchPhrase", this.searchPhrase);
        return jSONObject.toString();
    }
}
