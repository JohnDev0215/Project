package cerence.ark.assistant.controller.struct;

import cerence.ark.assistant.core.JsonCompliant;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class WeatherLocationInfo implements JsonCompliant {
    private String city;
    private String country;
    private double latitude;
    private double longitude;
    private String state;

    public void setCity(String str) {
        this.city = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setState(String str) {
        this.state = str;
    }

    public String getState() {
        return this.state;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public static WeatherLocationInfo fromJson(String str) throws JSONException {
        WeatherLocationInfo weatherLocationInfo = new WeatherLocationInfo();
        JSONObject jSONObject = new JSONObject(str);
        weatherLocationInfo.city = jSONObject.optString("city");
        weatherLocationInfo.state = jSONObject.optString("state");
        weatherLocationInfo.country = jSONObject.optString("country");
        weatherLocationInfo.longitude = jSONObject.optDouble("longitude");
        weatherLocationInfo.latitude = jSONObject.optDouble("latitude");
        return weatherLocationInfo;
    }

    @Override // cerence.ark.assistant.core.JsonCompliant
    public String toJsonString() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("city", this.city);
        jSONObject.put("state", this.state);
        jSONObject.put("country", this.country);
        jSONObject.put("longitude", this.longitude);
        jSONObject.put("latitude", this.latitude);
        return jSONObject.toString();
    }
}
