package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zznj extends zzpt implements zzrc {
    private static final zznj zzb;
    private int zze;
    private zzqb zzf = zzpt.zzw();
    private zzqb zzg = zzpt.zzw();
    private zznr zzh;

    static {
        zznj zznjVar = new zznj();
        zzb = zznjVar;
        zzpt.zzA(zznj.class, zznjVar);
    }

    private zznj() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0002\u0000\u0001\u001b\u0002\u001b\u0003ဉ\u0000", new Object[]{"zze", "zzf", zznx.class, "zzg", zzlq.class, "zzh"});
        }
        if (i2 == 3) {
            return new zznj();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzni(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
