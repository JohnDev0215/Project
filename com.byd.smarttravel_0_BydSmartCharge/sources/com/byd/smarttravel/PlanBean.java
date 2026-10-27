package com.byd.smarttravel;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class PlanBean implements Parcelable {
    public static final Parcelable.Creator<PlanBean> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f1963b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1964c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1965d;
    public boolean e;
    public long f;
    public long g;
    public boolean h;
    public boolean i;
    public String j;
    public long k;
    public String l;
    public long m;
    public long n;
    public boolean o;
    public String p;
    public boolean q;
    public boolean r;

    public class a implements Parcelable.Creator<PlanBean> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PlanBean createFromParcel(Parcel parcel) {
            return new PlanBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public PlanBean[] newArray(int i) {
            return new PlanBean[i];
        }
    }

    public PlanBean() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void f(Parcel parcel) {
        this.f1963b = parcel.readString();
        this.f1964c = parcel.readByte() != 0;
        this.f1965d = parcel.readByte() != 0;
        this.e = parcel.readByte() != 0;
        this.f = parcel.readLong();
        this.g = parcel.readLong();
        this.h = parcel.readByte() != 0;
        this.i = parcel.readByte() != 0;
        this.j = parcel.readString();
        this.k = parcel.readLong();
        this.l = parcel.readString();
        this.m = parcel.readLong();
        this.n = parcel.readLong();
        this.o = parcel.readByte() != 0;
        this.p = parcel.readString();
        this.q = parcel.readByte() != 0;
        this.r = parcel.readByte() != 0;
    }

    public String toString() {
        return "PlanBean{alarmFrom='" + this.f1963b + "', keyMode=" + this.f1964c + ", keyChargeSwitch=" + this.f1965d + ", keyUsecarSwitch=" + this.e + ", keyChargeStartTime=" + this.f + ", keyChargeEndTime=" + this.g + ", keyChargeFull=" + this.h + ", keyChargeNextday=" + this.i + ", keyChargeCycle='" + this.j + "', keyUsecarStartTime=" + this.k + ", keyUsecarCycle='" + this.l + "', keyPriceStartTime=" + this.m + ", keyPriceEndTime=" + this.n + ", keyPriceFull=" + this.o + ", timeZone='" + this.p + "', keyGunStateTips=" + this.q + ", keyAccOffTips=" + this.r + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f1963b);
        parcel.writeByte(this.f1964c ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f1965d ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.e ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.f);
        parcel.writeLong(this.g);
        parcel.writeByte(this.h ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.i ? (byte) 1 : (byte) 0);
        parcel.writeString(this.j);
        parcel.writeLong(this.k);
        parcel.writeString(this.l);
        parcel.writeLong(this.m);
        parcel.writeLong(this.n);
        parcel.writeByte(this.o ? (byte) 1 : (byte) 0);
        parcel.writeString(this.p);
        parcel.writeByte(this.q ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.r ? (byte) 1 : (byte) 0);
    }

    public PlanBean(Parcel parcel) {
        this.f1963b = parcel.readString();
        this.f1964c = parcel.readByte() != 0;
        this.f1965d = parcel.readByte() != 0;
        this.e = parcel.readByte() != 0;
        this.f = parcel.readLong();
        this.g = parcel.readLong();
        this.h = parcel.readByte() != 0;
        this.i = parcel.readByte() != 0;
        this.j = parcel.readString();
        this.k = parcel.readLong();
        this.l = parcel.readString();
        this.m = parcel.readLong();
        this.n = parcel.readLong();
        this.o = parcel.readByte() != 0;
        this.p = parcel.readString();
        this.q = parcel.readByte() != 0;
        this.r = parcel.readByte() != 0;
    }
}
