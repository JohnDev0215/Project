package com.byd.carsettings.db.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class EnergyBean implements Parcelable {
    public static final Parcelable.Creator<EnergyBean> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public long f2683d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2684e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2685f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public double f2686g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f2687h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f2688i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f2689j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2690k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f2691l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public double f2692m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public double f2693n;

    public class a implements Parcelable.Creator<EnergyBean> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public EnergyBean createFromParcel(Parcel parcel) {
            return new EnergyBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public EnergyBean[] newArray(int i8) {
            return new EnergyBean[i8];
        }
    }

    public EnergyBean() {
    }

    public void A(int i8) {
        this.f2684e = i8;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public double f() {
        return this.f2686g;
    }

    public double g() {
        return this.f2692m;
    }

    public double h() {
        return this.f2693n;
    }

    public long i() {
        return this.f2683d;
    }

    public int j() {
        return this.f2690k;
    }

    public int k() {
        return this.f2691l;
    }

    public int l() {
        return this.f2685f;
    }

    public String m() {
        return this.f2688i;
    }

    public String n() {
        return this.f2689j;
    }

    public long o() {
        return this.f2687h;
    }

    public int p() {
        return this.f2684e;
    }

    public void q(double d8) {
        this.f2686g = d8;
    }

    public void r(double d8) {
        this.f2692m = d8;
    }

    public void s(double d8) {
        this.f2693n = d8;
    }

    public void t(long j8) {
        this.f2683d = j8;
    }

    public String toString() {
        return "EnergyConBean{id=" + this.f2683d + ", type=" + this.f2684e + ", mile=" + this.f2685f + ", avgConsume=" + this.f2686g + ", time=" + this.f2687h + '}';
    }

    public void u(int i8) {
        this.f2690k = i8;
    }

    public void v(int i8) {
        this.f2691l = i8;
    }

    public void w(int i8) {
        this.f2685f = i8;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeLong(this.f2683d);
        parcel.writeInt(this.f2684e);
        parcel.writeInt(this.f2685f);
        parcel.writeDouble(this.f2686g);
        parcel.writeLong(this.f2687h);
        parcel.writeString(this.f2688i);
        parcel.writeString(this.f2689j);
        parcel.writeInt(this.f2690k);
        parcel.writeInt(this.f2691l);
        parcel.writeDouble(this.f2692m);
        parcel.writeDouble(this.f2693n);
    }

    public void x(String str) {
        this.f2688i = str;
    }

    public void y(String str) {
        this.f2689j = str;
    }

    public void z(long j8) {
        this.f2687h = j8;
    }

    public EnergyBean(Parcel parcel) {
        this.f2683d = parcel.readLong();
        this.f2684e = parcel.readInt();
        this.f2685f = parcel.readInt();
        this.f2686g = parcel.readDouble();
        this.f2687h = parcel.readLong();
        this.f2688i = parcel.readString();
        this.f2689j = parcel.readString();
        this.f2690k = parcel.readInt();
        this.f2691l = parcel.readInt();
        this.f2692m = parcel.readDouble();
        this.f2693n = parcel.readDouble();
    }
}
