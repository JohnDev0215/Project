package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zznf extends zzpt implements zzrc {
    private static final zznf zzb;
    private int zze;
    private boolean zzf;
    private boolean zzg;
    private int zzh;
    private int zzj;
    private int zzk;
    private String zzi = "";
    private String zzl = "";

    static {
        zznf zznfVar = new zznf();
        zzb = zznfVar;
        zzpt.zzA(zznf.class, zznfVar);
    }

    private zznf() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဇ\u0000\u0002ဇ\u0001\u0003င\u0002\u0004ဈ\u0003\u0005င\u0004\u0006င\u0005\u0007ဈ\u0006", new Object[]{"zze", "zzf", "zzg", "zzh", "zzi", "zzj", "zzk", "zzl"});
        }
        if (i2 == 3) {
            return new zznf();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzne(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
