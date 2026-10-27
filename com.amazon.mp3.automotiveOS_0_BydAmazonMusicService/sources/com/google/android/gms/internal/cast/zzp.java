package com.google.android.gms.internal.cast;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.framework.CastOptions;
import com.google.android.gms.dynamic.IObjectWrapper;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzp extends zza implements zzq {
    zzp(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.internal.ICastDynamiteModule");
    }

    @Override // com.google.android.gms.internal.cast.zzq
    public final com.google.android.gms.cast.framework.zzaa zze(IObjectWrapper iObjectWrapper, CastOptions castOptions, zzs zzsVar, Map map) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzf(parcelZza, iObjectWrapper);
        zzc.zzd(parcelZza, castOptions);
        zzc.zzf(parcelZza, zzsVar);
        parcelZza.writeMap(map);
        Parcel parcelZzb = zzb(1, parcelZza);
        com.google.android.gms.cast.framework.zzaa zzaaVarZzb = com.google.android.gms.cast.framework.zzz.zzb(parcelZzb.readStrongBinder());
        parcelZzb.recycle();
        return zzaaVarZzb;
    }

    @Override // com.google.android.gms.internal.cast.zzq
    public final com.google.android.gms.cast.framework.zzad zzf(CastOptions castOptions, IObjectWrapper iObjectWrapper, com.google.android.gms.cast.framework.zzx zzxVar) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzd(parcelZza, castOptions);
        zzc.zzf(parcelZza, iObjectWrapper);
        zzc.zzf(parcelZza, zzxVar);
        Parcel parcelZzb = zzb(3, parcelZza);
        com.google.android.gms.cast.framework.zzad zzadVarZzb = com.google.android.gms.cast.framework.zzac.zzb(parcelZzb.readStrongBinder());
        parcelZzb.recycle();
        return zzadVarZzb;
    }

    @Override // com.google.android.gms.internal.cast.zzq
    public final com.google.android.gms.cast.framework.zzak zzg(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzf(parcelZza, iObjectWrapper);
        zzc.zzf(parcelZza, iObjectWrapper2);
        zzc.zzf(parcelZza, iObjectWrapper3);
        Parcel parcelZzb = zzb(5, parcelZza);
        com.google.android.gms.cast.framework.zzak zzakVarZzb = com.google.android.gms.cast.framework.zzaj.zzb(parcelZzb.readStrongBinder());
        parcelZzb.recycle();
        return zzakVarZzb;
    }

    @Override // com.google.android.gms.internal.cast.zzq
    public final com.google.android.gms.cast.framework.zzan zzh(String str, String str2, com.google.android.gms.cast.framework.zzav zzavVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        zzc.zzf(parcelZza, zzavVar);
        Parcel parcelZzb = zzb(2, parcelZza);
        com.google.android.gms.cast.framework.zzan zzanVarZzb = com.google.android.gms.cast.framework.zzam.zzb(parcelZzb.readStrongBinder());
        parcelZzb.recycle();
        return zzanVarZzb;
    }

    @Override // com.google.android.gms.internal.cast.zzq
    public final com.google.android.gms.cast.framework.media.internal.zzi zzi(IObjectWrapper iObjectWrapper, com.google.android.gms.cast.framework.media.internal.zzk zzkVar, int i, int i2, boolean z, long j, int i3, int i4, int i5) throws RemoteException {
        Parcel parcelZza = zza();
        zzc.zzf(parcelZza, iObjectWrapper);
        zzc.zzf(parcelZza, zzkVar);
        parcelZza.writeInt(i);
        parcelZza.writeInt(i2);
        zzc.zzc(parcelZza, false);
        parcelZza.writeLong(2097152L);
        parcelZza.writeInt(5);
        parcelZza.writeInt(333);
        parcelZza.writeInt(10000);
        Parcel parcelZzb = zzb(6, parcelZza);
        com.google.android.gms.cast.framework.media.internal.zzi zziVarZzb = com.google.android.gms.cast.framework.media.internal.zzh.zzb(parcelZzb.readStrongBinder());
        parcelZzb.recycle();
        return zziVarZzb;
    }
}
