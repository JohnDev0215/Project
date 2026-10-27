package com.byd.mvcadapter.rpc;

import android.os.Parcel;
import android.os.Parcelable;
import java.lang.reflect.Method;
import u3.b;
import u3.f;

/* JADX INFO: loaded from: classes.dex */
public class CallbackMsg extends f implements Parcelable {
    public static final Parcelable.Creator<CallbackMsg> CREATOR = new a();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f2784o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f2785p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public boolean f2786q;

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
        public CallbackMsg[] newArray(int i8) {
            return new CallbackMsg[i8];
        }
    }

    public /* synthetic */ CallbackMsg(a aVar) {
        this();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String r() {
        return this.f2785p;
    }

    public boolean s() {
        return this.f2786q;
    }

    public void t(Parcel parcel) {
        o(parcel);
        this.f2784o = parcel.readString();
        this.f2785p = parcel.readString();
    }

    public String toString() {
        return "CallbackMsg[" + f().f() + "->" + m() + "]: " + this.f2785p + " " + this.f2784o + "(), id: " + i();
    }

    public void u(boolean z7) {
        this.f2786q = z7;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        q(parcel, i8);
        parcel.writeString(this.f2784o);
        parcel.writeString(this.f2785p);
    }

    public CallbackMsg() {
    }

    public CallbackMsg(b bVar, int i8, long j8, Method method, Object[] objArr) {
        super(bVar, i8, j8, method.getParameterTypes(), f.j(method), objArr);
        this.f2784o = method.getName();
        this.f2785p = method.getReturnType().getName();
    }
}
