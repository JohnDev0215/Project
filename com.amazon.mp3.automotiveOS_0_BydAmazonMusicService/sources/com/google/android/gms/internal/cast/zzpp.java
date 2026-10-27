package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzpp implements zzqz {
    private static final zzpp zza = new zzpp();

    private zzpp() {
    }

    public static zzpp zza() {
        return zza;
    }

    @Override // com.google.android.gms.internal.cast.zzqz
    public final zzqy zzb(Class cls) {
        if (!zzpt.class.isAssignableFrom(cls)) {
            String name = cls.getName();
            throw new IllegalArgumentException(name.length() != 0 ? "Unsupported message type: ".concat(name) : new String("Unsupported message type: "));
        }
        try {
            return (zzqy) zzpt.zzt(cls.asSubclass(zzpt.class)).zzb(3, null, null);
        } catch (Exception e) {
            String name2 = cls.getName();
            throw new RuntimeException(name2.length() != 0 ? "Unable to get message info for ".concat(name2) : new String("Unable to get message info for "), e);
        }
    }

    @Override // com.google.android.gms.internal.cast.zzqz
    public final boolean zzc(Class cls) {
        return zzpt.class.isAssignableFrom(cls);
    }
}
