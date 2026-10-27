package com.google.android.gms.internal.wearable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzbo implements zzcv {
    private static final zzbo zza = new zzbo();

    private zzbo() {
    }

    public static zzbo zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.wearable.zzcv
    public final boolean zzb(Class<?> cls) {
        return zzbs.class.isAssignableFrom(cls);
    }

    @Override // com.google.android.gms.internal.wearable.zzcv
    public final zzcu zzc(Class<?> cls) {
        if (!zzbs.class.isAssignableFrom(cls)) {
            String name = cls.getName();
            throw new IllegalArgumentException(name.length() != 0 ? "Unsupported message type: ".concat(name) : new String("Unsupported message type: "));
        }
        try {
            return (zzcu) zzbs.zzQ(cls.asSubclass(zzbs.class)).zzG(3, null, null);
        } catch (Exception e) {
            String name2 = cls.getName();
            throw new RuntimeException(name2.length() != 0 ? "Unable to get message info for ".concat(name2) : new String("Unable to get message info for "), e);
        }
    }
}
