package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.common.ConnectionResult;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzab extends com.google.android.gms.internal.cast.zza implements zzad {
    zzab(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.ICastSession");
    }

    @Override // com.google.android.gms.cast.framework.zzad
    public final void zze(boolean z, int i) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, z);
        parcelZza.writeInt(0);
        zzc(6, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzad
    public final void zzf(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzd(parcelZza, applicationMetadata);
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, z);
        zzc(4, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzad
    public final void zzg(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzc(5, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzad
    public final void zzh(Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzd(parcelZza, null);
        zzc(1, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzad
    public final void zzi(ConnectionResult connectionResult) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzd(parcelZza, connectionResult);
        zzc(3, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzad
    public final void zzj(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzc(2, parcelZza);
    }
}
