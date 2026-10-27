package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class HourlyWeatherInfo implements JsonCompliant {
    private String date;
    private String dayOfWeek;
    private String phenomena;
    private String temperature;
    private int weatherCode;

    public void setDate(String str) {
        this.date = str;
    }

    public String getDate() {
        return this.date;
    }

    public void setDayOfWeek(String str) {
        this.dayOfWeek = str;
    }

    public String getDayOfWeek() {
        return this.dayOfWeek;
    }

    public void setPhenomena(String str) {
        this.phenomena = str;
    }

    public String getPhenomena() {
        return this.phenomena;
    }

    public void setTemperature(String str) {
        this.temperature = str;
    }

    public String getTemperature() {
        return this.temperature;
    }

    public void setWeatherCode(int i) {
        this.weatherCode = i;
    }

    public int getWeatherCode() {
        return this.weatherCode;
    }

    public static HourlyWeatherInfo fromJson(String str) throws JSONException {
        HourlyWeatherInfo hourlyWeatherInfo = new HourlyWeatherInfo();
        JSONObject jSONObject = new JSONObject(str);
        hourlyWeatherInfo.date = jSONObject.optString("date");
        hourlyWeatherInfo.dayOfWeek = jSONObject.optString("day_of_week");
        hourlyWeatherInfo.phenomena = jSONObject.optString("phenomena");
        hourlyWeatherInfo.temperature = jSONObject.optString("temperature");
        hourlyWeatherInfo.weatherCode = jSONObject.optInt("weather_code");
        return hourlyWeatherInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("date", this.date);
        jSONObject.put("day_of_week", this.dayOfWeek);
        jSONObject.put("phenomena", this.phenomena);
        jSONObject.put("temperature", this.temperature);
        jSONObject.put("weather_code", this.weatherCode);
        return jSONObject.toString();
    }
}
