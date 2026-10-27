package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zznr extends zzpt implements zzrc {
    private static final zznr zzb;
    private int zze;
    private zzkw zzf;

    static {
        zznr zznrVar = new zznr();
        zzb = zznrVar;
        zzpt.zzA(zznr.class, zznrVar);
    }

    private zznr() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဉ\u0000", new Object[]{"zze", "zzf"});
        }
        if (i2 == 3) {
            return new zznr();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zznq(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
