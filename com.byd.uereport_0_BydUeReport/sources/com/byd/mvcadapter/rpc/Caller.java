package com.byd.mvcadapter.rpc;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class Caller implements Parcelable {
    public static final Parcelable.Creator<Caller> CREATOR = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2787d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2788e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f2789f;

    public static class a implements Parcelable.Creator<Caller> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Caller createFromParcel(Parcel parcel) {
            Caller caller = new Caller(null);
            caller.g(parcel);
            return caller;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Caller[] newArray(int i8) {
            return new Caller[i8];
        }
    }

    public /* synthetic */ Caller(a aVar) {
        this();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int f() {
        return this.f2787d;
    }

    public void g(Parcel parcel) {
        this.f2787d = parcel.readInt();
        this.f2788e = parcel.readInt();
        this.f2789f = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i8) {
        parcel.writeInt(this.f2787d);
        parcel.writeInt(this.f2788e);
        parcel.writeString(this.f2789f);
    }

    public Caller(int i8, int i9, String str) {
        this.f2787d = i8;
        this.f2788e = i9;
        this.f2789f = str;
    }

    public Caller() {
    }
}
