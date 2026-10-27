package com.byd.mvcadapter.rpc;

import android.os.Parcel;
import android.os.Parcelable;
import b.b.l.u.c;
import b.b.l.u.i;

/* JADX INFO: loaded from: classes2.dex */
public class GetMsg extends i implements Parcelable {
    public static final Parcelable.Creator<GetMsg> CREATOR = new a();
    public String l;
    public boolean m;

    public static class a implements Parcelable.Creator<GetMsg> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public GetMsg createFromParcel(Parcel parcel) {
            GetMsg getMsg = new GetMsg(null);
            getMsg.s(parcel);
            return getMsg;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public GetMsg[] newArray(int i) {
            return new GetMsg[i];
        }
    }

    public /* synthetic */ GetMsg(a aVar) {
        this();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String q() {
        return this.l;
    }

    public boolean r() {
        return this.m;
    }

    public void s(Parcel parcel) {
        l(parcel);
        this.l = parcel.readString();
        this.m = parcel.readInt() == 1;
    }

    public String toString() {
        return "GetMsg[" + a().a() + "->" + j() + "]: " + this.l + ", isNew: " + this.m + ", id: " + d();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        p(parcel, i);
        parcel.writeString(this.l);
        parcel.writeInt(this.m ? 1 : 0);
    }

    public GetMsg() {
    }

    public GetMsg(c cVar, int i, String str, boolean z, Class<?>[] clsArr, Object[] objArr) {
        super(cVar, i, -1L, clsArr, i.e(objArr.length), objArr);
        this.l = str;
        this.m = z;
    }
}
