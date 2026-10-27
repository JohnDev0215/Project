package com.google.android.gms.internal.cast;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public interface zzq extends IInterface {
    com.google.android.gms.cast.framework.zzaa zze(IObjectWrapper iObjectWrapper, CastOptions castOptions, zzs zzsVar, Map map) throws RemoteException;

    com.google.android.gms.cast.framework.zzad zzf(CastOptions castOptions, IObjectWrapper iObjectWrapper, com.google.android.gms.cast.framework.zzx zzxVar) throws RemoteException;

    com.google.android.gms.cast.framework.zzak zzg(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException;

    com.google.android.gms.cast.framework.zzan zzh(String str, String str2, com.google.android.gms.cast.framework.zzav zzavVar) throws RemoteException;

    com.google.android.gms.cast.framework.media.internal.zzi zzi(IObjectWrapper iObjectWrapper, com.google.android.gms.cast.framework.media.internal.zzk zzkVar, int i, int i2, boolean z, long j, int i3, int i4, int i5) throws RemoteException;
}
