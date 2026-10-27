package com.byd.mvcadapter;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class Reply implements Parcelable {
    public static final Parcelable.Creator<Reply> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4360b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f4361c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f4362d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Object f4363e = null;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ComplexData f4364f = new ComplexData();

    public static class a implements Parcelable.Creator<Reply> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Reply createFromParcel(Parcel parcel) {
            Reply reply = new Reply();
            reply.n(parcel);
            return reply;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Reply[] newArray(int i2) {
            return new Reply[i2];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String j() {
        return this.f4361c;
    }

    public int k() {
        return this.f4360b;
    }

    public Object l() {
        return m(null);
    }

    public Object m(ClassLoader classLoader) {
        Object[] objArrM = this.f4364f.m(classLoader);
        if (objArrM.length != 1 || (objArrM[0] instanceof NullResult)) {
            return null;
        }
        return objArrM[0];
    }

    public void n(Parcel parcel) {
        this.f4360b = parcel.readInt();
        this.f4361c = parcel.readString();
        this.f4362d = parcel.readInt() == 1;
        this.f4364f = (ComplexData) parcel.readParcelable(ComplexData.k());
    }

    public void o(String str) {
        this.f4361c = str;
    }

    public void p(int i2) {
        this.f4360b = i2;
    }

    public void q(Object obj) {
        if (obj == null) {
            obj = new NullResult();
        }
        this.f4363e = obj;
        this.f4364f = new ComplexData(new Object[]{obj});
    }

    public void r(boolean z) {
        this.f4362d = z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f4360b);
        parcel.writeString(this.f4361c);
        parcel.writeInt(this.f4362d ? 1 : 0);
        parcel.writeParcelable(this.f4364f, 0);
    }
}
