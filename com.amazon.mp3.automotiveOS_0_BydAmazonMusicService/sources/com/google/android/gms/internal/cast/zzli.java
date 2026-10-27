package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzli extends zzpt implements zzrc {
    private static final zzli zzb;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private boolean zzi;
    private int zzj;

    static {
        zzli zzliVar = new zzli();
        zzb = zzliVar;
        zzpt.zzA(zzli.class, zzliVar);
    }

    private zzli() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003ဌ\u0002\u0004ဇ\u0003\u0005င\u0004", new Object[]{"zze", "zzf", zzix.zza(), "zzg", zzir.zza(), "zzh", zziu.zza(), "zzi", "zzj"});
        }
        if (i2 == 3) {
            return new zzli();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzlh(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
