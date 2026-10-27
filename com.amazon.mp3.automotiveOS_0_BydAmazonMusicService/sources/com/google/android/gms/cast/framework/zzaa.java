package com.google.android.gms.cast.framework;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public interface zzaa extends IInterface {
    Bundle zze() throws RemoteException;

    zzah zzf() throws RemoteException;

    zzap zzg() throws RemoteException;

    void zzh(zzv zzvVar) throws RemoteException;

    void zzi(String str, Map map) throws RemoteException;
}
