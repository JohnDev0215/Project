package com.google.android.gms.internal.play_billing;

/* JADX INFO: compiled from: com.android.billingclient:billing@@5.2.1 */
/* JADX INFO: loaded from: classes6.dex */
public class zzbe {
    public static final /* synthetic */ int zza = 0;
    private static volatile int zzb = 100;

    /* synthetic */ zzbe(zzbd zzbdVar) {
    }

    public static int zzb(int i) {
        return (i >>> 1) ^ (-(i & 1));
    }

    public static long zzc(long j) {
        return (j >>> 1) ^ (-(1 & j));
    }
}
