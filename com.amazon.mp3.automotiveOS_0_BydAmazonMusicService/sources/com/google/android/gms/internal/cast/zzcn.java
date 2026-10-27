package com.google.android.gms.internal.cast;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzcn extends zzcs {
    final /* synthetic */ zzct zza;
    final /* synthetic */ zzco zzb;

    zzcn(zzco zzcoVar, zzct zzctVar) {
        this.zzb = zzcoVar;
        this.zza = zzctVar;
    }

    @Override // com.google.android.gms.internal.cast.zzct
    public final void zzb(int i) throws RemoteException {
        zzco.zze.d("onRemoteDisplayEnded", new Object[0]);
        zzct zzctVar = this.zza;
        if (zzctVar != null) {
            zzctVar.zzb(i);
        }
        zzco zzcoVar = this.zzb;
        if (zzcoVar.zzf != null) {
            zzcoVar.zzf.onRemoteDisplayEnded(new Status(i));
        }
    }
}
