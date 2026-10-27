package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzoe extends zzpt implements zzrc {
    private static final zzpz zzb = new zzoc();
    private static final zzoe zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private zzpy zzi = zzpt.zzu();
    private int zzj;

    static {
        zzoe zzoeVar = new zzoe();
        zze = zzoeVar;
        zzpt.zzA(zzoe.class, zzoeVar);
    }

    private zzoe() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zze, "\u0001\u0004\u0000\u0001\u0001\u0005\u0004\u0000\u0001\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003\u001e\u0005ဌ\u0002", new Object[]{"zzf", "zzg", zzkh.zza(), "zzh", zzhb.zza(), "zzi", zzke.zza(), "zzj", zzgj.zza()});
        }
        if (i2 == 3) {
            return new zzoe();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzod(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zze;
    }
}
