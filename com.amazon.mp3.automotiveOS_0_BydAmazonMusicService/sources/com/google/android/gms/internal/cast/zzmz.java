package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzmz extends zzpt implements zzrc {
    private static final zzmz zzb;
    private int zze;
    private boolean zzf;
    private boolean zzg;
    private boolean zzh;
    private boolean zzi;
    private int zzj;
    private int zzk;
    private boolean zzl;

    static {
        zzmz zzmzVar = new zzmz();
        zzb = zzmzVar;
        zzpt.zzA(zzmz.class, zzmzVar);
    }

    private zzmz() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003ဇ\u0002\u0004ဇ\u0003\u0005ဌ\u0004\u0006ဌ\u0005\u0007ဇ\u0006", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", zzii.zza(), "zzk", zzii.zza(), "zzl"});
        }
        if (i2 == 3) {
            return new zzmz();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzmy(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
