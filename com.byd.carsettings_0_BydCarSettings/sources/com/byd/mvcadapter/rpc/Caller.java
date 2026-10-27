package com.byd.mvcadapter.rpc;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class Caller implements Parcelable {
    public static final Parcelable.Creator<Caller> CREATOR = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f3692a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f3693b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public String f3694c;

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
        return this.f3692a;
    }

    public void b(Parcel parcel) {
        this.f3692a = parcel.readInt();
        this.f3693b = parcel.readInt();
        this.f3694c = parcel.readString();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f3692a);
        parcel.writeInt(this.f3693b);
        parcel.writeString(this.f3694c);
    }

    public Caller(int i, int i2, String str) {
        this.f3692a = i;
        this.f3693b = i2;
        this.f3694c = str;
    }

    public Caller() {
    }
}
