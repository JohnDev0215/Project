package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class CurrentWeatherInfo implements JsonCompliant {
    private int aqi;
    private String city;
    private String date;
    private String phenomena;
    private int pm10;
    private int pm25;
    private String temperature;
    private int uvIndex;
    private int weatherCode;
    private int windDirection;
    private int windDirectionCode;
    private String windSpeed;
    private int windStrengthCode;

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

    public void setTemperature(String str) {
        this.temperature = str;
    }

    public String getTemperature() {
        return this.temperature;
    }

    public void setPhenomena(String str) {
        this.phenomena = str;
    }

    public String getPhenomena() {
        return this.phenomena;
    }

    public void setWeatherCode(int i) {
        this.weatherCode = i;
    }

    public int getWeatherCode() {
        return this.weatherCode;
    }

    public void setWindSpeed(String str) {
        this.windSpeed = str;
    }

    public String getWindSpeed() {
        return this.windSpeed;
    }

    public void setWindStrengthCode(int i) {
        this.windStrengthCode = i;
    }

    public int getWindStrengthCode() {
        return this.windStrengthCode;
    }

    public void setWindDirection(int i) {
        this.windDirection = i;
    }

    public int getWindDirection() {
        return this.windDirection;
    }

    public void setWindDirectionCode(int i) {
        this.windDirectionCode = i;
    }

    public int getWindDirectionCode() {
        return this.windDirectionCode;
    }

    public void setPm25(int i) {
        this.pm25 = i;
    }

    public int getPm25() {
        return this.pm25;
    }

    public void setAqi(int i) {
        this.aqi = i;
    }

    public int getAqi() {
        return this.aqi;
    }

    public void setPm10(int i) {
        this.pm10 = i;
    }

    public int getPm10() {
        return this.pm10;
    }

    public void setUvIndex(int i) {
        this.uvIndex = i;
    }

    public int getUvIndex() {
        return this.uvIndex;
    }

    public static CurrentWeatherInfo fromJson(String str) throws JSONException {
        CurrentWeatherInfo currentWeatherInfo = new CurrentWeatherInfo();
        JSONObject jSONObject = new JSONObject(str);
        currentWeatherInfo.city = jSONObject.optString("city");
        currentWeatherInfo.date = jSONObject.optString("date");
        currentWeatherInfo.temperature = jSONObject.optString("temperature");
        currentWeatherInfo.phenomena = jSONObject.optString("phenomena");
        currentWeatherInfo.weatherCode = jSONObject.optInt("weather_code");
        currentWeatherInfo.windSpeed = jSONObject.optString("wind_speed");
        currentWeatherInfo.windStrengthCode = jSONObject.optInt("wind_strength_code");
        currentWeatherInfo.windDirection = jSONObject.optInt("wind_direction");
        currentWeatherInfo.windDirectionCode = jSONObject.optInt("wind_direction_code");
        currentWeatherInfo.pm25 = jSONObject.optInt("pm25");
        currentWeatherInfo.aqi = jSONObject.optInt("aqi");
        currentWeatherInfo.pm10 = jSONObject.optInt("pm10");
        currentWeatherInfo.uvIndex = jSONObject.optInt("uv_index");
        return currentWeatherInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("city", this.city);
        jSONObject.put("date", this.date);
        jSONObject.put("temperature", this.temperature);
        jSONObject.put("phenomena", this.phenomena);
        jSONObject.put("weather_code", this.weatherCode);
        jSONObject.put("wind_speed", this.windSpeed);
        jSONObject.put("wind_strength_code", this.windStrengthCode);
        jSONObject.put("wind_direction", this.windDirection);
        jSONObject.put("wind_direction_code", this.windDirectionCode);
        jSONObject.put("pm25", this.pm25);
        jSONObject.put("aqi", this.aqi);
        jSONObject.put("pm10", this.pm10);
        jSONObject.put("uv_index", this.uvIndex);
        return jSONObject.toString();
    }
}
