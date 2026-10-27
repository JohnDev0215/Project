package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzkw extends zzpt implements zzrc {
    private static final zzkw zzb;
    private int zze;
    private int zzf;
    private double zzg;
    private double zzh;
    private double zzi;
    private double zzj;

    static {
        zzkw zzkwVar = new zzkw();
        zzb = zzkwVar;
        zzpt.zzA(zzkw.class, zzkwVar);
    }

    private zzkw() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဋ\u0000\u0002က\u0001\u0003က\u0002\u0004က\u0003\u0005က\u0004", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj"});
        }
        if (i2 == 3) {
            return new zzkw();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzkv(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
