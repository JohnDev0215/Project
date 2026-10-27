package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzmt extends zzpt implements zzrc {
    private static final zzmt zzb;
    private int zze;
    private int zzf;
    private long zzg;
    private int zzh;

    static {
        zzmt zzmtVar = new zzmt();
        zzb = zzmtVar;
        zzpt.zzA(zzmt.class, zzmtVar);
    }

    private zzmt() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဂ\u0001\u0003ဌ\u0002", new Object[]{"zze", "zzf", zzic.zza(), "zzg", "zzh", zzfx.zza()});
        }
        if (i2 == 3) {
            return new zzmt();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzms(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
