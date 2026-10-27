package com.google.android.gms.cast.framework;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzal extends com.google.android.gms.internal.cast.zza implements zzan {
    zzal(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.cast.framework.ISession");
    }

    @Override // com.google.android.gms.cast.framework.zzan
    public final int zze() throws RemoteException {
        Parcel parcelZzb = zzb(17, zza());
        int i = parcelZzb.readInt();
        parcelZzb.recycle();
        return i;
    }

    @Override // com.google.android.gms.cast.framework.zzan
    public final int zzf() throws RemoteException {
        Parcel parcelZzb = zzb(18, zza());
        int i = parcelZzb.readInt();
        parcelZzb.recycle();
        return i;
    }

    @Override // com.google.android.gms.cast.framework.zzan
    public final IObjectWrapper zzg() throws RemoteException {
        Parcel parcelZzb = zzb(1, zza());
        IObjectWrapper iObjectWrapperAsInterface = IObjectWrapper.Stub.asInterface(parcelZzb.readStrongBinder());
        parcelZzb.recycle();
        return iObjectWrapperAsInterface;
    }

    @Override // com.google.android.gms.cast.framework.zzan
    public final String zzh() throws RemoteException {
        Parcel parcelZzb = zzb(2, zza());
        String string = parcelZzb.readString();
        parcelZzb.recycle();
        return string;
    }

    @Override // com.google.android.gms.cast.framework.zzan
    public final String zzi() throws RemoteException {
        Parcel parcelZzb = zzb(3, zza());
        String string = parcelZzb.readString();
        parcelZzb.recycle();
        return string;
    }

    @Override // com.google.android.gms.cast.framework.zzan
    public final void zzj(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzc(15, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzan
    public final void zzk(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzc(12, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzan
    public final void zzl(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzc(13, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzan
    public final void zzm(boolean z) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.cast.zzc.zzc(parcelZza, z);
        zzc(14, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzan
    public final void zzn(String str) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeString(str);
        zzc(11, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzan
    public final void zzo(int i) throws RemoteException {
        Parcel parcelZza = zza();
        parcelZza.writeInt(i);
        zzc(16, parcelZza);
    }

    @Override // com.google.android.gms.cast.framework.zzan
    public final boolean zzp() throws RemoteException {
        Parcel parcelZzb = zzb(5, zza());
        boolean zZzg = com.google.android.gms.internal.cast.zzc.zzg(parcelZzb);
        parcelZzb.recycle();
        return zZzg;
    }

    @Override // com.google.android.gms.cast.framework.zzan
    public final boolean zzq() throws RemoteException {
        Parcel parcelZzb = zzb(6, zza());
        boolean zZzg = com.google.android.gms.internal.cast.zzc.zzg(parcelZzb);
        parcelZzb.recycle();
        return zZzg;
    }

    @Override // com.google.android.gms.cast.framework.zzan
    public final boolean zzr() throws RemoteException {
        Parcel parcelZzb = zzb(8, zza());
        boolean zZzg = com.google.android.gms.internal.cast.zzc.zzg(parcelZzb);
        parcelZzb.recycle();
        return zZzg;
    }

    @Override // com.google.android.gms.cast.framework.zzan
    public final boolean zzs() throws RemoteException {
        Parcel parcelZzb = zzb(7, zza());
        boolean zZzg = com.google.android.gms.internal.cast.zzc.zzg(parcelZzb);
        parcelZzb.recycle();
        return zZzg;
    }

    @Override // com.google.android.gms.cast.framework.zzan
    public final boolean zzt() throws RemoteException {
        Parcel parcelZzb = zzb(9, zza());
        boolean zZzg = com.google.android.gms.internal.cast.zzc.zzg(parcelZzb);
        parcelZzb.recycle();
        return zZzg;
    }

    @Override // com.google.android.gms.cast.framework.zzan
    public final boolean zzu() throws RemoteException {
        Parcel parcelZzb = zzb(10, zza());
        boolean zZzg = com.google.android.gms.internal.cast.zzc.zzg(parcelZzb);
        parcelZzb.recycle();
        return zZzg;
    }
}
