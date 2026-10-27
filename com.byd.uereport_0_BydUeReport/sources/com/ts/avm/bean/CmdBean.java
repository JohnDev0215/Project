package com.ts.avm.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class CmdBean implements Parcelable {
    public static final Parcelable.Creator<CmdBean> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f4246d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4247e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4248f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f4249g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f4250h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f4251i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f4252j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f4253k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int f4254l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4255m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int[] f4256n;

    public class a implements Parcelable.Creator<CmdBean> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public CmdBean createFromParcel(Parcel parcel) {
            return new CmdBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public CmdBean[] newArray(int i8) {
            return new CmdBean[i8];
        }
    }

    public CmdBean() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int f() {
        return this.f4251i;
    }

    public int[] g() {
        int[] iArr = this.f4256n;
        return iArr == null ? new int[4] : iArr;
    }

    public void h(int i8) {
        this.f4251i = i8;
    }

    public void i(int i8) {
        this.f4248f = i8;
    }

    public void j(int i8) {
        this.f4249g = i8;
    }

    public void k(int[] iArr) {
        this.f4256n = iArr;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeInt(this.f4246d);
        parcel.writeInt(this.f4247e);
        parcel.writeInt(this.f4248f);
        parcel.writeInt(this.f4249g);
        parcel.writeInt(this.f4250h);
        parcel.writeInt(this.f4251i);
        parcel.writeInt(this.f4252j);
        parcel.writeInt(this.f4253k);
        parcel.writeInt(this.f4254l);
        parcel.writeInt(i8);
        parcel.writeIntArray(this.f4256n);
    }

    public CmdBean(Parcel parcel) {
        this.f4246d = parcel.readInt();
        this.f4247e = parcel.readInt();
        this.f4248f = parcel.readInt();
        this.f4249g = parcel.readInt();
        this.f4250h = parcel.readInt();
        this.f4251i = parcel.readInt();
        this.f4252j = parcel.readInt();
        this.f4253k = parcel.readInt();
        this.f4254l = parcel.readInt();
        this.f4255m = parcel.readInt();
        this.f4256n = parcel.createIntArray();
    }
}
