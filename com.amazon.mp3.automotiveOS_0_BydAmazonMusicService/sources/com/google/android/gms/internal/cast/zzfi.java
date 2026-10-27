package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzfi extends zzpt implements zzrc {
    private static final zzfi zzb;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private zzfe zzj;
    private int zzk;

    static {
        zzfi zzfiVar = new zzfi();
        zzb = zzfiVar;
        zzpt.zzA(zzfi.class, zzfiVar);
    }

    private zzfi() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001င\u0000\u0002ဌ\u0001\u0003င\u0002\u0004င\u0003\u0005ဉ\u0004\u0006ဌ\u0005", new Object[]{"zze", "zzf", "zzg", zzfh.zza, "zzh", "zzi", "zzj", "zzk", zzfg.zza});
        }
        if (i2 == 3) {
            return new zzfi();
        }
        zzew zzewVar = null;
        if (i2 == 4) {
            return new zzff(zzewVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
