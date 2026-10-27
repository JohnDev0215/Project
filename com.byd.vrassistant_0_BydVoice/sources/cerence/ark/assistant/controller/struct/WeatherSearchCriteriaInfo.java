package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class WeatherSearchCriteriaInfo implements JsonCompliant {
    private String date;
    private String locality;
    private String queryExtension;
    private String time;

    public void setDate(String str) {
        this.date = str;
    }

    public String getDate() {
        return this.date;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setLocality(String str) {
        this.locality = str;
    }

    public String getLocality() {
        return this.locality;
    }

    public void setQueryExtension(String str) {
        this.queryExtension = str;
    }

    public String getQueryExtension() {
        return this.queryExtension;
    }

    public static WeatherSearchCriteriaInfo fromJson(String str) throws JSONException {
        WeatherSearchCriteriaInfo weatherSearchCriteriaInfo = new WeatherSearchCriteriaInfo();
        JSONObject jSONObject = new JSONObject(str);
        weatherSearchCriteriaInfo.date = jSONObject.optString("date");
        weatherSearchCriteriaInfo.time = jSONObject.optString("time");
        weatherSearchCriteriaInfo.locality = jSONObject.optString("locality");
        weatherSearchCriteriaInfo.queryExtension = jSONObject.optString("queryExtension");
        return weatherSearchCriteriaInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("date", this.date);
        jSONObject.put("time", this.time);
        jSONObject.put("locality", this.locality);
        jSONObject.put("queryExtension", this.queryExtension);
        return jSONObject.toString();
    }
}
