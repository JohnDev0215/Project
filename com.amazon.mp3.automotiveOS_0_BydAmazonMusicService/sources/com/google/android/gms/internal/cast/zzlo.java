package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzlo extends zzpt implements zzrc {
    private static final zzlo zzb;
    private int zze;
    private int zzf = 0;
    private Object zzg;
    private long zzh;

    static {
        zzlo zzloVar = new zzlo();
        zzb = zzloVar;
        zzpt.zzA(zzlo.class, zzloVar);
    }

    private zzlo() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0004\u0001\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001စ\u0000\u0002်\u0000\u0003ဵ\u0000\u0004း\u0000", new Object[]{"zzg", "zzf", "zze", "zzh"});
        }
        if (i2 == 3) {
            return new zzlo();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzln(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
