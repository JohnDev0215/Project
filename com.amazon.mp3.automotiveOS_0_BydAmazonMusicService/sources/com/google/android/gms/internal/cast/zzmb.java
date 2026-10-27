package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzmb extends zzpt implements zzrc {
    private static final zzpz zzb = new zzlz();
    private static final zzmb zze;
    private int zzf;
    private boolean zzh;
    private zzoe zzi;
    private boolean zzj;
    private String zzg = "";
    private zzpy zzk = zzpt.zzu();

    static {
        zzmb zzmbVar = new zzmb();
        zze = zzmbVar;
        zzpt.zzA(zzmb.class, zzmbVar);
    }

    private zzmb() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zze, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001ဈ\u0000\u0002ဇ\u0001\u0003ဉ\u0002\u0004ဇ\u0003\u0005,", new Object[]{"zzf", "zzg", "zzh", "zzi", "zzj", "zzk", zzke.zza()});
        }
        if (i2 == 3) {
            return new zzmb();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzma(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zze;
    }
}
