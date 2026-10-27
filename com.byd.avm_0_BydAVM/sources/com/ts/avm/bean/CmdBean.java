package com.ts.avm.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class CmdBean implements Parcelable {
    public static final Parcelable.Creator<CmdBean> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f2606b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f2607c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2608d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int[] l;

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

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int f() {
        return this.g;
    }

    public int[] g() {
        int[] iArr = this.l;
        return iArr == null ? new int[4] : iArr;
    }

    public void h(int i) {
        this.g = i;
    }

    public void i(int i) {
        this.f2608d = i;
    }

    public void j(int i) {
        this.e = i;
    }

    public void k(int[] iArr) {
        this.l = iArr;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f2606b);
        parcel.writeInt(this.f2607c);
        parcel.writeInt(this.f2608d);
        parcel.writeInt(this.e);
        parcel.writeInt(this.f);
        parcel.writeInt(this.g);
        parcel.writeInt(this.h);
        parcel.writeInt(this.i);
        parcel.writeInt(this.j);
        parcel.writeInt(i);
        parcel.writeIntArray(this.l);
    }

    protected CmdBean(Parcel parcel) {
        this.f2606b = parcel.readInt();
        this.f2607c = parcel.readInt();
        this.f2608d = parcel.readInt();
        this.e = parcel.readInt();
        this.f = parcel.readInt();
        this.g = parcel.readInt();
        this.h = parcel.readInt();
        this.i = parcel.readInt();
        this.j = parcel.readInt();
        this.k = parcel.readInt();
        this.l = parcel.createIntArray();
    }
}
