package com.byd.mvcadapter.rpc;

import android.os.Parcel;
import android.os.Parcelable;
import b.b.l.u.c;
import b.b.l.u.i;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes2.dex */
public class InvokeMsg extends i implements Parcelable {
    public static final Parcelable.Creator<InvokeMsg> CREATOR = new a();
    public String l;
    public String m;
    public String n;
    public boolean o;

    public static class a implements Parcelable.Creator<InvokeMsg> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public InvokeMsg createFromParcel(Parcel parcel) {
            InvokeMsg invokeMsg = new InvokeMsg(null);
            invokeMsg.u(parcel);
            return invokeMsg;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public InvokeMsg[] newArray(int i) {
            return new InvokeMsg[i];
        }
    }

    public /* synthetic */ InvokeMsg(a aVar) {
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

    public String s() {
        return this.n;
    }

    public boolean t() {
        return this.o;
    }

    public String toString() {
        return "InvokeMsg[" + a().a() + "->" + j() + "]: " + this.n + " " + this.l + "." + this.m + "(), id: " + d();
    }

    public void u(Parcel parcel) {
        l(parcel);
        this.l = parcel.readString();
        this.m = parcel.readString();
        this.n = parcel.readString();
    }

    public void v(boolean z) {
        this.o = z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        p(parcel, i);
        parcel.writeString(this.l);
        parcel.writeString(this.m);
        parcel.writeString(this.n);
    }

    public InvokeMsg() {
    }

    public InvokeMsg(c cVar, int i, long j, String str, Method method, Object[] objArr) {
        super(cVar, i, j, method.getParameterTypes(), i.f(method), objArr);
        this.l = str;
        this.m = method.getName();
        this.n = method.getReturnType().getName();
    }
}
