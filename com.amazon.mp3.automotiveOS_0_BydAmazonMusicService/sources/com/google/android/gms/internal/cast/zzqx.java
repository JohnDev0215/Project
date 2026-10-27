package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzqx {
    private static final zzqw zza;
    private static final zzqw zzb;

    static {
        zzqw zzqwVar;
        try {
            zzqwVar = (zzqw) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzqwVar = null;
        }
        zza = zzqwVar;
        zzb = new zzqw();
    }

    static zzqw zza() {
        return zza;
    }

    static zzqw zzb() {
        return zzb;
    }
}
