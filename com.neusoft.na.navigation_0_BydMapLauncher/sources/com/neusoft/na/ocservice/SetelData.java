package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class SetelData implements Parcelable {
    public static final Parcelable.Creator<SetelData> CREATOR = new Parcelable.Creator<SetelData>() { // from class: com.neusoft.na.ocservice.SetelData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SetelData createFromParcel(Parcel parcel) {
            return new SetelData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SetelData[] newArray(int i) {
            return new SetelData[i];
        }
    };
    double Poslat;
    double Poslon;
    int Range;
    int Token;
    SetelSearchData[] searchResult;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SetelData() {
        this.Token = -1;
        this.Poslon = -1.0d;
        this.Poslat = -1.0d;
        this.Range = -1;
    }

    public int getToken() {
        return this.Token;
    }

    public void setToken(int i) {
        this.Token = i;
    }

    public double getPoslon() {
        return this.Poslon;
    }

    public void setPoslon(double d) {
        this.Poslon = d;
    }

    public double getPoslat() {
        return this.Poslat;
    }

    public void setPoslat(double d) {
        this.Poslat = d;
    }

    public int getRange() {
        return this.Range;
    }

    public void setRange(int i) {
        this.Range = i;
    }

    public SetelSearchData[] getSearchResult() {
        return this.searchResult;
    }

    public void setSearchResult(SetelSearchData[] setelSearchDataArr) {
        this.searchResult = setelSearchDataArr;
    }

    protected SetelData(Parcel parcel) {
        this.Token = -1;
        this.Poslon = -1.0d;
        this.Poslat = -1.0d;
        this.Range = -1;
        this.Token = parcel.readInt();
        this.Poslon = parcel.readDouble();
        this.Poslat = parcel.readDouble();
        this.Range = parcel.readInt();
        this.searchResult = (SetelSearchData[]) parcel.createTypedArray(SetelSearchData.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.Token);
        parcel.writeDouble(this.Poslon);
        parcel.writeDouble(this.Poslat);
        parcel.writeInt(this.Range);
        parcel.writeTypedArray(this.searchResult, i);
    }
}
