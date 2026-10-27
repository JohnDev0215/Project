package com.google.android.gms.cast.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzs implements Runnable {
    final /* synthetic */ zzw zza;
    final /* synthetic */ zzy zzb;

    zzs(zzv zzvVar, zzw zzwVar, zzy zzyVar) {
        this.zza = zzwVar;
        this.zzb = zzyVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        zzw.zzI(this.zza, this.zzb);
    }
}
