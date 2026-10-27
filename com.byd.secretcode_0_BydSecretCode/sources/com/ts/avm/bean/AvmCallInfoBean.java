package com.ts.avm.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class AvmCallInfoBean implements Parcelable {
    public static final Parcelable.Creator<AvmCallInfoBean> CREATOR = new a();
    public String b;
    public String c;
    public String d;
    public int e;
    public int f;
    public int g;
    public boolean h;
    public int i;

    public class a implements Parcelable.Creator<AvmCallInfoBean> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public AvmCallInfoBean createFromParcel(Parcel parcel) {
            return new AvmCallInfoBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public AvmCallInfoBean[] newArray(int i) {
            return new AvmCallInfoBean[i];
        }
    }

    public AvmCallInfoBean(Parcel parcel) {
        this.e = -1;
        this.f = -1;
        this.g = -1;
        this.i = -1;
        this.b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readString();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = parcel.readInt();
        this.h = parcel.readByte() != 0;
        this.i = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        String strSubstring;
        if (this.b.length() > 4) {
            String str = this.b;
            strSubstring = str.substring(str.length() - 4, this.b.length());
        } else {
            strSubstring = this.b;
        }
        return "AvmCallInfoBean{phoneNum='" + strSubstring + "', callTime=" + this.e + ", callType=" + this.f + ", callState=" + this.g + ", isMute=" + this.h + ", audioSource=" + this.i + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeString(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        parcel.writeInt(this.g);
        parcel.writeByte(this.h ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.i);
    }

    public AvmCallInfoBean() {
        this.e = -1;
        this.f = -1;
        this.g = -1;
        this.i = -1;
    }
}
