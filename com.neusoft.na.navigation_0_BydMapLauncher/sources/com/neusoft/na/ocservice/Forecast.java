package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class Forecast implements Parcelable {
    public static final Parcelable.Creator<Forecast> CREATOR = new Parcelable.Creator<Forecast>() { // from class: com.neusoft.na.ocservice.Forecast.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Forecast createFromParcel(Parcel parcel) {
            return new Forecast(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Forecast[] newArray(int i) {
            return new Forecast[i];
        }
    };
    private String day_c_temperature;
    private String day_weather_code;
    private String day_weather_image;
    private String day_weather_phenomena;
    private String day_wind_direction;
    private String day_wind_force;
    private String forecast_date;
    private String night_c_temperature;
    private String night_weather_code;
    private String night_weather_image;
    private String night_weather_phenomena;
    private String night_wind_direction;
    private String night_wind_force;
    private String sunrd;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Forecast() {
        this.forecast_date = "";
        this.day_wind_direction = "";
        this.day_wind_force = "";
        this.day_weather_phenomena = "";
        this.day_c_temperature = "";
        this.night_weather_phenomena = "";
        this.night_c_temperature = "";
        this.night_wind_direction = "";
        this.night_wind_force = "";
        this.day_weather_code = "";
        this.day_weather_image = "";
        this.night_weather_code = "";
        this.night_weather_image = "";
        this.sunrd = "";
    }

    protected Forecast(Parcel parcel) {
        this.forecast_date = "";
        this.day_wind_direction = "";
        this.day_wind_force = "";
        this.day_weather_phenomena = "";
        this.day_c_temperature = "";
        this.night_weather_phenomena = "";
        this.night_c_temperature = "";
        this.night_wind_direction = "";
        this.night_wind_force = "";
        this.day_weather_code = "";
        this.day_weather_image = "";
        this.night_weather_code = "";
        this.night_weather_image = "";
        this.sunrd = "";
        this.forecast_date = parcel.readString();
        this.day_wind_direction = parcel.readString();
        this.day_wind_force = parcel.readString();
        this.day_weather_phenomena = parcel.readString();
        this.day_c_temperature = parcel.readString();
        this.night_weather_phenomena = parcel.readString();
        this.night_c_temperature = parcel.readString();
        this.night_wind_direction = parcel.readString();
        this.night_wind_force = parcel.readString();
        this.day_weather_code = parcel.readString();
        this.day_weather_image = parcel.readString();
        this.night_weather_code = parcel.readString();
        this.night_weather_image = parcel.readString();
        this.sunrd = parcel.readString();
    }

    public String getForecast_date() {
        return this.forecast_date;
    }

    public void setForecast_date(String str) {
        this.forecast_date = str;
    }

    public String getDay_wind_direction() {
        return this.day_wind_direction;
    }

    public void setDay_wind_direction(String str) {
        this.day_wind_direction = str;
    }

    public String getDay_wind_force() {
        return this.day_wind_force;
    }

    public void setDay_wind_force(String str) {
        this.day_wind_force = str;
    }

    public String getDay_weather_phenomena() {
        return this.day_weather_phenomena;
    }

    public void setDay_weather_phenomena(String str) {
        this.day_weather_phenomena = str;
    }

    public String getDay_c_temperature() {
        return this.day_c_temperature;
    }

    public void setDay_c_temperature(String str) {
        this.day_c_temperature = str;
    }

    public String getNight_weather_phenomena() {
        return this.night_weather_phenomena;
    }

    public void setNight_weather_phenomena(String str) {
        this.night_weather_phenomena = str;
    }

    public String getNight_c_temperature() {
        return this.night_c_temperature;
    }

    public void setNight_c_temperature(String str) {
        this.night_c_temperature = str;
    }

    public String getNight_wind_direction() {
        return this.night_wind_direction;
    }

    public void setNight_wind_direction(String str) {
        this.night_wind_direction = str;
    }

    public String getNight_wind_force() {
        return this.night_wind_force;
    }

    public void setNight_wind_force(String str) {
        this.night_wind_force = str;
    }

    public String getDay_weather_code() {
        return this.day_weather_code;
    }

    public void setDay_weather_code(String str) {
        this.day_weather_code = str;
    }

    public String getDay_weather_image() {
        return this.day_weather_image;
    }

    public void setDay_weather_image(String str) {
        this.day_weather_image = str;
    }

    public String getNight_weather_code() {
        return this.night_weather_code;
    }

    public void setNight_weather_code(String str) {
        this.night_weather_code = str;
    }

    public String getNight_weather_image() {
        return this.night_weather_image;
    }

    public void setNight_weather_image(String str) {
        this.night_weather_image = str;
    }

    public String getSunrd() {
        return this.sunrd;
    }

    public void setSunrd(String str) {
        this.sunrd = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.forecast_date);
        parcel.writeString(this.day_wind_direction);
        parcel.writeString(this.day_wind_force);
        parcel.writeString(this.day_weather_phenomena);
        parcel.writeString(this.day_c_temperature);
        parcel.writeString(this.night_weather_phenomena);
        parcel.writeString(this.night_c_temperature);
        parcel.writeString(this.night_wind_direction);
        parcel.writeString(this.night_wind_force);
        parcel.writeString(this.day_weather_code);
        parcel.writeString(this.day_weather_image);
        parcel.writeString(this.night_weather_code);
        parcel.writeString(this.night_weather_image);
        parcel.writeString(this.sunrd);
    }
}
