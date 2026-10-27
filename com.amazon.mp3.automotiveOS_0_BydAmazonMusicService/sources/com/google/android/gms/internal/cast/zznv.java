package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zznv extends zzpt implements zzrc {
    private static final zznv zzb;
    private int zze;
    private String zzf = "";

    static {
        zznv zznvVar = new zznv();
        zzb = zznvVar;
        zzpt.zzA(zznv.class, zznvVar);
    }

    private zznv() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0001\u0000\u0001\u0001\u0001\u0001\u0000\u0000\u0000\u0001ဈ\u0000", new Object[]{"zze", "zzf"});
        }
        if (i2 == 3) {
            return new zznv();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zznu(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
