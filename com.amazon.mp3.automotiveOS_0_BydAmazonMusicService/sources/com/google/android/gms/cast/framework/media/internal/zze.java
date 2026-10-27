package com.google.android.gms.cast.framework.media.internal;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zze extends zzj {
    final /* synthetic */ zzf zza;

    /* synthetic */ zze(zzf zzfVar, zzd zzdVar) {
        this.zza = zzfVar;
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zzk
    public final void zzb(long j, long j2) {
        this.zza.publishProgress(Long.valueOf(j), Long.valueOf(j2));
    }
}
