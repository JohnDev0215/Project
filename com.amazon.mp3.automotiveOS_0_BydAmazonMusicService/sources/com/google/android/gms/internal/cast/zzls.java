package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzls extends zzpt implements zzrc {
    private static final zzls zzb;
    private int zze;
    private String zzf = "";
    private String zzg = "";

    static {
        zzls zzlsVar = new zzls();
        zzb = zzlsVar;
        zzpt.zzA(zzls.class, zzlsVar);
    }

    private zzls() {
    }

    public static zzlr zza() {
        return (zzlr) zzb.zzr();
    }

    static /* synthetic */ void zzd(zzls zzlsVar, String str) {
        str.getClass();
        zzlsVar.zze |= 1;
        zzlsVar.zzf = str;
    }

    @Override // com.google.android.gms.internal.cast.zzpt
    protected final Object zzb(int i, Object obj, Object obj2) {
        int i2 = i - 1;
        if (i2 == 0) {
            return (byte) 1;
        }
        if (i2 == 2) {
            return zzpt.zzz(zzb, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"zze", "zzf", "zzg"});
        }
        if (i2 == 3) {
            return new zzls();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzlr(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
