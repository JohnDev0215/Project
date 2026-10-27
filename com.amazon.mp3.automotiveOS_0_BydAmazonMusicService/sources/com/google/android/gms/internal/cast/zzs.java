package com.google.android.gms.internal.cast;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public interface zzs extends IInterface {
    @Nullable
    Bundle zzb(String str) throws RemoteException;

    String zzc() throws RemoteException;

    void zzd(Bundle bundle, int i) throws RemoteException;

    void zze(Bundle bundle, zzu zzuVar) throws RemoteException;

    void zzf() throws RemoteException;

    void zzg(Bundle bundle) throws RemoteException;

    void zzh() throws RemoteException;

    void zzi(String str) throws RemoteException;

    void zzj(int i) throws RemoteException;

    boolean zzk() throws RemoteException;

    boolean zzl() throws RemoteException;

    boolean zzm(Bundle bundle, int i) throws RemoteException;
}
