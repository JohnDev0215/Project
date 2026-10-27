package com.byd.carsettings.db.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class MaintainTimeBean implements Parcelable {
    public static final Parcelable.Creator<MaintainTimeBean> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f2852a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f2853b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f2854c;

    public static class a implements Parcelable.Creator<MaintainTimeBean> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public MaintainTimeBean createFromParcel(Parcel parcel) {
            return new MaintainTimeBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public MaintainTimeBean[] newArray(int i) {
            return new MaintainTimeBean[i];
        }
    }

    public MaintainTimeBean() {
    }

    public int a() {
        return this.f2852a;
    }

    public long b() {
        return this.f2853b;
    }

    public boolean c() {
        return this.f2854c;
    }

    public void d(Parcel parcel) {
        this.f2852a = parcel.readInt();
        this.f2853b = parcel.readLong();
        this.f2854c = parcel.readInt() != 0;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void e(int i) {
        this.f2852a = i;
    }

    public void f(boolean z) {
        this.f2854c = z;
    }

    public void g(long j) {
        this.f2853b = j;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f2852a);
        parcel.writeLong(this.f2853b);
        parcel.writeInt(this.f2854c ? 1 : 0);
    }

    public MaintainTimeBean(Parcel parcel) {
        this.f2852a = parcel.readInt();
        this.f2853b = parcel.readLong();
        this.f2854c = parcel.readInt() != 0;
    }
}
