package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzfe extends zzpt implements zzrc {
    private static final zzfe zzb;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
    private int zzl;
    private int zzm;
    private int zzn;
    private int zzo;
    private int zzp;
    private boolean zzq;

    static {
        zzfe zzfeVar = new zzfe();
        zzb = zzfeVar;
        zzpt.zzA(zzfe.class, zzfeVar);
    }

    private zzfe() {
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\f\u0000\u0001\u0001\f\f\u0000\u0000\u0000\u0001င\u0000\u0002င\u0001\u0003ဌ\u0002\u0004ဌ\u0003\u0005ဌ\u0004\u0006ဌ\u0005\u0007ဌ\u0006\bဌ\u0007\tင\b\nင\t\u000bင\n\fဇ\u000b", new Object[]{"zze", "zzf", "zzg", "zzh", zzfa.zza, "zzi", zzfb.zza, "zzj", zzey.zza, "zzk", zzfc.zza, "zzl", zzfd.zza, "zzm", zzex.zza, "zzn", "zzo", "zzp", "zzq"});
        }
        if (i2 == 3) {
            return new zzfe();
        }
        zzew zzewVar = null;
        if (i2 == 4) {
            return new zzez(zzewVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
