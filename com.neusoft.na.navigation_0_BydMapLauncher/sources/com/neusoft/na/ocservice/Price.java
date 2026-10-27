package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class Price implements Parcelable {
    public static final Parcelable.Creator<Price> CREATOR = new Parcelable.Creator<Price>() { // from class: com.neusoft.na.ocservice.Price.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Price createFromParcel(Parcel parcel) {
            return new Price(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Price[] newArray(int i) {
            return new Price[i];
        }
    };
    private String amount;
    private String currency;
    private int goupId;
    private String unit;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Price() {
    }

    public Price(int i, String str, String str2, String str3) {
        this.goupId = i;
        this.amount = str;
        this.currency = str2;
        this.unit = str3;
    }

    protected Price(Parcel parcel) {
        this.goupId = parcel.readInt();
        this.amount = parcel.readString();
        this.currency = parcel.readString();
        this.unit = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.goupId);
        parcel.writeString(this.amount);
        parcel.writeString(this.currency);
        parcel.writeString(this.unit);
    }

    public int getGoupId() {
        return this.goupId;
    }

    public void setGoupId(int i) {
        this.goupId = i;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String str) {
        this.amount = str;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public String getUnit() {
        return this.unit;
    }

    public void setUnit(String str) {
        this.unit = str;
    }
}
