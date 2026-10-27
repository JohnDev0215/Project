package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class ObserveWeatherInfo implements Parcelable {
    public static final Parcelable.Creator<ObserveWeatherInfo> CREATOR = new Parcelable.Creator<ObserveWeatherInfo>() { // from class: com.neusoft.na.ocservice.ObserveWeatherInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ObserveWeatherInfo createFromParcel(Parcel parcel) {
            return new ObserveWeatherInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ObserveWeatherInfo[] newArray(int i) {
            return new ObserveWeatherInfo[i];
        }
    };
    private String c_temperature;
    private String observe_publish_time;
    private String pressure;
    private String publish_time;
    private String rh;
    private String sendible_temperature;
    private Long servertime;
    private int visibility;
    private String weather_code;
    private String weather_image;
    private String weather_phenomena;
    private String wind_direction;
    private String wind_direction_angle;
    private String wind_force;
    private String wind_speed;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ObserveWeatherInfo() {
        this.servertime = 0L;
        this.visibility = 0;
        this.wind_direction_angle = "";
        this.pressure = "";
        this.sendible_temperature = "";
        this.weather_image = "";
        this.wind_speed = "";
        this.weather_code = "";
        this.weather_phenomena = "";
        this.c_temperature = "";
        this.wind_direction = "";
        this.wind_force = "";
        this.rh = "";
        this.observe_publish_time = "";
        this.publish_time = "";
    }

    public Long getServertime() {
        return this.servertime;
    }

    public void setServertime(Long l) {
        this.servertime = l;
    }

    public int getVisibility() {
        return this.visibility;
    }

    public void setVisibility(int i) {
        this.visibility = i;
    }

    public String getWind_direction_angle() {
        return this.wind_direction_angle;
    }

    public void setWind_direction_angle(String str) {
        this.wind_direction_angle = str;
    }

    public String getPressure() {
        return this.pressure;
    }

    public void setPressure(String str) {
        this.pressure = str;
    }

    public String getSendible_temperature() {
        return this.sendible_temperature;
    }

    public void setSendible_temperature(String str) {
        this.sendible_temperature = str;
    }

    public String getWeather_image() {
        return this.weather_image;
    }

    public void setWeather_image(String str) {
        this.weather_image = str;
    }

    public String getWind_speed() {
        return this.wind_speed;
    }

    public void setWind_speed(String str) {
        this.wind_speed = str;
    }

    public String getWeather_code() {
        return this.weather_code;
    }

    public void setWeather_code(String str) {
        this.weather_code = str;
    }

    public String getWeather_phenomena() {
        return this.weather_phenomena;
    }

    public void setWeather_phenomena(String str) {
        this.weather_phenomena = str;
    }

    public String getC_temperature() {
        return this.c_temperature;
    }

    public void setC_temperature(String str) {
        this.c_temperature = str;
    }

    public String getWind_direction() {
        return this.wind_direction;
    }

    public void setWind_direction(String str) {
        this.wind_direction = str;
    }

    public String getWind_force() {
        return this.wind_force;
    }

    public void setWind_force(String str) {
        this.wind_force = str;
    }

    public String getRh() {
        return this.rh;
    }

    public void setRh(String str) {
        this.rh = str;
    }

    public String getObserve_publish_time() {
        return this.observe_publish_time;
    }

    public void setObserve_publish_time(String str) {
        this.observe_publish_time = str;
    }

    public String getPublish_time() {
        return this.publish_time;
    }

    public void setPublish_time(String str) {
        this.publish_time = str;
    }

    protected ObserveWeatherInfo(Parcel parcel) {
        this.servertime = 0L;
        this.visibility = 0;
        this.wind_direction_angle = "";
        this.pressure = "";
        this.sendible_temperature = "";
        this.weather_image = "";
        this.wind_speed = "";
        this.weather_code = "";
        this.weather_phenomena = "";
        this.c_temperature = "";
        this.wind_direction = "";
        this.wind_force = "";
        this.rh = "";
        this.observe_publish_time = "";
        this.publish_time = "";
        if (parcel.readByte() == 0) {
            this.servertime = null;
        } else {
            this.servertime = Long.valueOf(parcel.readLong());
        }
        this.visibility = parcel.readInt();
        this.wind_direction_angle = parcel.readString();
        this.pressure = parcel.readString();
        this.sendible_temperature = parcel.readString();
        this.weather_image = parcel.readString();
        this.wind_speed = parcel.readString();
        this.weather_code = parcel.readString();
        this.weather_phenomena = parcel.readString();
        this.c_temperature = parcel.readString();
        this.wind_direction = parcel.readString();
        this.wind_force = parcel.readString();
        this.rh = parcel.readString();
        this.observe_publish_time = parcel.readString();
        this.publish_time = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (this.servertime == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(this.servertime.longValue());
        }
        parcel.writeInt(this.visibility);
        parcel.writeString(this.wind_direction_angle);
        parcel.writeString(this.pressure);
        parcel.writeString(this.sendible_temperature);
        parcel.writeString(this.weather_image);
        parcel.writeString(this.wind_speed);
        parcel.writeString(this.weather_code);
        parcel.writeString(this.weather_phenomena);
        parcel.writeString(this.c_temperature);
        parcel.writeString(this.wind_direction);
        parcel.writeString(this.wind_force);
        parcel.writeString(this.rh);
        parcel.writeString(this.observe_publish_time);
        parcel.writeString(this.publish_time);
    }
}
