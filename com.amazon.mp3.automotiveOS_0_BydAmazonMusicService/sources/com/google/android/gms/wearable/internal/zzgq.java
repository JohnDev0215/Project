package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@ShowFirstParty
@SafeParcelable.Class(creator = "StorageInfoResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzgq extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzgq> CREATOR = new zzgr();

    @SafeParcelable.Field(id = 2)
    public final int zza;

    @SafeParcelable.Field(id = 3)
    public final long zzb;

    @SafeParcelable.Field(id = 4)
    public final List<zzga> zzc;

    @SafeParcelable.Constructor
    public zzgq(@SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) long j, @SafeParcelable.Param(id = 4) List<zzga> list) {
        this.zza = i;
        this.zzb = j;
        this.zzc = list;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zza);
        SafeParcelWriter.writeLong(parcel, 3, this.zzb);
        SafeParcelWriter.writeTypedList(parcel, 4, this.zzc, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
