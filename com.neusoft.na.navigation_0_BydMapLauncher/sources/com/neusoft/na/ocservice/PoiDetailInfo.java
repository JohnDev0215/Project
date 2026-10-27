package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class PoiDetailInfo implements Parcelable {
    public static final Parcelable.Creator<PoiDetailInfo> CREATOR = new Parcelable.Creator<PoiDetailInfo>() { // from class: com.neusoft.na.ocservice.PoiDetailInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PoiDetailInfo createFromParcel(Parcel parcel) {
            return new PoiDetailInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PoiDetailInfo[] newArray(int i) {
            return new PoiDetailInfo[i];
        }
    };
    private String address;
    private List<ChargingPriceInfo> chargingPriceInfo;
    private List<OpeningHours> chargingPriceOpeningHours;
    private List<OpeningHours> openingHours;
    private List<String> phoneNumber;
    private String poiScore;
    private List<Price> price;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PoiDetailInfo() {
    }

    public String getPoiScore() {
        return this.poiScore;
    }

    public void setPoiScore(String str) {
        this.poiScore = str;
    }

    public List<ChargingPriceInfo> getChargingPriceInfo() {
        return this.chargingPriceInfo;
    }

    public void setChargingPriceInfo(List<ChargingPriceInfo> list) {
        this.chargingPriceInfo = list;
    }

    public List<OpeningHours> getChargingPriceOpeningHours() {
        return this.chargingPriceOpeningHours;
    }

    public void setChargingPriceOpeningHours(List<OpeningHours> list) {
        this.chargingPriceOpeningHours = list;
    }

    public List<Price> getPrice() {
        return this.price;
    }

    public void setPrice(List<Price> list) {
        this.price = list;
    }

    public List<OpeningHours> getOpeningHours() {
        return this.openingHours;
    }

    public void setOpeningHours(List<OpeningHours> list) {
        this.openingHours = list;
    }

    public List<String> getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(List<String> list) {
        this.phoneNumber = list;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    protected PoiDetailInfo(Parcel parcel) {
        this.poiScore = parcel.readString();
        this.chargingPriceInfo = parcel.createTypedArrayList(ChargingPriceInfo.CREATOR);
        this.chargingPriceOpeningHours = parcel.createTypedArrayList(OpeningHours.CREATOR);
        this.price = parcel.createTypedArrayList(Price.CREATOR);
        this.openingHours = parcel.createTypedArrayList(OpeningHours.CREATOR);
        this.phoneNumber = parcel.createStringArrayList();
        this.address = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.poiScore);
        parcel.writeTypedList(this.chargingPriceInfo);
        parcel.writeTypedList(this.chargingPriceOpeningHours);
        parcel.writeTypedList(this.price);
        parcel.writeTypedList(this.openingHours);
        parcel.writeStringList(this.phoneNumber);
        parcel.writeString(this.address);
    }
}
