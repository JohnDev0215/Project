package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "GetCloudSyncOptInOutDoneCreator")
@SafeParcelable.Reserved({1})
public final class zzdr extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzdr> CREATOR = new zzdq();

    @SafeParcelable.Field(id = 2)
    public final int zza;

    @SafeParcelable.Field(id = 3)
    public final boolean zzb;

    @SafeParcelable.Constructor
    public zzdr(@SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) boolean z) {
        this.zza = i;
        this.zzb = z;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zza);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzb);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
