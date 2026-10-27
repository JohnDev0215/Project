package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes6.dex */
final class zzdl {
    private static final zzdk zza;
    private static final zzdk zzb;

    static {
        zzdk zzdkVar;
        try {
            zzdkVar = (zzdk) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzdkVar = null;
        }
        zza = zzdkVar;
        zzb = new zzdk();
    }

    static zzdk zza() {
        return zza;
    }

    static zzdk zzb() {
        return zzb;
    }
}
