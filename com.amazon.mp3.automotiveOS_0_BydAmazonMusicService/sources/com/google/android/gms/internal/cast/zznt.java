package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zznt extends zzpt implements zzrc {
    private static final zznt zzb;
    private int zze;
    private long zzf;
    private boolean zzg;
    private long zzh;
    private boolean zzi;

    static {
        zznt zzntVar = new zznt();
        zzb = zzntVar;
        zzpt.zzA(zznt.class, zzntVar);
    }

    private zznt() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဇ\u0001\u0003ဂ\u0002\u0004ဇ\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zznt();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzns(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
