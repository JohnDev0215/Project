package com.byd.carsettings.db.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class MaintainMileBean implements Parcelable {
    public static final Parcelable.Creator<MaintainMileBean> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public double f2694d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public double f2695e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2696f;

    public class a implements Parcelable.Creator<MaintainMileBean> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public MaintainMileBean createFromParcel(Parcel parcel) {
            return new MaintainMileBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public MaintainMileBean[] newArray(int i8) {
            return new MaintainMileBean[i8];
        }
    }

    public MaintainMileBean() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public double f() {
        return this.f2694d;
    }

    public double g() {
        return this.f2695e;
    }

    public boolean h() {
        return this.f2696f;
    }

    public void i(double d8) {
        this.f2694d = d8;
    }

    public void j(boolean z7) {
        this.f2696f = z7;
    }

    public void k(double d8) {
        this.f2695e = d8;
    }

    public String toString() {
        return "MaintainMileBean{mile=" + this.f2694d + ", mileTarget=" + this.f2695e + ", mileState=" + this.f2696f + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeDouble(this.f2694d);
        parcel.writeDouble(this.f2695e);
        parcel.writeInt(this.f2696f ? 1 : 0);
    }

    public MaintainMileBean(Parcel parcel) {
        this.f2694d = parcel.readDouble();
        this.f2695e = parcel.readDouble();
        this.f2696f = parcel.readInt() != 0;
    }
}
