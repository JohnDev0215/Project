package com.spotify.connectivity.sessionstate;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public abstract class OptimizedParcelableTypeAdapter<T extends Parcelable> {
    private final Parcelable.Creator<? extends T> mCreator;

    public OptimizedParcelableTypeAdapter(Parcelable.Creator<? extends T> creator) {
        this.mCreator = creator;
    }

    /* JADX INFO: renamed from: fromParcel, reason: merged with bridge method [inline-methods] */
    public T m193fromParcel(Parcel parcel) {
        return (T) parcel.readTypedObject(this.mCreator);
    }

    public void toParcel(T t, Parcel parcel) {
        parcel.writeTypedObject(t, 0);
    }
}
