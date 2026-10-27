package com.google.android.gms.internal.cast;

import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public interface zzu extends IInterface {
    int zze() throws RemoteException;

    void zzf(String str, @Nullable Bundle bundle) throws RemoteException;

    void zzg(String str, @Nullable Bundle bundle) throws RemoteException;

    void zzh(String str, @Nullable Bundle bundle) throws RemoteException;

    void zzi(String str, @Nullable Bundle bundle) throws RemoteException;

    void zzj(String str, String str2, @Nullable Bundle bundle) throws RemoteException;

    void zzk(String str, @Nullable Bundle bundle, int i) throws RemoteException;
}
