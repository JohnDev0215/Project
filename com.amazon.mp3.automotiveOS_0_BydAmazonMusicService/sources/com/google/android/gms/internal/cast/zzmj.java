package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzmj extends zzpt implements zzrc {
    private static final zzmj zzb;
    private int zze;
    private zzqb zzf = zzpt.zzw();
    private boolean zzg;
    private boolean zzh;

    static {
        zzmj zzmjVar = new zzmj();
        zzb = zzmjVar;
        zzpt.zzA(zzmj.class, zzmjVar);
    }

    private zzmj() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0001\u0000\u0001\u001b\u0002ဇ\u0000\u0003ဇ\u0001", new Object[]{"zze", "zzf", zzmb.class, "zzg", "zzh"});
        }
        if (i2 == 3) {
            return new zzmj();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzmi(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
