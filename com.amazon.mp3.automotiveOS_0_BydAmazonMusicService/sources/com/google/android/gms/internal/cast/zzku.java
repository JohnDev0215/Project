package com.google.android.gms.internal.cast;

import java.util.Iterator;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzku extends zzpt implements zzrc {
    private static final zzpz zzb = new zzks();
    private static final zzku zze;
    private int zzf;
    private zzla zzg;
    private zzmr zzh;
    private zzqb zzi = zzpt.zzw();
    private zzpy zzj = zzpt.zzu();

    static {
        zzku zzkuVar = new zzku();
        zze = zzkuVar;
        zzpt.zzA(zzku.class, zzkuVar);
    }

    private zzku() {
    }

    public static zzkt zza() {
        return (zzkt) zze.zzr();
    }

    static /* synthetic */ void zzd(zzku zzkuVar, zzla zzlaVar) {
        zzlaVar.getClass();
        zzkuVar.zzg = zzlaVar;
        zzkuVar.zzf |= 1;
    }

    static /* synthetic */ void zze(zzku zzkuVar, Iterable iterable) {
        zzpy zzpyVar = zzkuVar.zzj;
        if (!zzpyVar.zzc()) {
            int size = zzpyVar.size();
            zzkuVar.zzj = zzpyVar.zzg(size == 0 ? 10 : size + size);
        }
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            zzkuVar.zzj.zzh(((zzkk) it.next()).zza());
        }
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zze, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0002\u0000\u0001ဉ\u0000\u0002ဉ\u0001\u0003\u001b\u0004\u001e", new Object[]{"zzf", "zzg", "zzh", "zzi", zzmn.class, "zzj", zzkk.zzc()});
        }
        if (i2 == 3) {
            return new zzku();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzkt(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zze;
    }
}
