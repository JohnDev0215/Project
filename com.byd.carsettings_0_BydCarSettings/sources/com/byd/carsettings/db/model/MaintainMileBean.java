package com.byd.carsettings.db.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class MaintainMileBean implements Parcelable {
    public static final Parcelable.Creator<MaintainMileBean> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public double f2849a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public double f2850b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2851c;

    public static class a implements Parcelable.Creator<MaintainMileBean> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public MaintainMileBean createFromParcel(Parcel parcel) {
            return new MaintainMileBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public MaintainMileBean[] newArray(int i) {
            return new MaintainMileBean[i];
        }
    }

    public MaintainMileBean() {
    }

    public double a() {
        return this.f2849a;
    }

    public double b() {
        return this.f2850b;
    }

    public boolean c() {
        return this.f2851c;
    }

    public void d(Parcel parcel) {
        this.f2849a = parcel.readDouble();
        this.f2850b = parcel.readDouble();
        this.f2851c = parcel.readInt() != 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void e(double d2) {
        this.f2849a = d2;
    }

    public void f(boolean z) {
        this.f2851c = z;
    }

    public void g(double d2) {
        this.f2850b = d2;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.f2849a);
        parcel.writeDouble(this.f2850b);
        parcel.writeInt(this.f2851c ? 1 : 0);
    }

    public MaintainMileBean(Parcel parcel) {
        this.f2849a = parcel.readDouble();
        this.f2850b = parcel.readDouble();
        this.f2851c = parcel.readInt() != 0;
    }
}
