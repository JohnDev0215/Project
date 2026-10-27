package com.neusoft.na.ocservice;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class SetelDataCallBackInfo implements Parcelable {
    public static final Parcelable.Creator<SetelDataCallBackInfo> CREATOR = new Parcelable.Creator<SetelDataCallBackInfo>() { // from class: com.neusoft.na.ocservice.SetelDataCallBackInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SetelDataCallBackInfo createFromParcel(Parcel parcel) {
            return new SetelDataCallBackInfo(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SetelDataCallBackInfo[] newArray(int i) {
            return new SetelDataCallBackInfo[i];
        }
    };
    int Token;
    int code;
    String msg;
    SetelResultData[] resultList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public SetelDataCallBackInfo() {
        this.Token = -1;
        this.code = -1;
        this.msg = "";
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

    public SetelResultData[] getResultList() {
        return this.resultList;
    }

    public void setResultList(SetelResultData[] setelResultDataArr) {
        this.resultList = setelResultDataArr;
    }

    protected SetelDataCallBackInfo(Parcel parcel) {
        this.Token = -1;
        this.code = -1;
        this.msg = "";
        this.Token = parcel.readInt();
        this.code = parcel.readInt();
        this.msg = parcel.readString();
        this.resultList = (SetelResultData[]) parcel.createTypedArray(SetelResultData.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.Token);
        parcel.writeInt(this.code);
        parcel.writeString(this.msg);
        parcel.writeTypedArray(this.resultList, i);
    }
}
