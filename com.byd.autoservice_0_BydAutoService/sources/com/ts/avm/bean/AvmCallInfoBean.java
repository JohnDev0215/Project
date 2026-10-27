package com.ts.avm.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class AvmCallInfoBean implements Parcelable {
    public static final Parcelable.Creator<AvmCallInfoBean> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f2623b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f2624c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f2625d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2626e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2627f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2628g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f2629h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2630i;

    public class a implements Parcelable.Creator<AvmCallInfoBean> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public AvmCallInfoBean createFromParcel(Parcel parcel) {
            return new AvmCallInfoBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public AvmCallInfoBean[] newArray(int i3) {
            return new AvmCallInfoBean[i3];
        }
    }

    public AvmCallInfoBean(Parcel parcel) {
        this.f2626e = -1;
        this.f2627f = -1;
        this.f2628g = -1;
        this.f2630i = -1;
        this.f2623b = parcel.readString();
        this.f2624c = parcel.readString();
        this.f2625d = parcel.readString();
        this.f2626e = parcel.readInt();
        this.f2627f = parcel.readInt();
        this.f2628g = parcel.readInt();
        this.f2629h = parcel.readByte() != 0;
        this.f2630i = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        String strSubstring;
        if (this.f2623b.length() > 4) {
            String str = this.f2623b;
            strSubstring = str.substring(str.length() - 4, this.f2623b.length());
        } else {
            strSubstring = this.f2623b;
        }
        return "AvmCallInfoBean{phoneNum='" + strSubstring + "', callTime=" + this.f2626e + ", callType=" + this.f2627f + ", callState=" + this.f2628g + ", isMute=" + this.f2629h + ", audioSource=" + this.f2630i + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i3) {
        parcel.writeString(this.f2623b);
        parcel.writeString(this.f2624c);
        parcel.writeString(this.f2625d);
        parcel.writeInt(this.f2626e);
        parcel.writeInt(this.f2627f);
        parcel.writeInt(this.f2628g);
        parcel.writeByte(this.f2629h ? (byte) 1 : (byte) 0);
        parcel.writeInt(this.f2630i);
    }

    public AvmCallInfoBean() {
        this.f2626e = -1;
        this.f2627f = -1;
        this.f2628g = -1;
        this.f2630i = -1;
    }
}
