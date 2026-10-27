package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzmn extends zzpt implements zzrc {
    private static final zzmn zzb;
    private int zze;
    private int zzf;
    private int zzg;

    static {
        zzmn zzmnVar = new zzmn();
        zzb = zzmnVar;
        zzpt.zzA(zzmn.class, zzmnVar);
    }

    private zzmn() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဌ\u0000\u0002င\u0001", new Object[]{"zze", "zzf", zzgy.zza(), "zzg"});
        }
        if (i2 == 3) {
            return new zzmn();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzmm(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
