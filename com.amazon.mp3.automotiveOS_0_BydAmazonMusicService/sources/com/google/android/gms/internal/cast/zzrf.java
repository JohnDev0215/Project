package com.google.android.gms.internal.cast;

import java.io.IOException;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzrf implements zzrm {
    private final zzrb zza;
    private final zzsd zzb;
    private final boolean zzc;
    private final zzpj zzd;

    private zzrf(zzsd zzsdVar, zzpj zzpjVar, zzrb zzrbVar) {
        this.zzb = zzsdVar;
        this.zzc = zzpjVar.zzc(zzrbVar);
        this.zzd = zzpjVar;
        this.zza = zzrbVar;
    }

    static zzrf zzg(zzsd zzsdVar, zzpj zzpjVar, zzrb zzrbVar) {
        return new zzrf(zzsdVar, zzpjVar, zzrbVar);
    }

    @Override // com.google.android.gms.internal.cast.zzrm
    public final int zza(Object obj) {
        zzsd zzsdVar = this.zzb;
        int iZzb = zzsdVar.zzb(zzsdVar.zzc(obj));
        if (!this.zzc) {
            return iZzb;
        }
        this.zzd.zza(obj);
        throw null;
    }

    @Override // com.google.android.gms.internal.cast.zzrm
    public final int zzb(Object obj) {
        int iHashCode = this.zzb.zzc(obj).hashCode();
        if (!this.zzc) {
            return iHashCode;
        }
        this.zzd.zza(obj);
        throw null;
    }

    @Override // com.google.android.gms.internal.cast.zzrm
    public final void zzc(Object obj) {
        this.zzb.zze(obj);
        this.zzd.zzb(obj);
    }

    @Override // com.google.android.gms.internal.cast.zzrm
    public final void zzd(Object obj, Object obj2) {
        zzro.zzD(this.zzb, obj, obj2);
        if (this.zzc) {
            zzro.zzC(this.zzd, obj, obj2);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzrm
    public final boolean zze(Object obj, Object obj2) {
        if (!this.zzb.zzc(obj).equals(this.zzb.zzc(obj2))) {
            return false;
        }
        if (!this.zzc) {
            return true;
        }
        this.zzd.zza(obj);
        this.zzd.zza(obj2);
        throw null;
    }

    @Override // com.google.android.gms.internal.cast.zzrm
    public final boolean zzf(Object obj) {
        this.zzd.zza(obj);
        throw null;
    }

    @Override // com.google.android.gms.internal.cast.zzrm
    public final void zzi(Object obj, zzph zzphVar) throws IOException {
        this.zzd.zza(obj);
        throw null;
    }
}
