package com.google.android.gms.internal.cast;

import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzcs extends zzb implements zzct {
    public zzcs() {
        super("com.google.android.gms.cast.remote_display.ICastRemoteDisplaySessionCallbacks");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        int i3 = parcel.readInt();
        zzc.zzb(parcel);
        zzb(i3);
        return true;
    }
}
