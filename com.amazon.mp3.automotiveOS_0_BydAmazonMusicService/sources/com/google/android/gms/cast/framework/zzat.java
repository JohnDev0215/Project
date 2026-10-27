package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public interface zzat extends IInterface {
    @Nullable
    IObjectWrapper zzb(String str) throws RemoteException;

    String zzc() throws RemoteException;

    boolean zzd() throws RemoteException;
}
