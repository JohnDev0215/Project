package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzmf extends zzpt implements zzrc {
    private static final zzmf zzb;
    private int zze;
    private int zzf;

    static {
        zzmf zzmfVar = new zzmf();
        zzb = zzmfVar;
        zzpt.zzA(zzmf.class, zzmfVar);
    }

    private zzmf() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဌ\u0000", new Object[]{"zze", "zzf", zzhn.zza()});
        }
        if (i2 == 3) {
            return new zzmf();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzme(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
