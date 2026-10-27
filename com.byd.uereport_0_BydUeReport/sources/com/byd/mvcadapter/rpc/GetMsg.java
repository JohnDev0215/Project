package com.byd.mvcadapter.rpc;

import android.os.Parcel;
import android.os.Parcelable;
import u3.f;

/* JADX INFO: loaded from: classes.dex */
public class GetMsg extends f implements Parcelable {
    public static final Parcelable.Creator<GetMsg> CREATOR = new a();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public String f2791o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f2792p;

    public static class a implements Parcelable.Creator<GetMsg> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public GetMsg createFromParcel(Parcel parcel) {
            GetMsg getMsg = new GetMsg(null);
            getMsg.t(parcel);
            return getMsg;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public GetMsg[] newArray(int i8) {
            return new GetMsg[i8];
        }
    }

    public /* synthetic */ GetMsg(a aVar) {
        this();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String r() {
        return this.f2791o;
    }

    public boolean s() {
        return this.f2792p;
    }

    public void t(Parcel parcel) {
        o(parcel);
        this.f2791o = parcel.readString();
        this.f2792p = parcel.readInt() == 1;
    }

    public String toString() {
        return "GetMsg[" + f().f() + "->" + m() + "]: " + this.f2791o + ", isNew: " + this.f2792p + ", id: " + i();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        q(parcel, i8);
        parcel.writeString(this.f2791o);
        parcel.writeInt(this.f2792p ? 1 : 0);
    }

    public GetMsg() {
    }
}
