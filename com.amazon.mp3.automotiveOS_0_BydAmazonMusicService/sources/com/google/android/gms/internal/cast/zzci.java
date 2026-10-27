package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzci extends zzcg {
    final /* synthetic */ zzcj zza;

    protected zzci(zzcj zzcjVar) {
        this.zza = zzcjVar;
    }

    @Override // com.google.android.gms.internal.cast.zzcg, com.google.android.gms.internal.cast.zzcq
    public final void zzd(int i) throws RemoteException {
        zzcl.zza.d("onError: %d", Integer.valueOf(i));
        zzcl.zzf(this.zza.zzc);
        this.zza.setResult(new zzck(Status.RESULT_INTERNAL_ERROR));
    }

    @Override // com.google.android.gms.internal.cast.zzcg, com.google.android.gms.internal.cast.zzcq
    public final void zzf() throws RemoteException {
        zzcl.zza.d("onDisconnected", new Object[0]);
        zzcl.zzf(this.zza.zzc);
        this.zza.setResult(new zzck(Status.RESULT_SUCCESS));
    }
}
