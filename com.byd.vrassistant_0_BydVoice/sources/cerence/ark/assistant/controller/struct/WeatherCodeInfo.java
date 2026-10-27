package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class WeatherCodeInfo implements JsonCompliant {
    private String id;
    private String phrase;

    public void setId(String str) {
        this.id = str;
    }

    public String getId() {
        return this.id;
    }

    public void setPhrase(String str) {
        this.phrase = str;
    }

    public String getPhrase() {
        return this.phrase;
    }

    public static WeatherCodeInfo fromJson(String str) throws JSONException {
        WeatherCodeInfo weatherCodeInfo = new WeatherCodeInfo();
        JSONObject jSONObject = new JSONObject(str);
        weatherCodeInfo.id = jSONObject.optString("id");
        weatherCodeInfo.phrase = jSONObject.optString("phrase");
        return weatherCodeInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("id", this.id);
        jSONObject.put("phrase", this.phrase);
        return jSONObject.toString();
    }
}
