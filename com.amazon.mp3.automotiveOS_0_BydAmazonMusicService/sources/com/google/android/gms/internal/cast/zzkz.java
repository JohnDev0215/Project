package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzkz extends zzpq implements zzrc {
    private zzkz() {
        super(zzla.zzb);
    }

    public final zzkz zza(String str) {
        if (this.zzb) {
            zzt();
            this.zzb = false;
        }
        zzla.zzd((zzla) this.zza, str);
        return this;
    }

    public final zzkz zzb(String str) {
        if (this.zzb) {
            zzt();
            this.zzb = false;
        }
        zzla.zze((zzla) this.zza, str);
        return this;
    }

    /* synthetic */ zzkz(zzko zzkoVar) {
        super(zzla.zzb);
    }
}
