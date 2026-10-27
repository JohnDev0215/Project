package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "GetDataItemResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzee extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzee> CREATOR = new zzef();

    @SafeParcelable.Field(id = 2)
    public final int zza;

    @SafeParcelable.Field(id = 3)
    public final zzdd zzb;

    @SafeParcelable.Constructor
    public zzee(@SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) zzdd zzddVar) {
        this.zza = i;
        this.zzb = zzddVar;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zza);
        SafeParcelWriter.writeParcelable(parcel, 3, this.zzb, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
