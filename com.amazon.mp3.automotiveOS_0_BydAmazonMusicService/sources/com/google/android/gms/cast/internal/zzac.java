package com.google.android.gms.cast.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class zzac extends com.google.android.gms.internal.cast.zzb implements zzad {
    public zzac() {
        super("com.google.android.gms.cast.internal.IBundleCallback");
    }

    @Override // com.google.android.gms.internal.cast.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        Bundle bundle = (Bundle) com.google.android.gms.internal.cast.zzc.zza(parcel, Bundle.CREATOR);
        com.google.android.gms.internal.cast.zzc.zzb(parcel);
        zzb(bundle);
        return true;
    }
}
