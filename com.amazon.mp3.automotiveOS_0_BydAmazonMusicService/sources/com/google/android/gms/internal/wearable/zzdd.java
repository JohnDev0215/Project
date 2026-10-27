package com.google.android.gms.internal.wearable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzdd {
    private static final zzdc zza;
    private static final zzdc zzb;

    static {
        zzdc zzdcVar;
        try {
            zzdcVar = (zzdc) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            zzdcVar = null;
        }
        zza = zzdcVar;
        zzb = new zzdc();
    }

    static zzdc zza() {
        return zza;
    }

    static zzdc zzb() {
        return zzb;
    }
}
