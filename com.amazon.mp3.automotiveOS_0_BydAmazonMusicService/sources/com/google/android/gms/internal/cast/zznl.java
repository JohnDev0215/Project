package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zznl extends zzpt implements zzrc {
    private static final zznl zzb;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        zznl zznlVar = new zznl();
        zzb = zznlVar;
        zzpt.zzA(zznl.class, zznlVar);
    }

    private zznl() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001", new Object[]{"zze", "zzf", zzix.zza(), "zzg", zzix.zza()});
        }
        if (i2 == 3) {
            return new zznl();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zznk(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
