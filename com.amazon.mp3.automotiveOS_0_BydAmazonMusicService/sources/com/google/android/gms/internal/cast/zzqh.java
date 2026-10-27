package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public class zzqh {
    private static final zzpi zzb = zzpi.zza();
    protected volatile zzrb zza;
    private volatile zzoy zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzqh)) {
            return false;
        }
        zzqh zzqhVar = (zzqh) obj;
        zzrb zzrbVar = this.zza;
        zzrb zzrbVar2 = zzqhVar.zza;
        if (zzrbVar == null && zzrbVar2 == null) {
            return zzb().equals(zzqhVar.zzb());
        }
        if (zzrbVar != null && zzrbVar2 != null) {
            return zzrbVar.equals(zzrbVar2);
        }
        if (zzrbVar != null) {
            zzqhVar.zzc(zzrbVar.zzs());
            return zzrbVar.equals(zzqhVar.zza);
        }
        zzc(zzrbVar2.zzs());
        return this.zza.equals(zzrbVar2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zzov) this.zzc).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzq();
        }
        return 0;
    }

    public final zzoy zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            if (this.zzc != null) {
                return this.zzc;
            }
            if (this.zza == null) {
                this.zzc = zzoy.zzb;
            } else {
                this.zzc = this.zza.zzo();
            }
            return this.zzc;
        }
    }

    protected final void zzc(zzrb zzrbVar) {
        if (this.zza != null) {
            return;
        }
        synchronized (this) {
            if (this.zza == null) {
                try {
                    this.zza = zzrbVar;
                    this.zzc = zzoy.zzb;
                } catch (zzqe unused) {
                    this.zza = zzrbVar;
                    this.zzc = zzoy.zzb;
                }
            }
        }
    }
}
