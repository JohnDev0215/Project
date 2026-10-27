package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class RecommendedGasStationsData implements Parcelable {
    public static final Parcelable.Creator<RecommendedGasStationsData> CREATOR = new Parcelable.Creator<RecommendedGasStationsData>() { // from class: com.neusoft.na.ocservice.RecommendedGasStationsData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecommendedGasStationsData createFromParcel(Parcel parcel) {
            return new RecommendedGasStationsData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public RecommendedGasStationsData[] newArray(int i) {
            return new RecommendedGasStationsData[i];
        }
    };
    int code;
    String msg;
    GasStationsInfo[] resultList;
    int token;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public RecommendedGasStationsData() {
        this.token = -1;
        this.code = -1;
        this.msg = "";
    }

    protected RecommendedGasStationsData(Parcel parcel) {
        this.token = -1;
        this.code = -1;
        this.msg = "";
        this.token = parcel.readInt();
        this.code = parcel.readInt();
        this.msg = parcel.readString();
        this.resultList = (GasStationsInfo[]) parcel.createTypedArray(GasStationsInfo.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.token);
        parcel.writeInt(this.code);
        parcel.writeString(this.msg);
        parcel.writeTypedArray(this.resultList, i);
    }

    public int getToken() {
        return this.token;
    }

    public void setToken(int i) {
        this.token = i;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String str) {
        this.msg = str;
    }

    public GasStationsInfo[] getResultList() {
        return this.resultList;
    }

    public void setResultList(GasStationsInfo[] gasStationsInfoArr) {
        this.resultList = gasStationsInfoArr;
    }
}
