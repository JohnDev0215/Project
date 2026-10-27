package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzao extends com.google.android.gms.internal.cast.zza implements zzap {
    zzao(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.ISessionManager");
    }

    @Override // com.google.android.gms.cast.framework.zzap
    public final int zze() throws RemoteException {
        Parcel parcelZzb = zzb(8, zza());
        int i = parcelZzb.readInt();
        parcelZzb.recycle();
        return i;
    }

    @Override // com.google.android.gms.cast.framework.zzap
    public final IObjectWrapper zzf() throws RemoteException {
        Parcel parcelZzb = zzb(1, zza());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZzb.readStrongBinder());
        parcelZzb.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.cast.framework.zzap
    public final IObjectWrapper zzg() throws RemoteException {
        Parcel parcelZzb = zzb(7, zza());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZzb.readStrongBinder());
        parcelZzb.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.cast.framework.zzap
    public final void zzh(zzaf zzafVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzf(parcelZza, zzafVar);
        zzc(4, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzap
    public final void zzi(zzar zzarVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzf(parcelZza, zzarVar);
        zzc(2, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzap
    public final void zzj(boolean z, boolean z2) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, true);
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, z2);
        zzc(6, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzap
    public final void zzk(zzaf zzafVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzf(parcelZza, zzafVar);
        zzc(5, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzap
    public final void zzl(zzar zzarVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzf(parcelZza, zzarVar);
        zzc(3, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzap
    public final void zzm(Bundle bundle) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzd(parcelZza, bundle);
        zzc(9, parcelZza);
    }
}
