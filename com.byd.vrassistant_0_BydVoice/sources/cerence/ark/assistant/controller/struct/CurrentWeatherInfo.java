package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CurrentWeatherInfo implements JsonCompliant {
    private int cloudCover;
    private String dateTime;
    private boolean dayTime;
    private MeasurementInfo feelsLike;
    private int humidity;
    private String humidityCode;
    private WeatherCodeInfo icon;
    private MeasurementInfo pressure;
    private MeasurementInfo temperature;
    private UvInfo uv;
    private WindInfo wind;

    public void setCloudCover(int i) {
        this.cloudCover = i;
    }

    public int getCloudCover() {
        return this.cloudCover;
    }

    public void setDateTime(String str) {
        this.dateTime = str;
    }

    public String getDateTime() {
        return this.dateTime;
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

    public void setIcon(WeatherCodeInfo weatherCodeInfo) {
        this.icon = weatherCodeInfo;
    }

    public WeatherCodeInfo getIcon() {
        return this.icon;
    }

    public void setWind(WindInfo windInfo) {
        this.wind = windInfo;
    }

    public WindInfo getWind() {
        return this.wind;
    }

    public void setUv(UvInfo uvInfo) {
        this.uv = uvInfo;
    }

    public UvInfo getUv() {
        return this.uv;
    }

    public static CurrentWeatherInfo fromJson(String str) throws JSONException {
        CurrentWeatherInfo currentWeatherInfo = new CurrentWeatherInfo();
        JSONObject jSONObject = new JSONObject(str);
        currentWeatherInfo.cloudCover = jSONObject.optInt("cloudCover");
        currentWeatherInfo.dateTime = jSONObject.optString("dateTime");
        currentWeatherInfo.dayTime = jSONObject.optBoolean("dayTime");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("feelsLike");
        if (jSONObjectOptJSONObject != null) {
            currentWeatherInfo.feelsLike = MeasurementInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        currentWeatherInfo.humidity = jSONObject.optInt("humidity");
        currentWeatherInfo.humidityCode = jSONObject.optString("humidityCode");
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("pressure");
        if (jSONObjectOptJSONObject2 != null) {
            currentWeatherInfo.pressure = MeasurementInfo.fromJson(jSONObjectOptJSONObject2.toString());
        }
        JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject("temperature");
        if (jSONObjectOptJSONObject3 != null) {
            currentWeatherInfo.temperature = MeasurementInfo.fromJson(jSONObjectOptJSONObject3.toString());
        }
        JSONObject jSONObjectOptJSONObject4 = jSONObject.optJSONObject("icon");
        if (jSONObjectOptJSONObject4 != null) {
            currentWeatherInfo.icon = WeatherCodeInfo.fromJson(jSONObjectOptJSONObject4.toString());
        }
        JSONObject jSONObjectOptJSONObject5 = jSONObject.optJSONObject("wind");
        if (jSONObjectOptJSONObject5 != null) {
            currentWeatherInfo.wind = WindInfo.fromJson(jSONObjectOptJSONObject5.toString());
        }
        JSONObject jSONObjectOptJSONObject6 = jSONObject.optJSONObject("uv");
        if (jSONObjectOptJSONObject6 != null) {
            currentWeatherInfo.uv = UvInfo.fromJson(jSONObjectOptJSONObject6.toString());
        }
        return currentWeatherInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cloudCover", this.cloudCover);
        jSONObject.put("dateTime", this.dateTime);
        jSONObject.put("dayTime", this.dayTime);
        if (this.feelsLike != null) {
            jSONObject.put("feelsLike", new JSONObject(this.feelsLike.toJsonString()));
        }
        jSONObject.put("humidity", this.humidity);
        jSONObject.put("humidityCode", this.humidityCode);
        if (this.pressure != null) {
            jSONObject.put("pressure", new JSONObject(this.pressure.toJsonString()));
        }
        if (this.temperature != null) {
            jSONObject.put("temperature", new JSONObject(this.temperature.toJsonString()));
        }
        if (this.icon != null) {
            jSONObject.put("icon", new JSONObject(this.icon.toJsonString()));
        }
        if (this.wind != null) {
            jSONObject.put("wind", new JSONObject(this.wind.toJsonString()));
        }
        if (this.uv != null) {
            jSONObject.put("uv", new JSONObject(this.uv.toJsonString()));
        }
        return jSONObject.toString();
    }
}
