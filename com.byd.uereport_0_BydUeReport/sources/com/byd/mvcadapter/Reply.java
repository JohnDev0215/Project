package com.byd.mvcadapter;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class Reply implements Parcelable {
    public static final Parcelable.Creator<Reply> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2775d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f2776e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f2777f = true;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public Object f2778g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ComplexData f2779h = new ComplexData();

    public static class a implements Parcelable.Creator<Reply> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Reply createFromParcel(Parcel parcel) {
            Reply reply = new Reply();
            reply.j(parcel);
            return reply;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Reply[] newArray(int i8) {
            return new Reply[i8];
        }
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String f() {
        return this.f2776e;
    }

    public int g() {
        return this.f2775d;
    }

    public Object h() {
        return i(null);
    }

    public Object i(ClassLoader classLoader) {
        Object[] objArrI = this.f2779h.i(classLoader);
        if (objArrI.length != 1 || (objArrI[0] instanceof NullResult)) {
            return null;
        }
        return objArrI[0];
    }

    public void j(Parcel parcel) {
        this.f2775d = parcel.readInt();
        this.f2776e = parcel.readString();
        this.f2777f = parcel.readInt() == 1;
        this.f2779h = (ComplexData) parcel.readParcelable(ComplexData.g());
    }

    public void k(String str) {
        this.f2776e = str;
    }

    public void l(int i8) {
        this.f2775d = i8;
    }

    public void m(Object obj) {
        if (obj == null) {
            obj = new NullResult();
        }
        this.f2778g = obj;
        this.f2779h = new ComplexData(new Object[]{obj});
    }

    public void n(boolean z7) {
        this.f2777f = z7;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeInt(this.f2775d);
        parcel.writeString(this.f2776e);
        parcel.writeInt(this.f2777f ? 1 : 0);
        parcel.writeParcelable(this.f2779h, 0);
    }
}
