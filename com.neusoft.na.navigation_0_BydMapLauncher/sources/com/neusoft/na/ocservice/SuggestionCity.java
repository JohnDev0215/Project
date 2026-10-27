package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class SuggestionCity implements Parcelable {
    public static final Parcelable.Creator<SuggestionCity> CREATOR = new Parcelable.Creator<SuggestionCity>() { // from class: com.neusoft.na.ocservice.SuggestionCity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SuggestionCity createFromParcel(Parcel parcel) {
            return new SuggestionCity(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SuggestionCity[] newArray(int i) {
            return new SuggestionCity[i];
        }
    };
    private String cityCode;
    private String cityName;
    private int suggestionNum;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected SuggestionCity(Parcel parcel) {
        this.cityName = "";
        this.cityCode = "";
        this.suggestionNum = parcel.readInt();
        this.cityName = parcel.readString();
        this.cityCode = parcel.readString();
    }

    public int getSuggestionNum() {
        return this.suggestionNum;
    }

    public void setSuggestionNum(int i) {
        this.suggestionNum = i;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String str) {
        this.cityName = str;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    public void setCityCode(String str) {
        this.cityCode = str;
    }

    public SuggestionCity() {
        this.cityName = "";
        this.cityCode = "";
    }

    public SuggestionCity(int i, String str, String str2) {
        this.cityName = "";
        this.cityCode = "";
        this.suggestionNum = i;
        if (str != null) {
            this.cityName = str;
        }
        if (str2 != null) {
            this.cityCode = str2;
        }
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.suggestionNum);
        parcel.writeString(this.cityName);
        parcel.writeString(this.cityCode);
    }
}
