package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzlv extends zzpt implements zzrc {
    private static final zzlv zzb;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;

    static {
        zzlv zzlvVar = new zzlv();
        zzb = zzlvVar;
        zzpt.zzA(zzlv.class, zzlvVar);
    }

    private zzlv() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003င\u0002\u0004င\u0003", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi"});
        }
        if (i2 == 3) {
            return new zzlv();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzlu(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
