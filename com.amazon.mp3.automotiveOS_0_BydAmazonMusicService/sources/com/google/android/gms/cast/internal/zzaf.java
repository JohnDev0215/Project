package com.google.android.gms.cast.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.cast.ApplicationMetadata;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzaf extends com.google.android.gms.internal.cast.zzb implements zzag {
    public zzaf() {
        super("com.google.android.gms.cast.internal.ICastDeviceControllerListener");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        switch (i) {
            case 1:
                int i3 = parcel.readInt();
                com.google.android.gms.internal.cast.zzc.zzb(parcel);
                zzk(i3);
                return true;
            case 2:
                ApplicationMetadata applicationMetadata = (ApplicationMetadata) com.google.android.gms.internal.cast.zzc.zza(parcel, ApplicationMetadata.CREATOR);
                String string = parcel.readString();
                String string2 = parcel.readString();
                boolean zZzg = com.google.android.gms.internal.cast.zzc.zzg(parcel);
                com.google.android.gms.internal.cast.zzc.zzb(parcel);
                zzb(applicationMetadata, string, string2, zZzg);
                return true;
            case 3:
                int i4 = parcel.readInt();
                com.google.android.gms.internal.cast.zzc.zzb(parcel);
                zzc(i4);
                return true;
            case 4:
                String string3 = parcel.readString();
                double d = parcel.readDouble();
                boolean zZzg2 = com.google.android.gms.internal.cast.zzc.zzg(parcel);
                com.google.android.gms.internal.cast.zzc.zzb(parcel);
                zzn(string3, d, zZzg2);
                return true;
            case 5:
                String string4 = parcel.readString();
                String string5 = parcel.readString();
                com.google.android.gms.internal.cast.zzc.zzb(parcel);
                zzp(string4, string5);
                return true;
            case 6:
                String string6 = parcel.readString();
                byte[] bArrCreateByteArray = parcel.createByteArray();
                com.google.android.gms.internal.cast.zzc.zzb(parcel);
                zzh(string6, bArrCreateByteArray);
                return true;
            case 7:
                int i5 = parcel.readInt();
                com.google.android.gms.internal.cast.zzc.zzb(parcel);
                zze(i5);
                return true;
            case 8:
                int i6 = parcel.readInt();
                com.google.android.gms.internal.cast.zzc.zzb(parcel);
                zzg(i6);
                return true;
            case 9:
                int i7 = parcel.readInt();
                com.google.android.gms.internal.cast.zzc.zzb(parcel);
                zzd(i7);
                return true;
            case 10:
                String string7 = parcel.readString();
                long j = parcel.readLong();
                int i8 = parcel.readInt();
                com.google.android.gms.internal.cast.zzc.zzb(parcel);
                zzm(string7, j, i8);
                return true;
            case 11:
                String string8 = parcel.readString();
                long j2 = parcel.readLong();
                com.google.android.gms.internal.cast.zzc.zzb(parcel);
                zzl(string8, j2);
                return true;
            case 12:
                zza zzaVar = (zza) com.google.android.gms.internal.cast.zzc.zza(parcel, zza.CREATOR);
                com.google.android.gms.internal.cast.zzc.zzb(parcel);
                zzf(zzaVar);
                return true;
            case 13:
                zzy zzyVar = (zzy) com.google.android.gms.internal.cast.zzc.zza(parcel, zzy.CREATOR);
                com.google.android.gms.internal.cast.zzc.zzb(parcel);
                zzj(zzyVar);
                return true;
            case 14:
                int i9 = parcel.readInt();
                com.google.android.gms.internal.cast.zzc.zzb(parcel);
                zzi(i9);
                return true;
            case 15:
                int i10 = parcel.readInt();
                com.google.android.gms.internal.cast.zzc.zzb(parcel);
                zzo(i10);
                return true;
            default:
                return false;
        }
    }
}
