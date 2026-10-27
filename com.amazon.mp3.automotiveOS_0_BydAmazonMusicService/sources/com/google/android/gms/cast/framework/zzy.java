package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzy extends com.google.android.gms.internal.cast.zza implements zzaa {
    zzy(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.ICastContext");
    }

    @Override // com.google.android.gms.cast.framework.zzaa
    public final Bundle zze() throws RemoteException {
        Parcel parcelZzb = zzb(1, zza());
        Bundle bundle = (Bundle) com.google.android.gms.internal.cast.zzc.zza(parcelZzb, Bundle.CREATOR);
        parcelZzb.recycle();
        return bundle;
    }

    @Override // com.google.android.gms.cast.framework.zzaa
    public final zzah zzf() throws RemoteException {
        zzah zzagVar;
        Parcel parcelZzb = zzb(6, zza());
        IBinder strongBinder = parcelZzb.readStrongBinder();
        if (strongBinder == null) {
            zzagVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.cast.framework.IDiscoveryManager");
            zzagVar = iInterfaceQueryLocalInterface instanceof zzah ? (zzah) iInterfaceQueryLocalInterface : new zzag(strongBinder);
        }
        parcelZzb.recycle();
        return zzagVar;
    }

    @Override // com.google.android.gms.cast.framework.zzaa
    public final zzap zzg() throws RemoteException {
        zzap zzaoVar;
        Parcel parcelZzb = zzb(5, zza());
        IBinder strongBinder = parcelZzb.readStrongBinder();
        if (strongBinder == null) {
            zzaoVar = null;
        } else {
            IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.cast.framework.ISessionManager");
            zzaoVar = iInterfaceQueryLocalInterface instanceof zzap ? (zzap) iInterfaceQueryLocalInterface : new zzao(strongBinder);
        }
        parcelZzb.recycle();
        return zzaoVar;
    }

    @Override // com.google.android.gms.cast.framework.zzaa
    public final void zzh(zzv zzvVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzf(parcelZza, zzvVar);
        zzc(3, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzaa
    public final void zzi(String str, Map map) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeMap(map);
        zzc(11, parcelZza);
    }
}
