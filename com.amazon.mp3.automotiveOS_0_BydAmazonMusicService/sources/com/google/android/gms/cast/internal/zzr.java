package com.google.android.gms.cast.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzr implements Runnable {
    final /* synthetic */ zzw zza;
    final /* synthetic */ int zzb;

    zzr(zzv zzvVar, zzw zzwVar, int i) {
        this.zza = zzwVar;
        this.zzb = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zza.zzj.onApplicationDisconnected(this.zzb);
    }
}
