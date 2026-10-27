package com.google.android.gms.cast.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.internal.IStatusCallback;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzah extends com.google.android.gms.internal.cast.zza {
    zzah(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.internal.ICastService");
    }

    public final void zze(IStatusCallback iStatusCallback, String[] strArr, String str, List list) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzf(parcelZza, iStatusCallback);
        parcelZza.writeStringArray(strArr);
        parcelZza.writeString(str);
        parcelZza.writeTypedList(null);
        zzd(2, parcelZza);
    }

    public final void zzf(zzad zzadVar, String[] strArr) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzf(parcelZza, zzadVar);
        parcelZza.writeStringArray(strArr);
        zzd(5, parcelZza);
    }

    public final void zzg(zzad zzadVar, String[] strArr) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzf(parcelZza, zzadVar);
        parcelZza.writeStringArray(strArr);
        zzd(7, parcelZza);
    }

    public final void zzh(zzad zzadVar, String[] strArr) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzf(parcelZza, zzadVar);
        parcelZza.writeStringArray(strArr);
        zzd(6, parcelZza);
    }
}
