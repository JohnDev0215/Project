package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class DailyWeatherInfo implements JsonCompliant {
    private String date;
    private DayNightInfo day;
    private String dayOfWeek;
    private MeasurementRangeInfo feelsLike;
    private DayNightInfo night;
    private Precipitation24hInfo precipitation;
    private String sunrise;
    private String sunset;
    private MeasurementRangeInfo temperature;
    private UvInfo uv;

    public void setDate(String str) {
        this.date = str;
    }

    public String getDate() {
        return this.date;
    }

    public void setDay(DayNightInfo dayNightInfo) {
        this.day = dayNightInfo;
    }

    public DayNightInfo getDay() {
        return this.day;
    }

    public void setDayOfWeek(String str) {
        this.dayOfWeek = str;
    }

    public String getDayOfWeek() {
        return this.dayOfWeek;
    }

    public void setFeelsLike(MeasurementRangeInfo measurementRangeInfo) {
        this.feelsLike = measurementRangeInfo;
    }

    public MeasurementRangeInfo getFeelsLike() {
        return this.feelsLike;
    }

    public void setNight(DayNightInfo dayNightInfo) {
        this.night = dayNightInfo;
    }

    public DayNightInfo getNight() {
        return this.night;
    }

    public void setPrecipitation(Precipitation24hInfo precipitation24hInfo) {
        this.precipitation = precipitation24hInfo;
    }

    public Precipitation24hInfo getPrecipitation() {
        return this.precipitation;
    }

    public void setSunrise(String str) {
        this.sunrise = str;
    }

    public String getSunrise() {
        return this.sunrise;
    }

    public void setSunset(String str) {
        this.sunset = str;
    }

    public String getSunset() {
        return this.sunset;
    }

    public void setTemperature(MeasurementRangeInfo measurementRangeInfo) {
        this.temperature = measurementRangeInfo;
    }

    public MeasurementRangeInfo getTemperature() {
        return this.temperature;
    }

    public void setUv(UvInfo uvInfo) {
        this.uv = uvInfo;
    }

    public UvInfo getUv() {
        return this.uv;
    }

    public static DailyWeatherInfo fromJson(String str) throws JSONException {
        DailyWeatherInfo dailyWeatherInfo = new DailyWeatherInfo();
        JSONObject jSONObject = new JSONObject(str);
        dailyWeatherInfo.date = jSONObject.optString("date");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("day");
        if (jSONObjectOptJSONObject != null) {
            dailyWeatherInfo.day = DayNightInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        dailyWeatherInfo.dayOfWeek = jSONObject.optString("dayOfWeek");
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("feelsLike");
        if (jSONObjectOptJSONObject2 != null) {
            dailyWeatherInfo.feelsLike = MeasurementRangeInfo.fromJson(jSONObjectOptJSONObject2.toString());
        }
        JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject("night");
        if (jSONObjectOptJSONObject3 != null) {
            dailyWeatherInfo.night = DayNightInfo.fromJson(jSONObjectOptJSONObject3.toString());
        }
        JSONObject jSONObjectOptJSONObject4 = jSONObject.optJSONObject("precipitation");
        if (jSONObjectOptJSONObject4 != null) {
            dailyWeatherInfo.precipitation = Precipitation24hInfo.fromJson(jSONObjectOptJSONObject4.toString());
        }
        dailyWeatherInfo.sunrise = jSONObject.optString("sunrise");
        dailyWeatherInfo.sunset = jSONObject.optString("sunset");
        JSONObject jSONObjectOptJSONObject5 = jSONObject.optJSONObject("temperature");
        if (jSONObjectOptJSONObject5 != null) {
            dailyWeatherInfo.temperature = MeasurementRangeInfo.fromJson(jSONObjectOptJSONObject5.toString());
        }
        JSONObject jSONObjectOptJSONObject6 = jSONObject.optJSONObject("uv");
        if (jSONObjectOptJSONObject6 != null) {
            dailyWeatherInfo.uv = UvInfo.fromJson(jSONObjectOptJSONObject6.toString());
        }
        return dailyWeatherInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("date", this.date);
        if (this.day != null) {
            jSONObject.put("day", new JSONObject(this.day.toJsonString()));
        }
        jSONObject.put("dayOfWeek", this.dayOfWeek);
        if (this.feelsLike != null) {
            jSONObject.put("feelsLike", new JSONObject(this.feelsLike.toJsonString()));
        }
        if (this.night != null) {
            jSONObject.put("night", new JSONObject(this.night.toJsonString()));
        }
        if (this.precipitation != null) {
            jSONObject.put("precipitation", new JSONObject(this.precipitation.toJsonString()));
        }
        jSONObject.put("sunrise", this.sunrise);
        jSONObject.put("sunset", this.sunset);
        if (this.temperature != null) {
            jSONObject.put("temperature", new JSONObject(this.temperature.toJsonString()));
        }
        if (this.uv != null) {
            jSONObject.put("uv", new JSONObject(this.uv.toJsonString()));
        }
        return jSONObject.toString();
    }
}
