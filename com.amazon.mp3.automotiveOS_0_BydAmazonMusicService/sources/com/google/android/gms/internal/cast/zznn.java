package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zznn extends zzpt implements zzrc {
    private static final zznn zzb;
    private int zze;
    private zzls zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private long zzk;
    private zzqb zzl = zzpt.zzw();

    static {
        zznn zznnVar = new zznn();
        zzb = zznnVar;
        zzpt.zzA(zznn.class, zznnVar);
    }

    private zznn() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0001\u0000\u0001ဉ\u0000\u0002ဌ\u0001\u0003ဌ\u0002\u0004ဌ\u0003\u0005ဌ\u0004\u0006ဂ\u0005\u0007\u001b", new Object[]{"zze", "zzf", "zzg", zzjd.zza(), "zzh", zzja.zza(), "zzi", zzhb.zza(), "zzj", zzfx.zza(), "zzk", "zzl", zzls.class});
        }
        if (i2 == 3) {
            return new zznn();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zznm(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
