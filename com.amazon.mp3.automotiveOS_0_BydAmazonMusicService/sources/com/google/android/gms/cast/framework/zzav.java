package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public interface zzav extends IInterface {
    long zzb() throws RemoteException;

    IObjectWrapper zzc() throws RemoteException;

    void zzd(boolean z) throws RemoteException;

    void zze(Bundle bundle) throws RemoteException;

    void zzf(@Nullable Bundle bundle) throws RemoteException;

    void zzg(Bundle bundle) throws RemoteException;

    void zzh(Bundle bundle) throws RemoteException;

    void zzi(Bundle bundle) throws RemoteException;
}
