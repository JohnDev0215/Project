package com.byd.vrdownload.model;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes2.dex */
public class AidlResult<T extends Parcelable> implements Parcelable {
    public static final Parcelable.Creator<AidlResult> CREATOR = new Parcelable.Creator<AidlResult>() { // from class: com.byd.vrdownload.model.AidlResult.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AidlResult createFromParcel(Parcel parcel) {
            return new AidlResult(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public AidlResult[] newArray(int i) {
            return new AidlResult[i];
        }
    };
    Class classType;
    Object value;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AidlResult(Object obj, Class cls) {
        this.value = obj;
        this.classType = cls;
    }

    public Object getValue() {
        return this.value;
    }

    public void setValue(Object obj) {
        this.value = obj;
    }

    protected AidlResult(Parcel parcel) {
        Class cls = (Class) parcel.readSerializable();
        this.classType = cls;
        this.value = parcel.readValue(cls.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeSerializable(this.classType);
        parcel.writeValue(this.value);
    }

    public static <T extends Parcelable> AidlResult<T> success(T t, Class cls) {
        return new AidlResult<>(t, cls);
    }

    public static <T extends Parcelable> AidlResult<T> fail(Exception exc) {
        return new AidlResult<>(new Failure(exc), Failure.class);
    }

    public boolean isSuccess() {
        return !(this.value instanceof Failure);
    }
}
