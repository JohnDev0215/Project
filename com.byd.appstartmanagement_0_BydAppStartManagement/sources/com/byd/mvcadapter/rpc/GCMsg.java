package com.byd.mvcadapter.rpc;

import android.os.Parcel;
import android.os.Parcelable;
import b.a.c.u.i;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class GCMsg extends i implements Parcelable {
    public static final Parcelable.Creator<GCMsg> CREATOR = new a();
    public ArrayList<Long> m;

    public static class a implements Parcelable.Creator<GCMsg> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public GCMsg createFromParcel(Parcel parcel) {
            GCMsg gCMsg = new GCMsg(null);
            gCMsg.r(parcel);
            return gCMsg;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public GCMsg[] newArray(int i) {
            return new GCMsg[i];
        }
    }

    public /* synthetic */ GCMsg(a aVar) {
        this();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ArrayList<Long> q() {
        return this.m;
    }

    public void r(Parcel parcel) {
        l(parcel);
        this.m = parcel.readArrayList(GCMsg.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        p(parcel, i);
        parcel.writeList(this.m);
    }

    public GCMsg() {
    }
}
