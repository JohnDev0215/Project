package com.android.networkstack.protobuf.nano.android;

import android.os.Parcel;
import android.os.Parcelable;
import com.android.networkstack.protobuf.nano.MessageNano;

/* JADX INFO: loaded from: classes.dex */
public final class ParcelableMessageNanoCreator<T extends MessageNano> implements Parcelable.Creator<T> {
    static <T extends MessageNano> void writeToParcel(Class<T> cls, MessageNano messageNano, Parcel parcel) {
        parcel.writeString(cls.getName());
        parcel.writeByteArray(MessageNano.toByteArray(messageNano));
    }
}
