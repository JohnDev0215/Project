package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzmx extends zzpt implements zzrc {
    private static final zzmx zzb;
    private int zze;
    private long zzf;
    private zzqa zzg = zzpt.zzv();
    private zzqa zzh = zzpt.zzv();

    static {
        zzmx zzmxVar = new zzmx();
        zzb = zzmxVar;
        zzpt.zzA(zzmx.class, zzmxVar);
    }

    private zzmx() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0002\u0000\u0001စ\u0000\u0002\u0017\u0003\u0017", new Object[]{"zze", "zzf", "zzg", "zzh"});
        }
        if (i2 == 3) {
            return new zzmx();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzmw(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
