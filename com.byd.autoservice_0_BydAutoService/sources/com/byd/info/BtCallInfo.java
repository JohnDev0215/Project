package com.byd.info;

import android.os.Parcel;
import android.os.Parcelable;
import org.litepal.annotation.Column;

/* JADX INFO: loaded from: classes.dex */
public class BtCallInfo implements Parcelable, Comparable<BtCallInfo> {
    public static final Parcelable.Creator<BtCallInfo> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Column(unique = true)
    public long f1865b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Column(defaultValue = "未知")
    public String f1866c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f1867d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1868e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1869f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f1870g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public String f1871h;

    public class a implements Parcelable.Creator<BtCallInfo> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BtCallInfo createFromParcel(Parcel parcel) {
            return new BtCallInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public BtCallInfo[] newArray(int i3) {
            return new BtCallInfo[i3];
        }
    }

    public BtCallInfo() {
        this.f1866c = "unknown";
        this.f1867d = "unknown";
        this.f1868e = -1;
        this.f1869f = 0;
        this.f1870g = true;
        this.f1871h = "";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof BtCallInfo) {
            BtCallInfo btCallInfo = (BtCallInfo) obj;
            if (g().equals(btCallInfo.g()) && h().equals(btCallInfo.h())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public int compareTo(BtCallInfo btCallInfo) {
        long jI = btCallInfo.i() - i();
        if (jI > 0) {
            return 1;
        }
        return jI == 0 ? 0 : -1;
    }

    public String g() {
        return this.f1866c;
    }

    public String h() {
        return this.f1867d;
    }

    public int i() {
        return this.f1869f;
    }

    public String toString() {
        return "BtCallInfo{id=" + this.f1865b + ", name='" + this.f1866c + "', number='" + this.f1867d + "', state=" + this.f1868e + ", time=" + this.f1869f + ", idle=" + this.f1870g + ", date='" + this.f1871h + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i3) {
        parcel.writeLong(this.f1865b);
        parcel.writeString(this.f1866c);
        parcel.writeString(this.f1867d);
        parcel.writeInt(this.f1868e);
        parcel.writeInt(this.f1869f);
        parcel.writeBoolean(this.f1870g);
        parcel.writeString(this.f1871h);
    }

    public BtCallInfo(Parcel parcel) {
        this.f1866c = "unknown";
        this.f1867d = "unknown";
        this.f1868e = -1;
        this.f1869f = 0;
        this.f1870g = true;
        this.f1871h = "";
        this.f1865b = parcel.readLong();
        this.f1866c = parcel.readString();
        this.f1867d = parcel.readString();
        this.f1868e = parcel.readInt();
        this.f1869f = parcel.readInt();
        this.f1870g = parcel.readBoolean();
        this.f1871h = parcel.readString();
    }
}
