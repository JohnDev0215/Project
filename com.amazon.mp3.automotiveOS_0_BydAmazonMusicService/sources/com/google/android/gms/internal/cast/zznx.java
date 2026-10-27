package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zznx extends zzpt implements zzrc {
    private static final zznx zzb;
    private int zze;
    private int zzf;
    private int zzg;
    private zzkw zzh;

    static {
        zznx zznxVar = new zznx();
        zzb = zznxVar;
        zzpt.zzA(zznx.class, zznxVar);
    }

    private zznx() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဋ\u0001\u0003ဉ\u0002", new Object[]{"zze", "zzf", zzjp.zza(), "zzg", "zzh"});
        }
        if (i2 == 3) {
            return new zznx();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zznw(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
