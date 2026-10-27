package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzob extends zzpt implements zzrc {
    private static final zzob zzb;
    private int zze;
    private int zzf;
    private int zzg;
    private zzqb zzh = zzpt.zzw();
    private zzqb zzi = zzpt.zzw();
    private int zzj;

    static {
        zzob zzobVar = new zzob();
        zzb = zzobVar;
        zzpt.zzA(zzob.class, zzobVar);
    }

    private zzob() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0002\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003\u001b\u0004\u001b\u0005ဌ\u0002", new Object[]{"zze", "zzf", zzjy.zza(), "zzg", zzkb.zza(), "zzh", zzls.class, "zzi", zzls.class, "zzj", zzhb.zza()});
        }
        if (i2 == 3) {
            return new zzob();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzoa(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
