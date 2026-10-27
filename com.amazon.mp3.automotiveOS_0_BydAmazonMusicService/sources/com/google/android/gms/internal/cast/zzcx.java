package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzcx extends zzdc {
    final /* synthetic */ zzcy zza;

    zzcx(zzcy zzcyVar) {
        this.zza = zzcyVar;
    }

    @Override // com.google.android.gms.internal.cast.zzdc
    public final void zza(long j) {
        this.zza.zzc++;
        zzcy zzcyVar = this.zza;
        if (zzcyVar.zza(zzcyVar.zza) || this.zza.zza.isStarted()) {
            return;
        }
        zzcy zzcyVar2 = this.zza;
        if (zzcy.zze(zzcyVar2)) {
            return;
        }
        zzcyVar2.zza.start();
    }
}
