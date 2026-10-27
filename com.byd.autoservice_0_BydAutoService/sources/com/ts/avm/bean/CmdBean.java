package com.ts.avm.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class CmdBean implements Parcelable {
    public static final Parcelable.Creator<CmdBean> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2631b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2632c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2633d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2634e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f2635f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f2636g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f2637h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f2638i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public int f2639j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public int f2640k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    public int[] f2641l;

    public class a implements Parcelable.Creator<CmdBean> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public CmdBean createFromParcel(Parcel parcel) {
            return new CmdBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public CmdBean[] newArray(int i3) {
            return new CmdBean[i3];
        }
    }

    public CmdBean() {
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int f() {
        return this.f2636g;
    }

    public int[] g() {
        int[] iArr = this.f2641l;
        return iArr == null ? new int[4] : iArr;
    }

    public void h(int i3) {
        this.f2636g = i3;
    }

    public void i(int i3) {
        this.f2633d = i3;
    }

    public void j(int i3) {
        this.f2634e = i3;
    }

    public void k(int[] iArr) {
        this.f2641l = iArr;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i3) {
        parcel.writeInt(this.f2631b);
        parcel.writeInt(this.f2632c);
        parcel.writeInt(this.f2633d);
        parcel.writeInt(this.f2634e);
        parcel.writeInt(this.f2635f);
        parcel.writeInt(this.f2636g);
        parcel.writeInt(this.f2637h);
        parcel.writeInt(this.f2638i);
        parcel.writeInt(this.f2639j);
        parcel.writeInt(i3);
        parcel.writeIntArray(this.f2641l);
    }

    public CmdBean(Parcel parcel) {
        this.f2631b = parcel.readInt();
        this.f2632c = parcel.readInt();
        this.f2633d = parcel.readInt();
        this.f2634e = parcel.readInt();
        this.f2635f = parcel.readInt();
        this.f2636g = parcel.readInt();
        this.f2637h = parcel.readInt();
        this.f2638i = parcel.readInt();
        this.f2639j = parcel.readInt();
        this.f2640k = parcel.readInt();
        this.f2641l = parcel.createIntArray();
    }
}
