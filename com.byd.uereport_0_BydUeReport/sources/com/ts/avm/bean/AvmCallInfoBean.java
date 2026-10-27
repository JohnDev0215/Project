package com.ts.avm.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class AvmCallInfoBean implements Parcelable {
    public static final Parcelable.Creator<AvmCallInfoBean> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f4238d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f4239e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f4240f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4241g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4242h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4243i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f4244j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f4245k;

    public class a implements Parcelable.Creator<AvmCallInfoBean> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public AvmCallInfoBean createFromParcel(Parcel parcel) {
            return new AvmCallInfoBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public AvmCallInfoBean[] newArray(int i8) {
            return new AvmCallInfoBean[i8];
        }
    }

    public AvmCallInfoBean(Parcel parcel) {
        this.f4241g = -1;
        this.f4242h = -1;
        this.f4243i = -1;
        this.f4245k = -1;
        this.f4238d = parcel.readString();
        this.f4239e = parcel.readString();
        this.f4240f = parcel.readString();
        this.f4241g = parcel.readInt();
        this.f4242h = parcel.readInt();
        this.f4243i = parcel.readInt();
        this.f4244j = parcel.readByte() != 0;
        this.f4245k = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        String strSubstring;
        if (this.f4238d.length() > 4) {
            String str = this.f4238d;
            strSubstring = str.substring(str.length() - 4, this.f4238d.length());
        } else {
            strSubstring = this.f4238d;
        }
        return "AvmCallInfoBean{phoneNum='" + strSubstring + "', callTime=" + this.f4241g + ", callType=" + this.f4242h + ", callState=" + this.f4243i + ", isMute=" + this.f4244j + ", audioSource=" + this.f4245k + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeString(this.f4238d);
        parcel.writeString(this.f4239e);
        parcel.writeString(this.f4240f);
        parcel.writeInt(this.f4241g);
        parcel.writeInt(this.f4242h);
        parcel.writeInt(this.f4243i);
        parcel.writeByte(this.f4244j ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.f4245k);
    }

    public AvmCallInfoBean() {
        this.f4241g = -1;
        this.f4242h = -1;
        this.f4243i = -1;
        this.f4245k = -1;
    }
}
