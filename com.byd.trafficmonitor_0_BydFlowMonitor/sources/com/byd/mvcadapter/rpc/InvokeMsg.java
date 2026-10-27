package com.byd.mvcadapter.rpc;

import android.os.Parcel;
import android.os.Parcelable;
import c.a.b.r.f;

/* JADX INFO: loaded from: classes.dex */
public class InvokeMsg extends f implements Parcelable {
    public static final Parcelable.Creator<InvokeMsg> CREATOR = new a();
    public String m;
    public String n;
    public String o;

    public static class a implements Parcelable.Creator<InvokeMsg> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public InvokeMsg createFromParcel(Parcel parcel) {
            InvokeMsg invokeMsg = new InvokeMsg(null);
            invokeMsg.y(parcel);
            return invokeMsg;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public InvokeMsg[] newArray(int i2) {
            return new InvokeMsg[i2];
        }
    }

    public /* synthetic */ InvokeMsg(a aVar) {
        this();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "InvokeMsg[" + j().j() + "->" + q() + "]: " + this.o + " " + this.m + "." + this.n + "(), id: " + m();
    }

    public String v() {
        return this.m;
    }

    public String w() {
        return this.n;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        u(parcel, i2);
        parcel.writeString(this.m);
        parcel.writeString(this.n);
        parcel.writeString(this.o);
    }

    public String x() {
        return this.o;
    }

    public void y(Parcel parcel) {
        s(parcel);
        this.m = parcel.readString();
        this.n = parcel.readString();
        this.o = parcel.readString();
    }

    public InvokeMsg() {
    }
}
