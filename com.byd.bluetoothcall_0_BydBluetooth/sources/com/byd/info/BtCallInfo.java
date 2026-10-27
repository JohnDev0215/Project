package com.byd.info;

import android.os.Parcel;
import android.os.Parcelable;
import org.litepal.annotation.Column;

/* JADX INFO: loaded from: classes.dex */
public class BtCallInfo implements Parcelable, Comparable<BtCallInfo> {
    public static final Parcelable.Creator<BtCallInfo> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Column(unique = true)
    private long f3549b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Column(defaultValue = "未知")
    private String f3550c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private String f3551d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private int f3552e;
    private int f;
    private boolean g;
    private String h;

    class a implements Parcelable.Creator<BtCallInfo> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public BtCallInfo createFromParcel(Parcel parcel) {
            return new BtCallInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public BtCallInfo[] newArray(int i) {
            return new BtCallInfo[i];
        }
    }

    public BtCallInfo() {
        this.f3550c = "unknown";
        this.f3551d = "unknown";
        this.f3552e = -1;
        this.f = 0;
        this.g = false;
        this.h = "";
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof BtCallInfo) {
            BtCallInfo btCallInfo = (BtCallInfo) obj;
            if (l().equals(btCallInfo.l()) && m().equals(btCallInfo.m())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: i, reason: merged with bridge method [inline-methods] */
    public int compareTo(BtCallInfo btCallInfo) {
        long jO = btCallInfo.o() - o();
        if (jO > 0) {
            return 1;
        }
        return jO == 0 ? 0 : -1;
    }

    public String j() {
        return this.h;
    }

    public long k() {
        return this.f3549b;
    }

    public String l() {
        return this.f3550c;
    }

    public String m() {
        return this.f3551d;
    }

    public int n() {
        return this.f3552e;
    }

    public int o() {
        return this.f;
    }

    public boolean p() {
        return this.g;
    }

    public void q(String str) {
        this.h = str;
    }

    public void r(long j) {
        this.f3549b = j;
    }

    public void s(boolean z) {
        this.g = z;
    }

    public void t(String str) {
        this.f3550c = str;
    }

    public String toString() {
        return "BtCallInfo{id=" + this.f3549b + ", name='" + this.f3550c + "', number='" + this.f3551d + "', state=" + this.f3552e + ", time=" + this.f + ", idle=" + this.g + ", date='" + this.h + "'}";
    }

    public void u(String str) {
        this.f3551d = str;
    }

    public void v(int i) {
        this.f3552e = i;
    }

    public void w(int i) {
        this.f = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f3549b);
        parcel.writeString(this.f3550c);
        parcel.writeString(this.f3551d);
        parcel.writeInt(this.f3552e);
        parcel.writeInt(this.f);
        parcel.writeBoolean(this.g);
        parcel.writeString(this.h);
    }

    public BtCallInfo(Parcel parcel) {
        this.f3550c = "unknown";
        this.f3551d = "unknown";
        this.f3552e = -1;
        this.f = 0;
        this.g = false;
        this.h = "";
        this.f3549b = parcel.readLong();
        this.f3550c = parcel.readString();
        this.f3551d = parcel.readString();
        this.f3552e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = parcel.readBoolean();
        this.h = parcel.readString();
    }
}
