package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzky extends zzpt implements zzrc {
    private static final zzky zzb;
    private int zze;
    private int zzf;
    private boolean zzg;
    private int zzh;
    private boolean zzi;
    private zzqb zzj = zzpt.zzw();
    private zzqb zzk = zzpt.zzw();
    private String zzl = "";

    static {
        zzky zzkyVar = new zzky();
        zzb = zzkyVar;
        zzpt.zzA(zzky.class, zzkyVar);
    }

    private zzky() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0007\u0000\u0001\u0001\t\u0007\u0000\u0002\u0000\u0001ဌ\u0000\u0002ဇ\u0001\u0003ဌ\u0002\u0004ဇ\u0003\u0007\u001b\b\u001b\tဈ\u0004", new Object[]{"zze", "zzf", zzfl.zza(), "zzg", "zzh", zzhb.zza(), "zzi", "zzj", zznd.class, "zzk", zznd.class, "zzl"});
        }
        if (i2 == 3) {
            return new zzky();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzkx(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
