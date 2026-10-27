package com.google.android.gms.cast.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public interface zzag extends IInterface {
    void zzb(ApplicationMetadata applicationMetadata, String str, String str2, boolean z) throws RemoteException;

    void zzc(int i) throws RemoteException;

    void zzd(int i) throws RemoteException;

    void zze(int i) throws RemoteException;

    void zzf(zza zzaVar) throws RemoteException;

    void zzg(int i) throws RemoteException;

    void zzh(String str, byte[] bArr) throws RemoteException;

    void zzi(int i) throws RemoteException;

    void zzj(zzy zzyVar) throws RemoteException;

    void zzk(int i) throws RemoteException;

    void zzl(String str, long j) throws RemoteException;

    void zzm(String str, long j, int i) throws RemoteException;

    void zzn(String str, double d, boolean z) throws RemoteException;

    void zzo(int i) throws RemoteException;

    void zzp(String str, String str2) throws RemoteException;
}
