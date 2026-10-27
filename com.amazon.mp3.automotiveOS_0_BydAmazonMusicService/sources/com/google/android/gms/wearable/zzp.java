package com.google.android.gms.wearable;

import com.google.android.gms.wearable.internal.zzfj;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzp implements Runnable {
    final /* synthetic */ zzfj zza;
    final /* synthetic */ zzx zzb;

    zzp(zzx zzxVar, zzfj zzfjVar) {
        this.zzb = zzxVar;
        this.zza = zzfjVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zza.onMessageReceived(this.zza);
    }
}
