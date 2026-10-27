package com.google.android.gms.internal.cast;

import android.app.PendingIntent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzcr extends zza {
    zzcr(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
    }

    public final void zze() throws RemoteException {
        zzd(3, zza());
    }

    public final void zzf(zzcq zzcqVar, int i) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzf(parcelZza, zzcqVar);
        parcelZza.writeInt(i);
        zzd(5, parcelZza);
    }

    public final void zzg(zzcq zzcqVar, zzct zzctVar, String str, String str2, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzf(parcelZza, zzcqVar);
        zzc.zzf(parcelZza, zzctVar);
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzc.zzd(parcelZza, bundle);
        zzd(7, parcelZza);
    }

    public final void zzh(zzcq zzcqVar, PendingIntent pendingIntent, String str, String str2, Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzf(parcelZza, zzcqVar);
        zzc.zzd(parcelZza, pendingIntent);
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzc.zzd(parcelZza, bundle);
        zzd(8, parcelZza);
    }

    public final void zzi(zzcq zzcqVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzf(parcelZza, zzcqVar);
        zzd(6, parcelZza);
    }
}
