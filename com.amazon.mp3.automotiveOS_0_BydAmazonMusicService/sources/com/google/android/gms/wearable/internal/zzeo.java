package com.google.android.gms.wearable.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzeo extends com.google.android.gms.internal.wearable.zza {
    zzeo(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.wearable.internal.IRpcResponseCallback");
    }

    public final void zzd(boolean z, byte[] bArr) throws RemoteException {
        Parcel parcelZza = zza();
        com.google.android.gms.internal.wearable.zzc.zza(parcelZza, z);
        parcelZza.writeByteArray(bArr);
        zzH(1, parcelZza);
    }
}
