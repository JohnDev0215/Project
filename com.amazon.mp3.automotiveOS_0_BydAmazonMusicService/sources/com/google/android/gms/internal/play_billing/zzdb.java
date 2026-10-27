package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes6.dex */
final class zzdb {
    private static final zzda zza;
    private static final zzda zzb;

    static {
        zzda zzdaVar;
        try {
            zzdaVar = (zzda) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzdaVar = null;
        }
        zza = zzdaVar;
        zzb = new zzda();
    }

    static zzda zza() {
        return zza;
    }

    static zzda zzb() {
        return zzb;
    }
}
