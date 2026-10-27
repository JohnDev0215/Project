package com.byd.amazon.music.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class Result implements Parcelable {
    public static final Parcelable.Creator<Result> CREATOR = new a();
    public int a;

    public class a implements Parcelable.Creator<Result> {
        @Override // android.os.Parcelable.Creator
        public final Result createFromParcel(Parcel parcel) {
            return new Result(parcel.readInt());
        }

        @Override // android.os.Parcelable.Creator
        public final Result[] newArray(int i) {
            return new Result[i];
        }
    }

    public Result() {
    }

    public static Result a() {
        return new Result(-1);
    }

    public static Result b() {
        return new Result(0);
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.a);
    }

    public Result(int i) {
        this.a = i;
    }
}
