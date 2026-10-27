package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzle extends zzpt implements zzrc {
    private static final zzle zzb;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;

    static {
        zzle zzleVar = new zzle();
        zzb = zzleVar;
        zzpt.zzA(zzle.class, zzleVar);
    }

    private zzle() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဋ\u0000\u0002ဋ\u0001\u0003ဌ\u0002\u0004င\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", zzhw.zza(), "zzi"});
        }
        if (i2 == 3) {
            return new zzle();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzld(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
