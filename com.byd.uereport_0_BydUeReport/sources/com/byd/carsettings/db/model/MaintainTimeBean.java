package com.byd.carsettings.db.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class MaintainTimeBean implements Parcelable {
    public static final Parcelable.Creator<MaintainTimeBean> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2697d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f2698e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2699f;

    public class a implements Parcelable.Creator<MaintainTimeBean> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public MaintainTimeBean createFromParcel(Parcel parcel) {
            return new MaintainTimeBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public MaintainTimeBean[] newArray(int i8) {
            return new MaintainTimeBean[i8];
        }
    }

    public MaintainTimeBean() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int f() {
        return this.f2697d;
    }

    public long g() {
        return this.f2698e;
    }

    public boolean h() {
        return this.f2699f;
    }

    public void i(int i8) {
        this.f2697d = i8;
    }

    public void j(boolean z7) {
        this.f2699f = z7;
    }

    public void k(long j8) {
        this.f2698e = j8;
    }

    public String toString() {
        return "MaintainTimeBean{time=" + this.f2697d + ", timeTarget=" + this.f2698e + ", timeState=" + this.f2699f + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeInt(this.f2697d);
        parcel.writeLong(this.f2698e);
        parcel.writeInt(this.f2699f ? 1 : 0);
    }

    public MaintainTimeBean(Parcel parcel) {
        this.f2697d = parcel.readInt();
        this.f2698e = parcel.readLong();
        this.f2699f = parcel.readInt() != 0;
    }
}
