package com.google.android.gms.internal.cast;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzr extends zzb implements zzs {
    public zzr() {
        super("com.google.android.gms.cast.framework.internal.IMediaRouter");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        zzu zztVar;
        switch (i) {
            case 1:
                Bundle bundle = (Bundle) zzc.zza(parcel, Bundle.CREATOR);
                IBinder strongBinder = parcel.readStrongBinder();
                if (strongBinder == null) {
                    zztVar = null;
                } else {
                    IInterface iInterfaceQueryLocalInterface = strongBinder.queryLocalInterface("com.google.android.gms.cast.framework.internal.IMediaRouterCallback");
                    zztVar = iInterfaceQueryLocalInterface instanceof zzu ? (zzu) iInterfaceQueryLocalInterface : new zzt(strongBinder);
                }
                zzc.zzb(parcel);
                zze(bundle, zztVar);
                parcel2.writeNoException();
                return true;
            case 2:
                Bundle bundle2 = (Bundle) zzc.zza(parcel, Bundle.CREATOR);
                int i3 = parcel.readInt();
                zzc.zzb(parcel);
                zzd(bundle2, i3);
                parcel2.writeNoException();
                return true;
            case 3:
                Bundle bundle3 = (Bundle) zzc.zza(parcel, Bundle.CREATOR);
                zzc.zzb(parcel);
                zzg(bundle3);
                parcel2.writeNoException();
                return true;
            case 4:
                Bundle bundle4 = (Bundle) zzc.zza(parcel, Bundle.CREATOR);
                int i4 = parcel.readInt();
                zzc.zzb(parcel);
                boolean zZzm = zzm(bundle4, i4);
                parcel2.writeNoException();
                zzc.zzc(parcel2, zZzm);
                return true;
            case 5:
                String string = parcel.readString();
                zzc.zzb(parcel);
                zzi(string);
                parcel2.writeNoException();
                return true;
            case 6:
                zzh();
                parcel2.writeNoException();
                return true;
            case 7:
                boolean zZzl = zzl();
                parcel2.writeNoException();
                zzc.zzc(parcel2, zZzl);
                return true;
            case 8:
                String string2 = parcel.readString();
                zzc.zzb(parcel);
                Bundle bundleZzb = zzb(string2);
                parcel2.writeNoException();
                zzc.zze(parcel2, bundleZzb);
                return true;
            case 9:
                String strZzc = zzc();
                parcel2.writeNoException();
                parcel2.writeString(strZzc);
                return true;
            case 10:
                parcel2.writeNoException();
                parcel2.writeInt(12451000);
                return true;
            case 11:
                zzf();
                parcel2.writeNoException();
                return true;
            case 12:
                boolean zZzk = zzk();
                parcel2.writeNoException();
                zzc.zzc(parcel2, zZzk);
                return true;
            case 13:
                int i5 = parcel.readInt();
                zzc.zzb(parcel);
                zzj(i5);
                parcel2.writeNoException();
                return true;
            default:
                return false;
        }
    }
}
