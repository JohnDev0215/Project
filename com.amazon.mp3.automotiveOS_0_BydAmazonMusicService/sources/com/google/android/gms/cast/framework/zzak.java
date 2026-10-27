package com.google.android.gms.cast.framework;

import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public interface zzak extends IInterface {
    int zze(Intent intent, int i, int i2) throws RemoteException;

    @Nullable
    IBinder zzf(Intent intent) throws RemoteException;

    void zzg() throws RemoteException;

    void zzh() throws RemoteException;
}
