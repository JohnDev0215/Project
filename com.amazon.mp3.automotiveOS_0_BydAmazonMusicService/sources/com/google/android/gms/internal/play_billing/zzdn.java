package com.google.android.gms.internal.play_billing;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes6.dex */
final class zzdn {
    private static final zzdn zza = new zzdn();
    private final ConcurrentMap zzc = new ConcurrentHashMap();
    private final zzdq zzb = new zzcx();

    private zzdn() {
    }

    public static zzdn zza() {
        return zza;
    }

    public final zzdp zzb(Class cls) {
        zzcg.zzc(cls, "messageType");
        zzdp zzdpVarZza = (zzdp) this.zzc.get(cls);
        if (zzdpVarZza == null) {
            zzdpVarZza = this.zzb.zza(cls);
            zzcg.zzc(cls, "messageType");
            zzcg.zzc(zzdpVarZza, "schema");
            zzdp zzdpVar = (zzdp) this.zzc.putIfAbsent(cls, zzdpVarZza);
            if (zzdpVar != null) {
                return zzdpVar;
            }
        }
        return zzdpVarZza;
    }
}
