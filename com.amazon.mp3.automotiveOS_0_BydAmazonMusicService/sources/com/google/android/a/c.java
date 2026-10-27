package com.google.android.a;

import android.os.Parcel;
import android.os.Parcelable;

/* JADX INFO: compiled from: Codecs.java */
/* JADX INFO: loaded from: classes6.dex */
public final class c {
    static {
        c.class.getClassLoader();
    }

    private c() {
    }

    public static <T extends Parcelable> T a(Parcel parcel, Parcelable.Creator<T> creator) {
        if (parcel.readInt() == 0) {
            return null;
        }
        return creator.createFromParcel(parcel);
    }

    public static void a(Parcel parcel, Parcelable parcelable) {
        parcel.writeInt(1);
        parcelable.writeToParcel(parcel, 0);
    }
}
