package com.byd.smarttravel;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class PlanBean implements Parcelable {
    public static final Parcelable.Creator<PlanBean> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f3006d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f3007e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f3008f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f3009g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f3010h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public long f3011i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f3012j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f3013k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public String f3014l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public long f3015m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f3016n;

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public long f3017o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f3018p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f3019q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String f3020r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f3021s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public boolean f3022t;

    public class a implements Parcelable.Creator<PlanBean> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public PlanBean createFromParcel(Parcel parcel) {
            return new PlanBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public PlanBean[] newArray(int i8) {
            return new PlanBean[i8];
        }
    }

    public PlanBean() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void f(Parcel parcel) {
        this.f3006d = parcel.readString();
        this.f3007e = parcel.readByte() != 0;
        this.f3008f = parcel.readByte() != 0;
        this.f3009g = parcel.readByte() != 0;
        this.f3010h = parcel.readLong();
        this.f3011i = parcel.readLong();
        this.f3012j = parcel.readByte() != 0;
        this.f3013k = parcel.readByte() != 0;
        this.f3014l = parcel.readString();
        this.f3015m = parcel.readLong();
        this.f3016n = parcel.readString();
        this.f3017o = parcel.readLong();
        this.f3018p = parcel.readLong();
        this.f3019q = parcel.readByte() != 0;
        this.f3020r = parcel.readString();
        this.f3021s = parcel.readByte() != 0;
        this.f3022t = parcel.readByte() != 0;
    }

    public String toString() {
        return "PlanBean{alarmFrom='" + this.f3006d + "', keyMode=" + this.f3007e + ", keyChargeSwitch=" + this.f3008f + ", keyUsecarSwitch=" + this.f3009g + ", keyChargeStartTime=" + this.f3010h + ", keyChargeEndTime=" + this.f3011i + ", keyChargeFull=" + this.f3012j + ", keyChargeNextday=" + this.f3013k + ", keyChargeCycle='" + this.f3014l + "', keyUsecarStartTime=" + this.f3015m + ", keyUsecarCycle='" + this.f3016n + "', keyPriceStartTime=" + this.f3017o + ", keyPriceEndTime=" + this.f3018p + ", keyPriceFull=" + this.f3019q + ", timeZone='" + this.f3020r + "', keyGunStateTips=" + this.f3021s + ", keyAccOffTips=" + this.f3022t + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeString(this.f3006d);
        parcel.writeByte(this.f3007e ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f3008f ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f3009g ? (byte) 1 : (byte) 0);
        parcel.writeLong(this.f3010h);
        parcel.writeLong(this.f3011i);
        parcel.writeByte(this.f3012j ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f3013k ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f3014l);
        parcel.writeLong(this.f3015m);
        parcel.writeString(this.f3016n);
        parcel.writeLong(this.f3017o);
        parcel.writeLong(this.f3018p);
        parcel.writeByte(this.f3019q ? (byte) 1 : (byte) 0);
        parcel.writeString(this.f3020r);
        parcel.writeByte(this.f3021s ? (byte) 1 : (byte) 0);
        parcel.writeByte(this.f3022t ? (byte) 1 : (byte) 0);
    }

    public PlanBean(Parcel parcel) {
        this.f3006d = parcel.readString();
        this.f3007e = parcel.readByte() != 0;
        this.f3008f = parcel.readByte() != 0;
        this.f3009g = parcel.readByte() != 0;
        this.f3010h = parcel.readLong();
        this.f3011i = parcel.readLong();
        this.f3012j = parcel.readByte() != 0;
        this.f3013k = parcel.readByte() != 0;
        this.f3014l = parcel.readString();
        this.f3015m = parcel.readLong();
        this.f3016n = parcel.readString();
        this.f3017o = parcel.readLong();
        this.f3018p = parcel.readLong();
        this.f3019q = parcel.readByte() != 0;
        this.f3020r = parcel.readString();
        this.f3021s = parcel.readByte() != 0;
        this.f3022t = parcel.readByte() != 0;
    }
}
