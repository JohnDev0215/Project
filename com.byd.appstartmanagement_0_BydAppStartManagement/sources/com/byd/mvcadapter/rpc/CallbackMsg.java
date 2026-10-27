package com.byd.mvcadapter.rpc;

import android.os.Parcel;
import android.os.Parcelable;
import b.a.c.u.c;
import b.a.c.u.i;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class CallbackMsg extends i implements Parcelable {
    public static final Parcelable.Creator<CallbackMsg> CREATOR = new a();
    public String m;
    public String n;
    public boolean o;

    public static class a implements Parcelable.Creator<CallbackMsg> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public CallbackMsg createFromParcel(Parcel parcel) {
            CallbackMsg callbackMsg = new CallbackMsg(null);
            callbackMsg.t(parcel);
            return callbackMsg;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public CallbackMsg[] newArray(int i) {
            return new CallbackMsg[i];
        }
    }

    public /* synthetic */ CallbackMsg(a aVar) {
        this();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String q() {
        return this.m;
    }

    public String r() {
        return this.n;
    }

    public boolean s() {
        return this.o;
    }

    public void t(Parcel parcel) {
        l(parcel);
        this.m = parcel.readString();
        this.n = parcel.readString();
    }

    public String toString() {
        return "CallbackMsg[" + a().a() + "->" + j() + "]: " + this.n + " " + this.m + "(), id: " + d();
    }

    public void u(boolean z) {
        this.o = z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        p(parcel, i);
        parcel.writeString(this.m);
        parcel.writeString(this.n);
    }

    public CallbackMsg() {
    }

    public CallbackMsg(c cVar, int i, long j, Method method, Object[] objArr) {
        super(cVar, i, j, method.getParameterTypes(), i.f(method), objArr);
        this.m = method.getName();
        this.n = method.getReturnType().getName();
    }
}
