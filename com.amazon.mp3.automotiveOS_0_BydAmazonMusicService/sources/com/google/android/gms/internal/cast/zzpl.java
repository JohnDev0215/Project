package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzpl {
    private static final zzpj zza = new zzpk();
    private static final zzpj zzb;

    static {
        zzpj zzpjVar;
        try {
            zzpjVar = (zzpj) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzpjVar = null;
        }
        zzb = zzpjVar;
    }

    static zzpj zza() {
        zzpj zzpjVar = zzb;
        if (zzpjVar != null) {
            return zzpjVar;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    static zzpj zzb() {
        return zza;
    }
}
