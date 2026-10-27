package com.ts.avm.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class CmdBean implements Parcelable {
    public static final Parcelable.Creator<CmdBean> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f90a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f91b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int[] j;

    class a implements Parcelable.Creator<CmdBean> {
        a() {
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public CmdBean createFromParcel(Parcel parcel) {
            return new CmdBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public CmdBean[] newArray(int i) {
            return new CmdBean[i];
        }
    }

    public CmdBean() {
    }

    public int a() {
        return this.f;
    }

    public int[] b() {
        int[] iArr = this.j;
        return iArr == null ? new int[4] : iArr;
    }

    public void c(int i) {
        this.f = i;
    }

    public void d(int i) {
        this.c = i;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void e(int i) {
        this.d = i;
    }

    public void f(int[] iArr) {
        this.j = iArr;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f90a);
        parcel.writeInt(this.f91b);
        parcel.writeInt(this.c);
        parcel.writeInt(this.d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        parcel.writeInt(this.g);
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
        parcel.writeInt(i);
        parcel.writeIntArray(this.j);
    }

    protected CmdBean(Parcel parcel) {
        this.f90a = parcel.readInt();
        this.f91b = parcel.readInt();
        this.c = parcel.readInt();
        this.d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = parcel.readInt();
        this.h = parcel.readInt();
        this.i = parcel.readInt();
        parcel.readInt();
        this.j = parcel.createIntArray();
    }
}
