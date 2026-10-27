package com.google.android.gms.internal.cast;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzt extends zza implements zzu {
    zzt(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.internal.IMediaRouterCallback");
    }

    @Override // com.google.android.gms.internal.cast.zzu
    public final int zze() throws RemoteException {
        Parcel parcelZzb = zzb(7, zza());
        int i = parcelZzb.readInt();
        parcelZzb.recycle();
        return i;
    }

    @Override // com.google.android.gms.internal.cast.zzu
    public final void zzf(String str, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc.zzd(parcelZza, bundle);
        zzc(1, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzu
    public final void zzg(String str, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc.zzd(parcelZza, bundle);
        zzc(2, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzu
    public final void zzh(String str, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc.zzd(parcelZza, bundle);
        zzc(3, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzu
    public final void zzi(String str, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc.zzd(parcelZza, bundle);
        zzc(4, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzu
    public final void zzj(String str, String str2, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzc.zzd(parcelZza, bundle);
        zzc(8, parcelZza);
    }

    @Override // com.google.android.gms.internal.cast.zzu
    public final void zzk(String str, Bundle bundle, int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc.zzd(parcelZza, bundle);
        parcelZza.writeInt(i);
        zzc(6, parcelZza);
    }
}
