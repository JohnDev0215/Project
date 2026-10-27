package com.google.android.gms.internal.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzla extends zzpt implements zzrc {
    private static final zzla zzb;
    private int zze;
    private String zzf = "";
    private String zzg = "";

    static {
        zzla zzlaVar = new zzla();
        zzb = zzlaVar;
        zzpt.zzA(zzla.class, zzlaVar);
    }

    private zzla() {
    }

    public static zzkz zza() {
        return (zzkz) zzb.zzr();
    }

    static /* synthetic */ void zzd(zzla zzlaVar, String str) {
        str.getClass();
        zzlaVar.zze |= 1;
        zzlaVar.zzf = str;
    }

    static /* synthetic */ void zze(zzla zzlaVar, String str) {
        str.getClass();
        zzlaVar.zze |= 2;
        zzlaVar.zzg = str;
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
            return new zzla();
        }
        zzko zzkoVar = null;
        if (i2 == 4) {
            return new zzkz(zzkoVar);
        }
        if (i2 != 5) {
            return null;
        }
        return zzb;
    }
}
