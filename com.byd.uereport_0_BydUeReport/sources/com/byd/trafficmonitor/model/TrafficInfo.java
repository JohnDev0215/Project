package com.byd.trafficmonitor.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class TrafficInfo implements Comparable<TrafficInfo>, Parcelable {
    public static final Parcelable.Creator<TrafficInfo> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static int f3025d = 1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f3026e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f3027f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f3028g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f3029h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f3030i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f3031j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public long f3032k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public long f3033l;

    public class a implements Parcelable.Creator<TrafficInfo> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public TrafficInfo createFromParcel(Parcel parcel) {
            return new TrafficInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public TrafficInfo[] newArray(int i8) {
            return new TrafficInfo[i8];
        }
    }

    public TrafficInfo() {
        this.f3031j = 1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: f, reason: merged with bridge method [inline-methods] */
    public int compareTo(TrafficInfo trafficInfo) {
        long j8 = trafficInfo.f3033l;
        long j9 = this.f3033l;
        if (j8 == j9) {
            j8 = trafficInfo.f3032k;
            j9 = this.f3032k;
        }
        return (int) (j8 - j9);
    }

    public String g() {
        return this.f3028g;
    }

    public int h() {
        return this.f3031j;
    }

    public int i() {
        return this.f3030i;
    }

    public long j() {
        return this.f3032k;
    }

    public long k() {
        return this.f3033l;
    }

    public String l() {
        return this.f3027f;
    }

    public int m() {
        return this.f3029h;
    }

    public void n(String str) {
        this.f3028g = str;
    }

    public void o(int i8) {
        this.f3031j = i8;
    }

    public void p(int i8) {
        this.f3030i = i8;
    }

    public void q(long j8) {
        this.f3032k = j8;
    }

    public void r(long j8) {
        this.f3033l = j8;
    }

    public void s(String str) {
        this.f3027f = str;
    }

    public void t(int i8) {
        this.f3029h = i8;
    }

    public String toString() {
        return "TrafficInfo{packageName='" + this.f3027f + "', appName='" + this.f3028g + "', uid=" + this.f3029h + ", isSystemApp=" + this.f3030i + ", enableMobileTraffic=" + this.f3031j + ", mobileRxByte=" + this.f3032k + ", mobileTxByte=" + this.f3033l + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeString(this.f3027f);
        parcel.writeString(this.f3028g);
        parcel.writeInt(this.f3029h);
        parcel.writeInt(this.f3030i);
        parcel.writeInt(this.f3031j);
        parcel.writeLong(this.f3033l);
        parcel.writeLong(this.f3032k);
    }

    public TrafficInfo(Parcel parcel) {
        this.f3031j = 1;
        this.f3027f = parcel.readString();
        this.f3028g = parcel.readString();
        this.f3029h = parcel.readInt();
        this.f3030i = parcel.readInt();
        this.f3031j = parcel.readInt();
        this.f3033l = parcel.readLong();
        this.f3032k = parcel.readLong();
    }
}
