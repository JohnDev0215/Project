package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzx implements com.google.android.gms.cast.framework.internal.featurehighlight.zzg {
    final /* synthetic */ zzy zza;

    zzx(zzy zzyVar) {
        this.zza = zzyVar;
    }

    @Override // com.google.android.gms.cast.framework.internal.featurehighlight.zzg
    public final void zza() {
        zzy zzyVar = this.zza;
        if (zzyVar.zzg) {
            com.google.android.gms.cast.framework.zzaw.zza(zzyVar.zzb);
            this.zza.zze.zzj(new zzw(this));
        }
    }

    @Override // com.google.android.gms.cast.framework.internal.featurehighlight.zzg
    public final void zzb() {
        zzy zzyVar = this.zza;
        if (zzyVar.zzg) {
            com.google.android.gms.cast.framework.zzaw.zza(zzyVar.zzb);
            this.zza.zze.zzi(new zzv(this));
        }
    }
}
