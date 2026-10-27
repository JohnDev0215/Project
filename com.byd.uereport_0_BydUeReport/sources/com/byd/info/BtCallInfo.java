package com.byd.info;

import android.os.Parcel;
import android.os.Parcelable;
import org.litepal.annotation.Column;

/* JADX INFO: loaded from: classes.dex */
public class BtCallInfo implements Parcelable, Comparable<BtCallInfo> {
    public static final Parcelable.Creator<BtCallInfo> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @Column(unique = true)
    public long f2738d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @Column(defaultValue = "未知")
    public String f2739e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f2740f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2741g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2742h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f2743i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f2744j;

    public class a implements Parcelable.Creator<BtCallInfo> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BtCallInfo createFromParcel(Parcel parcel) {
            return new BtCallInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public BtCallInfo[] newArray(int i8) {
            return new BtCallInfo[i8];
        }
    }

    public BtCallInfo() {
        this.f2739e = "unknown";
        this.f2740f = "unknown";
        this.f2741g = -1;
        this.f2742h = 0;
        this.f2743i = true;
        this.f2744j = "";
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
        return this.f2739e;
    }

    public String h() {
        return this.f2740f;
    }

    public int i() {
        return this.f2742h;
    }

    public String toString() {
        return "BtCallInfo{id=" + this.f2738d + ", name='" + this.f2739e + "', number='" + this.f2740f + "', state=" + this.f2741g + ", time=" + this.f2742h + ", idle=" + this.f2743i + ", date='" + this.f2744j + "'}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeLong(this.f2738d);
        parcel.writeString(this.f2739e);
        parcel.writeString(this.f2740f);
        parcel.writeInt(this.f2741g);
        parcel.writeInt(this.f2742h);
        parcel.writeBoolean(this.f2743i);
        parcel.writeString(this.f2744j);
    }

    public BtCallInfo(Parcel parcel) {
        this.f2739e = "unknown";
        this.f2740f = "unknown";
        this.f2741g = -1;
        this.f2742h = 0;
        this.f2743i = true;
        this.f2744j = "";
        this.f2738d = parcel.readLong();
        this.f2739e = parcel.readString();
        this.f2740f = parcel.readString();
        this.f2741g = parcel.readInt();
        this.f2742h = parcel.readInt();
        this.f2743i = parcel.readBoolean();
        this.f2744j = parcel.readString();
    }
}
