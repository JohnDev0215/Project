package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class HourlyWeatherInfo implements JsonCompliant {
    private String dateTime;
    private String dayOfWeek;
    private boolean dayTime;
    private MeasurementInfo feelsLike;
    private int humidity;
    private String humidityCode;
    private WeatherCodeInfo icon;
    private PrecipitationInfo precipitation;
    private Precipitation24hInfo precipitation24h;
    private MeasurementInfo pressure;
    private MeasurementInfo temperature;
    private UvInfo uv;
    private WindInfo wind;

    public void setDateTime(String str) {
        this.dateTime = str;
    }

    public String getDateTime() {
        return this.dateTime;
    }

    public void setDayOfWeek(String str) {
        this.dayOfWeek = str;
    }

    public String getDayOfWeek() {
        return this.dayOfWeek;
    }

    public void setDayTime(boolean z) {
        this.dayTime = z;
    }

    public boolean getDayTime() {
        return this.dayTime;
    }

    public void setFeelsLike(MeasurementInfo measurementInfo) {
        this.feelsLike = measurementInfo;
    }

    public MeasurementInfo getFeelsLike() {
        return this.feelsLike;
    }

    public void setHumidity(int i) {
        this.humidity = i;
    }

    public int getHumidity() {
        return this.humidity;
    }

    public void setHumidityCode(String str) {
        this.humidityCode = str;
    }

    public String getHumidityCode() {
        return this.humidityCode;
    }

    public void setIcon(WeatherCodeInfo weatherCodeInfo) {
        this.icon = weatherCodeInfo;
    }

    public WeatherCodeInfo getIcon() {
        return this.icon;
    }

    public void setPrecipitation(PrecipitationInfo precipitationInfo) {
        this.precipitation = precipitationInfo;
    }

    public PrecipitationInfo getPrecipitation() {
        return this.precipitation;
    }

    public void setPrecipitation24h(Precipitation24hInfo precipitation24hInfo) {
        this.precipitation24h = precipitation24hInfo;
    }

    public Precipitation24hInfo getPrecipitation24h() {
        return this.precipitation24h;
    }

    public void setPressure(MeasurementInfo measurementInfo) {
        this.pressure = measurementInfo;
    }

    public MeasurementInfo getPressure() {
        return this.pressure;
    }

    public void setTemperature(MeasurementInfo measurementInfo) {
        this.temperature = measurementInfo;
    }

    public MeasurementInfo getTemperature() {
        return this.temperature;
    }

    public void setUv(UvInfo uvInfo) {
        this.uv = uvInfo;
    }

    public UvInfo getUv() {
        return this.uv;
    }

    public void setWind(WindInfo windInfo) {
        this.wind = windInfo;
    }

    public WindInfo getWind() {
        return this.wind;
    }

    public static HourlyWeatherInfo fromJson(String str) throws JSONException {
        HourlyWeatherInfo hourlyWeatherInfo = new HourlyWeatherInfo();
        JSONObject jSONObject = new JSONObject(str);
        hourlyWeatherInfo.dateTime = jSONObject.optString("dateTime");
        hourlyWeatherInfo.dayOfWeek = jSONObject.optString("dayOfWeek");
        hourlyWeatherInfo.dayTime = jSONObject.optBoolean("dayTime");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("feelsLike");
        if (jSONObjectOptJSONObject != null) {
            hourlyWeatherInfo.feelsLike = MeasurementInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        hourlyWeatherInfo.humidity = jSONObject.optInt("humidity");
        hourlyWeatherInfo.humidityCode = jSONObject.optString("humidityCode");
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("icon");
        if (jSONObjectOptJSONObject2 != null) {
            hourlyWeatherInfo.icon = WeatherCodeInfo.fromJson(jSONObjectOptJSONObject2.toString());
        }
        JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject("precipitation");
        if (jSONObjectOptJSONObject3 != null) {
            hourlyWeatherInfo.precipitation = PrecipitationInfo.fromJson(jSONObjectOptJSONObject3.toString());
        }
        JSONObject jSONObjectOptJSONObject4 = jSONObject.optJSONObject("precipitation24h");
        if (jSONObjectOptJSONObject4 != null) {
            hourlyWeatherInfo.precipitation24h = Precipitation24hInfo.fromJson(jSONObjectOptJSONObject4.toString());
        }
        JSONObject jSONObjectOptJSONObject5 = jSONObject.optJSONObject("pressure");
        if (jSONObjectOptJSONObject5 != null) {
            hourlyWeatherInfo.pressure = MeasurementInfo.fromJson(jSONObjectOptJSONObject5.toString());
        }
        JSONObject jSONObjectOptJSONObject6 = jSONObject.optJSONObject("temperature");
        if (jSONObjectOptJSONObject6 != null) {
            hourlyWeatherInfo.temperature = MeasurementInfo.fromJson(jSONObjectOptJSONObject6.toString());
        }
        JSONObject jSONObjectOptJSONObject7 = jSONObject.optJSONObject("uv");
        if (jSONObjectOptJSONObject7 != null) {
            hourlyWeatherInfo.uv = UvInfo.fromJson(jSONObjectOptJSONObject7.toString());
        }
        JSONObject jSONObjectOptJSONObject8 = jSONObject.optJSONObject("wind");
        if (jSONObjectOptJSONObject8 != null) {
            hourlyWeatherInfo.wind = WindInfo.fromJson(jSONObjectOptJSONObject8.toString());
        }
        return hourlyWeatherInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("dateTime", this.dateTime);
        jSONObject.put("dayOfWeek", this.dayOfWeek);
        jSONObject.put("dayTime", this.dayTime);
        if (this.feelsLike != null) {
            jSONObject.put("feelsLike", new JSONObject(this.feelsLike.toJsonString()));
        }
        jSONObject.put("humidity", this.humidity);
        jSONObject.put("humidityCode", this.humidityCode);
        if (this.icon != null) {
            jSONObject.put("icon", new JSONObject(this.icon.toJsonString()));
        }
        if (this.precipitation != null) {
            jSONObject.put("precipitation", new JSONObject(this.precipitation.toJsonString()));
        }
        if (this.precipitation24h != null) {
            jSONObject.put("precipitation24h", new JSONObject(this.precipitation24h.toJsonString()));
        }
        if (this.pressure != null) {
            jSONObject.put("pressure", new JSONObject(this.pressure.toJsonString()));
        }
        if (this.temperature != null) {
            jSONObject.put("temperature", new JSONObject(this.temperature.toJsonString()));
        }
        if (this.uv != null) {
            jSONObject.put("uv", new JSONObject(this.uv.toJsonString()));
        }
        if (this.wind != null) {
            jSONObject.put("wind", new JSONObject(this.wind.toJsonString()));
        }
        return jSONObject.toString();
    }
}
