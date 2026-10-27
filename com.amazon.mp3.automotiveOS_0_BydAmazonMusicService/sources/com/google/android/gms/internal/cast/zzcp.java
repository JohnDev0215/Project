package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.RemoteException;
import android.view.Surface;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzcp extends zzb implements zzcq {
    public zzcp() {
        super("com.google.android.gms.cast.remote_display.ICastRemoteDisplayCallbacks");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            int i3 = parcel.readInt();
            int i4 = parcel.readInt();
            Surface surface = (Surface) zzc.zza(parcel, Surface.CREATOR);
            zzc.zzb(parcel);
            zzb(i3, i4, surface);
        } else if (i == 2) {
            int i5 = parcel.readInt();
            zzc.zzb(parcel);
            zzd(i5);
        } else if (i == 3) {
            zzf();
        } else if (i == 4) {
            zzc();
        } else {
            if (i != 5) {
                return false;
            }
            boolean zZzg = zzc.zzg(parcel);
            zzc.zzb(parcel);
            zze(zZzg);
        }
        parcel2.writeNoException();
        return true;
    }
}
