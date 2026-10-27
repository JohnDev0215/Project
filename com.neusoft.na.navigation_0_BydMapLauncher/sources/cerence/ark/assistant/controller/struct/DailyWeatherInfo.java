package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class DailyWeatherInfo implements JsonCompliant {
    private String city;
    private String date;
    private String dayOfWeek;
    private int dayWeatherCode;
    private String dayWeatherPhenomena;
    private String highTemperature;
    private String lowTemperature;
    private int nightWeatherCode;
    private String nightWeatherPhenomena;

    public void setCity(String str) {
        this.city = str;
    }

    public String getCity() {
        return this.city;
    }

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

    public void setDayWeatherPhenomena(String str) {
        this.dayWeatherPhenomena = str;
    }

    public String getDayWeatherPhenomena() {
        return this.dayWeatherPhenomena;
    }

    public void setDayWeatherCode(int i) {
        this.dayWeatherCode = i;
    }

    public int getDayWeatherCode() {
        return this.dayWeatherCode;
    }

    public void setNightWeatherPhenomena(String str) {
        this.nightWeatherPhenomena = str;
    }

    public String getNightWeatherPhenomena() {
        return this.nightWeatherPhenomena;
    }

    public void setNightWeatherCode(int i) {
        this.nightWeatherCode = i;
    }

    public int getNightWeatherCode() {
        return this.nightWeatherCode;
    }

    public void setHighTemperature(String str) {
        this.highTemperature = str;
    }

    public String getHighTemperature() {
        return this.highTemperature;
    }

    public void setLowTemperature(String str) {
        this.lowTemperature = str;
    }

    public String getLowTemperature() {
        return this.lowTemperature;
    }

    public static DailyWeatherInfo fromJson(String str) throws JSONException {
        DailyWeatherInfo dailyWeatherInfo = new DailyWeatherInfo();
        JSONObject jSONObject = new JSONObject(str);
        dailyWeatherInfo.city = jSONObject.optString("city");
        dailyWeatherInfo.date = jSONObject.optString("date");
        dailyWeatherInfo.dayOfWeek = jSONObject.optString("day_of_week");
        dailyWeatherInfo.dayWeatherPhenomena = jSONObject.optString("day_weather_phenomena");
        dailyWeatherInfo.dayWeatherCode = jSONObject.optInt("day_weather_code");
        dailyWeatherInfo.nightWeatherPhenomena = jSONObject.optString("night_weather_phenomena");
        dailyWeatherInfo.nightWeatherCode = jSONObject.optInt("night_weather_code");
        dailyWeatherInfo.highTemperature = jSONObject.optString("high_temperature");
        dailyWeatherInfo.lowTemperature = jSONObject.optString("low_temperature");
        return dailyWeatherInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("city", this.city);
        jSONObject.put("date", this.date);
        jSONObject.put("day_of_week", this.dayOfWeek);
        jSONObject.put("day_weather_phenomena", this.dayWeatherPhenomena);
        jSONObject.put("day_weather_code", this.dayWeatherCode);
        jSONObject.put("night_weather_phenomena", this.nightWeatherPhenomena);
        jSONObject.put("night_weather_code", this.nightWeatherCode);
        jSONObject.put("high_temperature", this.highTemperature);
        jSONObject.put("low_temperature", this.lowTemperature);
        return jSONObject.toString();
    }
}
