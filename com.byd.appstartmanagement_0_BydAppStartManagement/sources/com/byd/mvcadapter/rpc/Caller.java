package com.byd.mvcadapter.rpc;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class Caller implements Parcelable {
    public static final Parcelable.Creator<Caller> CREATOR = new a();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f639b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f640c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f641d;

    public static class a implements Parcelable.Creator<Caller> {
        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Caller createFromParcel(Parcel parcel) {
            Caller caller = new Caller(null);
            caller.b(parcel);
            return caller;
        }

        @Override // android.os.Parcelable.Creator
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public Caller[] newArray(int i) {
            return new Caller[i];
        }
    }

    public /* synthetic */ Caller(a aVar) {
        this();
    }

    public int a() {
        return this.f639b;
    }

    public void b(Parcel parcel) {
        this.f639b = parcel.readInt();
        this.f640c = parcel.readInt();
        this.f641d = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f639b);
        parcel.writeInt(this.f640c);
        parcel.writeString(this.f641d);
    }

    public Caller(int i, int i2, String str) {
        this.f639b = i;
        this.f640c = i2;
        this.f641d = str;
    }

    public Caller() {
    }
}
