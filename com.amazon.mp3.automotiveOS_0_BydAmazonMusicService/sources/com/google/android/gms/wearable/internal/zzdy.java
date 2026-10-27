package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.ConnectionConfiguration;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "GetConfigResponseCreator")
@SafeParcelable.Reserved({1})
@Deprecated
public final class zzdy extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzdy> CREATOR = new zzdz();

    @SafeParcelable.Field(id = 2)
    public final int zza;

    @SafeParcelable.Field(id = 3)
    public final ConnectionConfiguration zzb;

    @SafeParcelable.Constructor
    public zzdy(@SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) ConnectionConfiguration connectionConfiguration) {
        this.zza = i;
        this.zzb = connectionConfiguration;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zza);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzb, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
