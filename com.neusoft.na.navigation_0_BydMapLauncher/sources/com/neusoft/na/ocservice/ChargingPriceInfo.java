package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class ChargingPriceInfo implements Parcelable {
    public static final Parcelable.Creator<ChargingPriceInfo> CREATOR = new Parcelable.Creator<ChargingPriceInfo>() { // from class: com.neusoft.na.ocservice.ChargingPriceInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChargingPriceInfo createFromParcel(Parcel parcel) {
            return new ChargingPriceInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ChargingPriceInfo[] newArray(int i) {
            return new ChargingPriceInfo[i];
        }
    };
    private String chargingCurrency;
    private String chargingPrice;
    private String chargingUnit;
    private String chargingUnitAmount;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ChargingPriceInfo() {
    }

    public ChargingPriceInfo(String str, String str2, String str3, String str4) {
        this.chargingPrice = str;
        this.chargingCurrency = str2;
        this.chargingUnit = str3;
        this.chargingUnitAmount = str4;
    }

    public String getChargingPrice() {
        return this.chargingPrice;
    }

    public void setChargingPrice(String str) {
        this.chargingPrice = str;
    }

    public String getChargingCurrency() {
        return this.chargingCurrency;
    }

    public void setChargingCurrency(String str) {
        this.chargingCurrency = str;
    }

    public String getChargingUnit() {
        return this.chargingUnit;
    }

    public void setChargingUnit(String str) {
        this.chargingUnit = str;
    }

    public String getChargingUnitAmount() {
        return this.chargingUnitAmount;
    }

    public void setChargingUnitAmount(String str) {
        this.chargingUnitAmount = str;
    }

    protected ChargingPriceInfo(Parcel parcel) {
        this.chargingPrice = parcel.readString();
        this.chargingCurrency = parcel.readString();
        this.chargingUnit = parcel.readString();
        this.chargingUnitAmount = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.chargingPrice);
        parcel.writeString(this.chargingCurrency);
        parcel.writeString(this.chargingUnit);
        parcel.writeString(this.chargingUnitAmount);
    }
}
