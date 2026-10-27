package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import javax.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@SafeParcelable.Class(creator = "AddListenerRequestCreator")
@SafeParcelable.Reserved({1})
public final class zzd extends AbstractSafeParcelable {
    public static final Parcelable.Creator<zzd> CREATOR = new zze();

    @SafeParcelable.Field(getter = "getListenerAsBinder", id = 2, type = "android.os.IBinder")
    public final zzet zza;

    @SafeParcelable.Field(id = 3)
    public final IntentFilter[] zzb;

    @Nullable
    @SafeParcelable.Field(id = 4)
    public final String zzc;

    @Nullable
    @SafeParcelable.Field(id = 5)
    public final String zzd;

    @SafeParcelable.Constructor
    zzd(@SafeParcelable.Param(id = 2) IBinder iBinder, @SafeParcelable.Param(id = 3) IntentFilter[] intentFilterArr, @SafeParcelable.Param(id = 4) @Nullable String str, @SafeParcelable.Param(id = 5) @Nullable String str2) {
        if (iBinder != null) {
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.wearable.internal.IWearableListener");
            this.zza = iInterfaceQueryLocalInterface instanceof zzet ? (zzet) iInterfaceQueryLocalInterface : new zzer(iBinder);
        } else {
            this.zza = null;
        }
        this.zzb = intentFilterArr;
        this.zzc = str;
        this.zzd = str2;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        int iBeginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        zzet zzetVar = this.zza;
        SafeParcelWriter.writeIBinder(parcel, 2, zzetVar == null ? null : zzetVar.asBinder(), false);
        SafeParcelWriter.writeTypedArray(parcel, 3, this.zzb, i, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzc, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzd, false);
        SafeParcelWriter.finishObjectHeader(parcel, iBeginObjectHeader);
    }

    public zzd(zzia zziaVar) {
        this.zza = zziaVar;
        this.zzb = zziaVar.zzr();
        this.zzc = zziaVar.zzs();
        this.zzd = null;
    }
}
