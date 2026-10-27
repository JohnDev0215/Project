package com.byd.vrdownload.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class Result<T> implements Parcelable {
    public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() { // from class: com.byd.vrdownload.model.Result.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Result createFromParcel(Parcel parcel) {
            return new Result(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public Result[] newArray(int i) {
            return new Result[i];
        }
    };
    Object value;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Result(Object obj) {
        this.value = obj;
    }

    protected Result(Parcel parcel) {
        this.value = parcel.readValue(this.value.getClass().getClassLoader());
    }

    public static <T> Result<T> success(T t) {
        return new Result<>(t);
    }

    public static <T> Result<T> fail(Exception exc) {
        return new Result<>(new Failure(exc));
    }

    public boolean isSuccess() {
        return !(this.value instanceof Failure);
    }

    public T getOrNull() {
        if (isSuccess()) {
            return (T) this.value;
        }
        return null;
    }

    public Exception exceptionOrNull() {
        if (isSuccess()) {
            return null;
        }
        return ((Failure) this.value).getException();
    }

    public Exception getExceptionMessage() {
        return ((Failure) this.value).getException();
    }

    public String toString() {
        return "Result{value=" + this.value.toString() + '}';
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.value);
    }
}
