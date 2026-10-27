package com.byd.mvcadapter.rpc;

import android.os.Parcel;
import android.os.Parcelable;
import b.b.l.u.c;
import b.b.l.u.i;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class CallbackMsg extends i implements Parcelable {
    public static final Parcelable.Creator<CallbackMsg> CREATOR = new a();
    public String l;
    public String m;
    public boolean n;

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
        return this.l;
    }

    public String r() {
        return this.m;
    }

    public boolean s() {
        return this.n;
    }

    public void t(Parcel parcel) {
        l(parcel);
        this.l = parcel.readString();
        this.m = parcel.readString();
    }

    public String toString() {
        return "CallbackMsg[" + a().a() + "->" + j() + "]: " + this.m + " " + this.l + "(), id: " + d();
    }

    public void u(boolean z) {
        this.n = z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        p(parcel, i);
        parcel.writeString(this.l);
        parcel.writeString(this.m);
    }

    public CallbackMsg() {
    }

    public CallbackMsg(c cVar, int i, long j, Method method, Object[] objArr) {
        super(cVar, i, j, method.getParameterTypes(), i.f(method), objArr);
        this.l = method.getName();
        this.m = method.getReturnType().getName();
    }
}
