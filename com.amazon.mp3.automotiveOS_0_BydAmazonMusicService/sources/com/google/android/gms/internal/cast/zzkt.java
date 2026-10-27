package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzkt extends zzpq implements zzrc {
    private zzkt() {
        super(zzku.zze);
    }

    public final zzkt zza(Iterable iterable) {
        if (this.zzb) {
            zzt();
            this.zzb = false;
        }
        zzku.zze((zzku) this.zza, iterable);
        return this;
    }

    public final zzkt zzb(zzla zzlaVar) {
        if (this.zzb) {
            zzt();
            this.zzb = false;
        }
        zzku.zzd((zzku) this.zza, zzlaVar);
        return this;
    }

    /* synthetic */ zzkt(zzko zzkoVar) {
        super(zzku.zze);
    }
}
