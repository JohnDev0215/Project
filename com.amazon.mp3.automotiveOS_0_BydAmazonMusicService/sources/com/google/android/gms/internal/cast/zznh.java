package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zznh extends zzpt implements zzrc {
    private static final zznh zzb;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;

    static {
        zznh zznhVar = new zznh();
        zzb = zznhVar;
        zzpt.zzA(zznh.class, zznhVar);
    }

    private zznh() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001င\u0000\u0002ဌ\u0001\u0003င\u0002", new Object[]{"zze", "zzf", "zzg", zzio.zza(), "zzh"});
        }
        if (i2 == 3) {
            return new zznh();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzng(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
