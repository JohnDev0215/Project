package com.google.android.gms.cast.framework;

import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public interface zzan extends IInterface {
    int zze() throws RemoteException;

    int zzf() throws RemoteException;

    IObjectWrapper zzg() throws RemoteException;

    String zzh() throws RemoteException;

    @Nullable
    String zzi() throws RemoteException;

    void zzj(int i) throws RemoteException;

    void zzk(int i) throws RemoteException;

    void zzl(int i) throws RemoteException;

    void zzm(boolean z) throws RemoteException;

    void zzn(String str) throws RemoteException;

    void zzo(int i) throws RemoteException;

    boolean zzp() throws RemoteException;

    boolean zzq() throws RemoteException;

    boolean zzr() throws RemoteException;

    boolean zzs() throws RemoteException;

    boolean zzt() throws RemoteException;

    boolean zzu() throws RemoteException;
}
