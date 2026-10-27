package com.ts.avm.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class AvmCallInfoBean implements Parcelable {
    public static final Parcelable.Creator<AvmCallInfoBean> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f88a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private String f89b;
    private String c;
    private int d;
    private int e;
    private int f;
    private boolean g;
    private int h;

    class a implements Parcelable.Creator<AvmCallInfoBean> {
        a() {
        }

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

    protected AvmCallInfoBean(Parcel parcel) {
        this.d = -1;
        this.e = -1;
        this.f = -1;
        this.h = -1;
        this.f88a = parcel.readString();
        this.f89b = parcel.readString();
        this.c = parcel.readString();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = parcel.readByte() != 0;
        this.h = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        String strSubstring;
        if (this.f88a.length() > 4) {
            String str = this.f88a;
            strSubstring = str.substring(str.length() - 4, this.f88a.length());
        } else {
            strSubstring = this.f88a;
        }
        return "AvmCallInfoBean{phoneNum='" + strSubstring + "', callTime=" + this.d + ", callType=" + this.e + ", callState=" + this.f + ", isMute=" + this.g + ", audioSource=" + this.h + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f88a);
        parcel.writeString(this.f89b);
        parcel.writeString(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        parcel.writeByte(this.g ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.h);
    }

    public AvmCallInfoBean() {
        this.d = -1;
        this.e = -1;
        this.f = -1;
        this.h = -1;
    }
}
