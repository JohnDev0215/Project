package com.google.android.gms.cast.framework;

import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzai extends com.google.android.gms.internal.cast.zza implements zzak {
    zzai(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.IReconnectionService");
    }

    @Override // com.google.android.gms.cast.framework.zzak
    public final int zze(Intent intent, int i, int i2) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzd(parcelZza, intent);
        parcelZza.writeInt(i);
        parcelZza.writeInt(i2);
        Parcel parcelZzb = zzb(2, parcelZza);
        int i3 = parcelZzb.readInt();
        parcelZzb.recycle();
        return i3;
    }

    @Override // com.google.android.gms.cast.framework.zzak
    public final IBinder zzf(Intent intent) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzd(parcelZza, intent);
        Parcel parcelZzb = zzb(3, parcelZza);
        IBinder strongBinder = parcelZzb.readStrongBinder();
        parcelZzb.recycle();
        return strongBinder;
    }

    @Override // com.google.android.gms.cast.framework.zzak
    public final void zzg() throws RemoteException {
        zzc(1, zza());
    }

    @Override // com.google.android.gms.cast.framework.zzak
    public final void zzh() throws RemoteException {
        zzc(4, zza());
    }
}
