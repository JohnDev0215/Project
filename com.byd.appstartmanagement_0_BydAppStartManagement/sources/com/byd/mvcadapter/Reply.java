package com.byd.mvcadapter;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class Reply implements Parcelable {
    public static final Parcelable.Creator<Reply> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f632b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f633c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f634d = true;
    public Object e = null;
    public ComplexData f = new ComplexData();

    public static class a implements Parcelable.Creator<Reply> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Reply createFromParcel(Parcel parcel) {
            Reply reply = new Reply();
            reply.f(parcel);
            return reply;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Reply[] newArray(int i) {
            return new Reply[i];
        }
    }

    public String a() {
        return this.f633c;
    }

    public int b() {
        return this.f632b;
    }

    public Object c() {
        return d(null);
    }

    public Object d(ClassLoader classLoader) {
        Object[] objArrD = this.f.d(classLoader);
        if (objArrD.length != 1 || (objArrD[0] instanceof NullResult)) {
            return null;
        }
        return objArrD[0];
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public boolean e() {
        return this.f634d;
    }

    public void f(Parcel parcel) {
        this.f632b = parcel.readInt();
        this.f633c = parcel.readString();
        this.f634d = parcel.readInt() == 1;
        this.f = (ComplexData) parcel.readParcelable(ComplexData.b());
    }

    public void g(String str) {
        this.f633c = str;
    }

    public void h(int i) {
        this.f632b = i;
    }

    public void i(Object obj) {
        if (obj == null) {
            obj = new NullResult();
        }
        this.e = obj;
        this.f = new ComplexData(new Object[]{obj});
    }

    public void j(boolean z) {
        this.f634d = z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f632b);
        parcel.writeString(this.f633c);
        parcel.writeInt(this.f634d ? 1 : 0);
        parcel.writeParcelable(this.f, 0);
    }
}
