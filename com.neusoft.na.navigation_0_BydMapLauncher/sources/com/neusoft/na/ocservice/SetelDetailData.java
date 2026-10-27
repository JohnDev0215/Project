package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class SetelDetailData implements Parcelable {
    public static final Parcelable.Creator<SetelDetailData> CREATOR = new Parcelable.Creator<SetelDetailData>() { // from class: com.neusoft.na.ocservice.SetelDetailData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SetelDetailData createFromParcel(Parcel parcel) {
            return new SetelDetailData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SetelDetailData[] newArray(int i) {
            return new SetelDetailData[i];
        }
    };
    int Token;
    int availablePumps;
    int code;
    String msg;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getToken() {
        return this.Token;
    }

    public void setToken(int i) {
        this.Token = i;
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

    public int getAvailablePumps() {
        return this.availablePumps;
    }

    public void setAvailablePumps(int i) {
        this.availablePumps = i;
    }

    protected SetelDetailData(Parcel parcel) {
        this.Token = -1;
        this.code = -1;
        this.msg = "";
        this.availablePumps = -1;
        this.Token = parcel.readInt();
        this.code = parcel.readInt();
        this.msg = parcel.readString();
        this.availablePumps = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.Token);
        parcel.writeInt(this.code);
        parcel.writeString(this.msg);
        parcel.writeInt(this.availablePumps);
    }
}
