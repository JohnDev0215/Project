package com.byd.trafficmonitor.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class TrafficInfo implements Comparable<TrafficInfo>, Parcelable {
    public static final Parcelable.Creator<TrafficInfo> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static int f4414b = 1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static int f4415c = 0;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static int f4416d = 1;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static int f4417e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f4418f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public String f4419g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4420h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4421i;
    public int j;
    public long k;
    public long l;

    public class a implements Parcelable.Creator<TrafficInfo> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public TrafficInfo createFromParcel(Parcel parcel) {
            return new TrafficInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public TrafficInfo[] newArray(int i2) {
            return new TrafficInfo[i2];
        }
    }

    public TrafficInfo() {
        this.j = 1;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: j, reason: merged with bridge method [inline-methods] */
    public int compareTo(TrafficInfo trafficInfo) {
        long j = trafficInfo.l;
        long j2 = trafficInfo.k;
        long j3 = j + j2;
        long j4 = this.l;
        long j5 = this.k;
        return j3 == j4 + j5 ? this.f4420h - trafficInfo.f4420h : (int) ((j + j2) - (j4 + j5));
    }

    public String k() {
        return this.f4419g;
    }

    public int l() {
        return this.j;
    }

    public long m() {
        return this.k;
    }

    public long n() {
        return this.l;
    }

    public String o() {
        return this.f4418f;
    }

    public int p() {
        return this.f4420h;
    }

    public void q(String str) {
        this.f4419g = str;
    }

    public void r(int i2) {
        this.j = i2;
    }

    public void s(int i2) {
        this.f4421i = i2;
    }

    public void t(long j) {
        this.k = j;
    }

    public String toString() {
        return "TrafficInfo{packageName='" + this.f4418f + "', appName='" + this.f4419g + "', uid=" + this.f4420h + ", isSystemApp=" + this.f4421i + ", enableMobileTraffic=" + this.j + ", mobileRxByte=" + this.k + ", mobileTxByte=" + this.l + '}';
    }

    public void u(long j) {
        this.l = j;
    }

    public void v(String str) {
        this.f4418f = str;
    }

    public void w(int i2) {
        this.f4420h = i2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeString(this.f4418f);
        parcel.writeString(this.f4419g);
        parcel.writeInt(this.f4420h);
        parcel.writeInt(this.f4421i);
        parcel.writeInt(this.j);
        parcel.writeLong(this.l);
        parcel.writeLong(this.k);
    }

    public TrafficInfo(Parcel parcel) {
        this.j = 1;
        this.f4418f = parcel.readString();
        this.f4419g = parcel.readString();
        this.f4420h = parcel.readInt();
        this.f4421i = parcel.readInt();
        this.j = parcel.readInt();
        this.l = parcel.readLong();
        this.k = parcel.readLong();
    }
}
