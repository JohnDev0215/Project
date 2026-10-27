package com.byd.mvcadapter.rpc;

import android.os.Parcel;
import android.os.Parcelable;
import c.a.b.r.f;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class GCMsg extends f implements Parcelable {
    public static final Parcelable.Creator<GCMsg> CREATOR = new a();
    public ArrayList<Long> m;

    public static class a implements Parcelable.Creator<GCMsg> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public GCMsg createFromParcel(Parcel parcel) {
            GCMsg gCMsg = new GCMsg(null);
            gCMsg.w(parcel);
            return gCMsg;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public GCMsg[] newArray(int i2) {
            return new GCMsg[i2];
        }
    }

    public /* synthetic */ GCMsg(a aVar) {
        this();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ArrayList<Long> v() {
        return this.m;
    }

    public void w(Parcel parcel) {
        s(parcel);
        this.m = parcel.readArrayList(GCMsg.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        u(parcel, i2);
        parcel.writeList(this.m);
    }

    public GCMsg() {
    }
}
