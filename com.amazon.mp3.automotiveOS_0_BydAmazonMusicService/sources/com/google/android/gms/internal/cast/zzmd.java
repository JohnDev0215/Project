package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzmd extends zzpt implements zzrc {
    private static final zzmd zzb;
    private int zze;
    private int zzf;
    private int zzg;
    private zzpy zzh = zzpt.zzu();
    private zzpy zzi = zzpt.zzu();
    private zzqb zzj = zzpt.zzw();
    private zzqb zzk = zzpt.zzw();
    private int zzl;

    static {
        zzmd zzmdVar = new zzmd();
        zzb = zzmdVar;
        zzpt.zzA(zzmd.class, zzmdVar);
    }

    private zzmd() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0004\u0000\u0001င\u0000\u0002ဌ\u0001\u0003\u0016\u0004\u0016\u0005\u001a\u0006\u001a\u0007ဌ\u0002", new Object[]{"zze", "zzf", "zzg", zzhn.zza(), "zzh", "zzi", "zzj", "zzk", "zzl", zzhb.zza()});
        }
        if (i2 == 3) {
            return new zzmd();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzmc(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
