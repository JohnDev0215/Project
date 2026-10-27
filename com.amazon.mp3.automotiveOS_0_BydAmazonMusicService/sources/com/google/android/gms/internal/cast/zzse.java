package com.google.android.gms.internal.cast;

import java.util.Arrays;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzse {
    private static final zzse zza = new zzse(0, new int[0], new Object[0], false);
    private int zzb;
    private int[] zzc;
    private Object[] zzd;
    private int zze;

    private zzse() {
        this(0, new int[8], new Object[8], true);
    }

    private zzse(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zze = -1;
        this.zzb = 0;
        this.zzc = iArr;
        this.zzd = objArr;
    }

    public static zzse zzc() {
        return zza;
    }

    static zzse zzd(zzse zzseVar, zzse zzseVar2) {
        int i = zzseVar.zzb;
        int i2 = zzseVar2.zzb;
        int[] iArrCopyOf = Arrays.copyOf(zzseVar.zzc, 0);
        System.arraycopy(zzseVar2.zzc, 0, iArrCopyOf, 0, 0);
        Object[] objArrCopyOf = Arrays.copyOf(zzseVar.zzd, 0);
        System.arraycopy(zzseVar2.zzd, 0, objArrCopyOf, 0, 0);
        return new zzse(0, iArrCopyOf, objArrCopyOf, true);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof zzse)) {
            return false;
        }
        return true;
    }

    public final int hashCode() {
        return 506991;
    }

    public final int zza() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        this.zze = 0;
        return 0;
    }

    public final int zzb() {
        int i = this.zze;
        if (i != -1) {
            return i;
        }
        this.zze = 0;
        return 0;
    }

    final void zze(StringBuilder sb, int i) {
    }
}
