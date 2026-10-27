package com.byd.mvcadapter.rpc;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public class Caller implements Parcelable {
    public static final Parcelable.Creator<Caller> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f4369b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f4370c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f4371d;

    public static class a implements Parcelable.Creator<Caller> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Caller createFromParcel(Parcel parcel) {
            Caller caller = new Caller(null);
            caller.k(parcel);
            return caller;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Caller[] newArray(int i2) {
            return new Caller[i2];
        }
    }

    public /* synthetic */ Caller(a aVar) {
        this();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int j() {
        return this.f4369b;
    }

    public void k(Parcel parcel) {
        this.f4369b = parcel.readInt();
        this.f4370c = parcel.readInt();
        this.f4371d = parcel.readString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i2) {
        parcel.writeInt(this.f4369b);
        parcel.writeInt(this.f4370c);
        parcel.writeString(this.f4371d);
    }

    public Caller(int i2, int i3, String str) {
        this.f4369b = i2;
        this.f4370c = i3;
        this.f4371d = str;
    }

    public Caller() {
    }
}
