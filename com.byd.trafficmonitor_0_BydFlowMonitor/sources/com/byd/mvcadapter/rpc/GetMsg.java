package com.byd.mvcadapter.rpc;

import android.os.Parcel;
import android.os.Parcelable;
import c.a.b.r.f;

/* JADX INFO: loaded from: classes.dex */
public class GetMsg extends f implements Parcelable {
    public static final Parcelable.Creator<GetMsg> CREATOR = new a();
    public String m;
    public boolean n;

    public static class a implements Parcelable.Creator<GetMsg> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public GetMsg createFromParcel(Parcel parcel) {
            GetMsg getMsg = new GetMsg(null);
            getMsg.x(parcel);
            return getMsg;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public GetMsg[] newArray(int i2) {
            return new GetMsg[i2];
        }
    }

    public /* synthetic */ GetMsg(a aVar) {
        this();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return "GetMsg[" + j().j() + "->" + q() + "]: " + this.m + ", isNew: " + this.n + ", id: " + m();
    }

    public String v() {
        return this.m;
    }

    public boolean w() {
        return this.n;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        u(parcel, i2);
        parcel.writeString(this.m);
        parcel.writeInt(this.n ? 1 : 0);
    }

    public void x(Parcel parcel) {
        s(parcel);
        this.m = parcel.readString();
        this.n = parcel.readInt() == 1;
    }

    public GetMsg() {
    }
}
