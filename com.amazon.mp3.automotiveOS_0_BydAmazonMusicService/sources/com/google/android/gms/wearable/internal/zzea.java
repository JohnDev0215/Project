package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.ConnectionConfiguration;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "GetConfigsResponseCreator")
@SafeParcelable.Reserved({1})
public final class zzea extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzea> CREATOR = new zzeb();

    @SafeParcelable.Field(id = 2)
    public final int zza;

    @SafeParcelable.Field(id = 3)
    public final ConnectionConfiguration[] zzb;

    @SafeParcelable.Constructor
    public zzea(@SafeParcelable.Param(id = 2) int i, @SafeParcelable.Param(id = 3) ConnectionConfiguration[] connectionConfigurationArr) {
        this.zza = i;
        this.zzb = connectionConfigurationArr;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 2, this.zza);
        SafeParcelWriter.writeTypedArray(parcel, 3, this.zzb, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }
}
