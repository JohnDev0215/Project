package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zznd extends zzpt implements zzrc {
    private static final zznd zzb;
    private int zze;
    private int zzf;
    private String zzg = "";

    static {
        zznd zzndVar = new zznd();
        zzb = zzndVar;
        zzpt.zzA(zznd.class, zzndVar);
    }

    private zznd() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001င\u0000\u0002ဈ\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zznd();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zznc(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
