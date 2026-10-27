package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes6.dex */
final class zzcr extends zzct {
    private zzcr() {
        super(null);
    }

    /* synthetic */ zzcr(zzcq zzcqVar) {
        super(null);
    }

    @Override // com.google.android.gms.internal.play_billing.zzct
    final void zza(Object obj, long j) {
        ((zzcf) zzeq.zzf(obj, j)).zzb();
    }

    @Override // com.google.android.gms.internal.play_billing.zzct
    final void zzb(Object obj, Object obj2, long j) {
        zzcf zzcfVarZzd = (zzcf) zzeq.zzf(obj, j);
        zzcf zzcfVar = (zzcf) zzeq.zzf(obj2, j);
        int size = zzcfVarZzd.size();
        int size2 = zzcfVar.size();
        if (size > 0 && size2 > 0) {
            if (!zzcfVarZzd.zzc()) {
                zzcfVarZzd = zzcfVarZzd.zzd(size2 + size);
            }
            zzcfVarZzd.addAll(zzcfVar);
        }
        if (size > 0) {
            zzcfVar = zzcfVarZzd;
        }
        zzeq.zzs(obj, j, zzcfVar);
    }
}
