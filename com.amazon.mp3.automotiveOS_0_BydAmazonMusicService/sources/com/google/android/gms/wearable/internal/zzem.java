package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzem extends com.google.android.gms.internal.wearable.zzb implements zzen {
    public zzem() {
        super("com.google.android.gms.wearable.internal.IChannelStreamCallbacks");
    }

    @Override // com.google.android.gms.internal.wearable.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 2) {
            return false;
        }
        zzc(parcel.readInt(), parcel.readInt());
        parcel2.writeNoException();
        return true;
    }
}
