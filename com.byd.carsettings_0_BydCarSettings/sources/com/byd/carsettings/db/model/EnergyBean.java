package com.byd.carsettings.db.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class EnergyBean implements Parcelable {
    public static final Parcelable.Creator<EnergyBean> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f2843a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2844b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2845c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public double f2846d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f2847e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f2848f;
    public String g;
    public int h;
    public int i;
    public double j;
    public double k;

    public static class a implements Parcelable.Creator<EnergyBean> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public EnergyBean createFromParcel(Parcel parcel) {
            return new EnergyBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public EnergyBean[] newArray(int i) {
            return new EnergyBean[i];
        }
    }

    public EnergyBean() {
    }

    public double a() {
        return this.f2846d;
    }

    public void b(double d2) {
        this.f2846d = d2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "EnergyConBean{id=" + this.f2843a + ", type=" + this.f2844b + ", mile=" + this.f2845c + ", avgConsume=" + this.f2846d + ", time=" + this.f2847e + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f2843a);
        parcel.writeInt(this.f2844b);
        parcel.writeInt(this.f2845c);
        parcel.writeDouble(this.f2846d);
        parcel.writeLong(this.f2847e);
        parcel.writeString(this.f2848f);
        parcel.writeString(this.g);
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
        parcel.writeDouble(this.j);
        parcel.writeDouble(this.k);
    }

    public EnergyBean(Parcel parcel) {
        this.f2843a = parcel.readLong();
        this.f2844b = parcel.readInt();
        this.f2845c = parcel.readInt();
        this.f2846d = parcel.readDouble();
        this.f2847e = parcel.readLong();
        this.f2848f = parcel.readString();
        this.g = parcel.readString();
        this.h = parcel.readInt();
        this.i = parcel.readInt();
        this.j = parcel.readDouble();
        this.k = parcel.readDouble();
    }
}
