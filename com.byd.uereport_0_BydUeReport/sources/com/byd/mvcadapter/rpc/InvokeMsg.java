package com.byd.mvcadapter.rpc;

import android.os.Parcel;
import android.os.Parcelable;
import u3.f;

/* JADX INFO: loaded from: classes.dex */
public class InvokeMsg extends f implements Parcelable {
    public static final Parcelable.Creator<InvokeMsg> CREATOR = new a();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f2793o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f2794p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f2795q;

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
        public InvokeMsg[] newArray(int i8) {
            return new InvokeMsg[i8];
        }
    }

    public /* synthetic */ InvokeMsg(a aVar) {
        this();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String r() {
        return this.f2793o;
    }

    public String s() {
        return this.f2794p;
    }

    public String t() {
        return this.f2795q;
    }

    public String toString() {
        return "InvokeMsg[" + f().f() + "->" + m() + "]: " + this.f2795q + " " + this.f2793o + "." + this.f2794p + "(), id: " + i();
    }

    public void u(Parcel parcel) {
        o(parcel);
        this.f2793o = parcel.readString();
        this.f2794p = parcel.readString();
        this.f2795q = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        q(parcel, i8);
        parcel.writeString(this.f2793o);
        parcel.writeString(this.f2794p);
        parcel.writeString(this.f2795q);
    }

    public InvokeMsg() {
    }
}
