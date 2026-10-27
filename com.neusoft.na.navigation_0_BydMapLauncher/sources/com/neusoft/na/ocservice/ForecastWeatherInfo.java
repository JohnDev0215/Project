package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ForecastWeatherInfo implements Parcelable {
    public static final Parcelable.Creator<ForecastWeatherInfo> CREATOR = new Parcelable.Creator<ForecastWeatherInfo>() { // from class: com.neusoft.na.ocservice.ForecastWeatherInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ForecastWeatherInfo createFromParcel(Parcel parcel) {
            return new ForecastWeatherInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ForecastWeatherInfo[] newArray(int i) {
            return new ForecastWeatherInfo[i];
        }
    };
    private List<Forecast> forecasts;
    private String publish_time;
    private int record_count;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected ForecastWeatherInfo(Parcel parcel) {
        this.record_count = 0;
        this.publish_time = "";
        this.forecasts = new ArrayList();
        this.record_count = parcel.readInt();
        this.publish_time = parcel.readString();
        this.forecasts = parcel.createTypedArrayList(Forecast.CREATOR);
    }

    public ForecastWeatherInfo() {
        this.record_count = 0;
        this.publish_time = "";
        this.forecasts = new ArrayList();
    }

    public int getRecord_count() {
        return this.record_count;
    }

    public void setRecord_count(int i) {
        this.record_count = i;
    }

    public String getPublish_time() {
        return this.publish_time;
    }

    public void setPublish_time(String str) {
        this.publish_time = str;
    }

    public List<Forecast> getForecasts() {
        return this.forecasts;
    }

    public void setForecasts(List<Forecast> list) {
        this.forecasts = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.record_count);
        parcel.writeString(this.publish_time);
        parcel.writeTypedList(this.forecasts);
    }
}
