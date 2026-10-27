package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzrh {
    private static final zzrg zza;
    private static final zzrg zzb;

    static {
        zzrg zzrgVar;
        try {
            zzrgVar = (zzrg) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzrgVar = null;
        }
        zza = zzrgVar;
        zzb = new zzrg();
    }

    static zzrg zza() {
        return zza;
    }

    static zzrg zzb() {
        return zzb;
    }
}
