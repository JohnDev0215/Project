package com.byd.mvcadapter.rpc;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import u3.f;

/* JADX INFO: loaded from: classes.dex */
public class GCMsg extends f implements Parcelable {
    public static final Parcelable.Creator<GCMsg> CREATOR = new a();

    /* JADX INFO: renamed from: o, reason: collision with root package name */
    public ArrayList<Long> f2790o;

    public static class a implements Parcelable.Creator<GCMsg> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public GCMsg createFromParcel(Parcel parcel) {
            GCMsg gCMsg = new GCMsg(null);
            gCMsg.s(parcel);
            return gCMsg;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public GCMsg[] newArray(int i8) {
            return new GCMsg[i8];
        }
    }

    public /* synthetic */ GCMsg(a aVar) {
        this();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ArrayList<Long> r() {
        return this.f2790o;
    }

    public void s(Parcel parcel) {
        o(parcel);
        this.f2790o = parcel.readArrayList(GCMsg.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        q(parcel, i8);
        parcel.writeList(this.f2790o);
    }

    public GCMsg() {
    }
}
