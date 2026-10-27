package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "ChannelReceiveFileResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzbo extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzbo> CREATOR = new zzbp();

    @SafeParcelable.Field(id = 2)
    public final int zza;

    @SafeParcelable.Constructor
    public zzbo(@SafeParcelable.Param(id = 2) int i) {
        this.zza = i;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zza);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
