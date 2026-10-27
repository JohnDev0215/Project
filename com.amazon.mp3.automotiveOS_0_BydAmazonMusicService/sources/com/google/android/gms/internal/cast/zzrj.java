package com.google.android.gms.internal.cast;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzrj {
    private static final zzrj zza = new zzrj();
    private final ConcurrentMap zzc = new ConcurrentHashMap();
    private final zzrn zzb = new zzqt();

    private zzrj() {
    }

    public static zzrj zza() {
        return zza;
    }

    public final zzrm zzb(Class cls) {
        zzqc.zzf(cls, "messageType");
        zzrm zzrmVarZza = (zzrm) this.zzc.get(cls);
        if (zzrmVarZza == null) {
            zzrmVarZza = this.zzb.zza(cls);
            zzqc.zzf(cls, "messageType");
            zzqc.zzf(zzrmVarZza, "schema");
            zzrm zzrmVar = (zzrm) this.zzc.putIfAbsent(cls, zzrmVarZza);
            if (zzrmVar != null) {
                return zzrmVar;
            }
        }
        return zzrmVarZza;
    }
}
