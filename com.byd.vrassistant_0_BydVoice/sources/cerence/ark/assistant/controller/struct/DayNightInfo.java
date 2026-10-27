package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class DayNightInfo implements JsonCompliant {
    private int cloudCover;
    private WeatherCodeInfo icon;
    private PrecipitationInfo precipitation;
    private MeasurementInfo pressure;
    private WindInfo wind;

    public void setCloudCover(int i) {
        this.cloudCover = i;
    }

    public int getCloudCover() {
        return this.cloudCover;
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

    public void setPressure(MeasurementInfo measurementInfo) {
        this.pressure = measurementInfo;
    }

    public MeasurementInfo getPressure() {
        return this.pressure;
    }

    public void setWind(WindInfo windInfo) {
        this.wind = windInfo;
    }

    public WindInfo getWind() {
        return this.wind;
    }

    public static DayNightInfo fromJson(String str) throws JSONException {
        DayNightInfo dayNightInfo = new DayNightInfo();
        JSONObject jSONObject = new JSONObject(str);
        dayNightInfo.cloudCover = jSONObject.optInt("cloudCover");
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("icon");
        if (jSONObjectOptJSONObject != null) {
            dayNightInfo.icon = WeatherCodeInfo.fromJson(jSONObjectOptJSONObject.toString());
        }
        JSONObject jSONObjectOptJSONObject2 = jSONObject.optJSONObject("precipitation");
        if (jSONObjectOptJSONObject2 != null) {
            dayNightInfo.precipitation = PrecipitationInfo.fromJson(jSONObjectOptJSONObject2.toString());
        }
        JSONObject jSONObjectOptJSONObject3 = jSONObject.optJSONObject("pressure");
        if (jSONObjectOptJSONObject3 != null) {
            dayNightInfo.pressure = MeasurementInfo.fromJson(jSONObjectOptJSONObject3.toString());
        }
        JSONObject jSONObjectOptJSONObject4 = jSONObject.optJSONObject("wind");
        if (jSONObjectOptJSONObject4 != null) {
            dayNightInfo.wind = WindInfo.fromJson(jSONObjectOptJSONObject4.toString());
        }
        return dayNightInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("cloudCover", this.cloudCover);
        if (this.icon != null) {
            jSONObject.put("icon", new JSONObject(this.icon.toJsonString()));
        }
        if (this.precipitation != null) {
            jSONObject.put("precipitation", new JSONObject(this.precipitation.toJsonString()));
        }
        if (this.pressure != null) {
            jSONObject.put("pressure", new JSONObject(this.pressure.toJsonString()));
        }
        if (this.wind != null) {
            jSONObject.put("wind", new JSONObject(this.wind.toJsonString()));
        }
        return jSONObject.toString();
    }
}
