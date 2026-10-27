package com.byd.mvcadapter.rpc;

import android.os.Parcel;
import android.os.Parcelable;
import c.a.b.r.b;
import c.a.b.r.f;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class CallbackMsg extends f implements Parcelable {
    public static final Parcelable.Creator<CallbackMsg> CREATOR = new a();
    public String m;
    public String n;
    public boolean o;

    public static class a implements Parcelable.Creator<CallbackMsg> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public CallbackMsg createFromParcel(Parcel parcel) {
            CallbackMsg callbackMsg = new CallbackMsg(null);
            callbackMsg.x(parcel);
            return callbackMsg;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public CallbackMsg[] newArray(int i2) {
            return new CallbackMsg[i2];
        }
    }

    public /* synthetic */ CallbackMsg(a aVar) {
        this();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "CallbackMsg[" + j().j() + "->" + q() + "]: " + this.n + " " + this.m + "(), id: " + m();
    }

    public String v() {
        return this.n;
    }

    public boolean w() {
        return this.o;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        u(parcel, i2);
        parcel.writeString(this.m);
        parcel.writeString(this.n);
    }

    public void x(Parcel parcel) {
        s(parcel);
        this.m = parcel.readString();
        this.n = parcel.readString();
    }

    public void y(boolean z) {
        this.o = z;
    }

    public CallbackMsg() {
    }

    public CallbackMsg(b bVar, int i2, long j, Method method, Object[] objArr) {
        super(bVar, i2, j, method.getParameterTypes(), f.n(method), objArr);
        this.m = method.getName();
        this.n = method.getReturnType().getName();
    }
}
