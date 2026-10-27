package com.byd.mvcadapter;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class Reply implements Parcelable {
    public static final Parcelable.Creator<Reply> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3683a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f3684b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f3685c = true;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Object f3686d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ComplexData f3687e = new ComplexData();

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
        return this.f3684b;
    }

    public int b() {
        return this.f3683a;
    }

    public Object c() {
        return d(null);
    }

    public Object d(ClassLoader classLoader) {
        Object[] objArrD = this.f3687e.d(classLoader);
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
        return this.f3685c;
    }

    public void f(Parcel parcel) {
        this.f3683a = parcel.readInt();
        this.f3684b = parcel.readString();
        this.f3685c = parcel.readInt() == 1;
        this.f3687e = (ComplexData) parcel.readParcelable(ComplexData.b());
    }

    public void g(String str) {
        this.f3684b = str;
    }

    public void h(int i) {
        this.f3683a = i;
    }

    public void i(Object obj) {
        if (obj == null) {
            obj = new NullResult();
        }
        this.f3686d = obj;
        this.f3687e = new ComplexData(new Object[]{obj});
    }

    public void j(boolean z) {
        this.f3685c = z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f3683a);
        parcel.writeString(this.f3684b);
        parcel.writeInt(this.f3685c ? 1 : 0);
        parcel.writeParcelable(this.f3687e, 0);
    }
}
