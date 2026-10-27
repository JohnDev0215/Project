package com.google.android.gms.cast.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.cast.zzbs;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzae extends com.google.android.gms.internal.cast.zza {
    zzae(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.internal.ICastDeviceController");
    }

    public final void zze() throws RemoteException {
        zzd(17, zza());
    }

    public final void zzf() throws RemoteException {
        zzd(1, zza());
    }

    public final void zzg(zzad zzadVar, String[] strArr) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzf(parcelZza, zzadVar);
        parcelZza.writeStringArray(strArr);
        zzd(20, parcelZza);
    }

    public final void zzh(String str, String str2, zzbs zzbsVar) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        com.google.android.gms.internal.cast.zzc.zzd(parcelZza, zzbsVar);
        zzd(14, parcelZza);
    }

    public final void zzi(String str, LaunchOptions launchOptions) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        com.google.android.gms.internal.cast.zzc.zzd(parcelZza, launchOptions);
        zzd(13, parcelZza);
    }

    public final void zzj() throws RemoteException {
        zzd(4, zza());
    }

    public final void zzk(zzag zzagVar) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzf(parcelZza, zzagVar);
        zzd(18, parcelZza);
    }

    public final void zzl(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzd(11, parcelZza);
    }

    public final void zzm() throws RemoteException {
        zzd(6, zza());
    }

    public final void zzn(String str, String str2, long j) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        parcelZza.writeString(str2);
        parcelZza.writeLong(j);
        zzd(9, parcelZza);
    }

    public final void zzo(boolean z, double d, boolean z2) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, z);
        parcelZza.writeDouble(d);
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, z2);
        zzd(8, parcelZza);
    }

    public final void zzp(double d, double d2, boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeDouble(d);
        parcelZza.writeDouble(d2);
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, z);
        zzd(7, parcelZza);
    }

    public final void zzq(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzd(5, parcelZza);
    }

    public final void zzr() throws RemoteException {
        zzd(19, zza());
    }

    public final void zzs(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzd(12, parcelZza);
    }
}
