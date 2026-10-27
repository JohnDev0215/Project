package com.google.android.gms.internal.cast;

import android.os.Bundle;
import androidx.annotation.MainThread;
import com.google.android.gms.cast.internal.Logger;
import java.math.BigInteger;
import java.util.Map;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@MainThread
public final class zzj {
    private static final Logger zza = new Logger("ApplicationAnalyticsUtils");
    private static final String zzb = "21.1.0";
    private final String zzc;
    private final Map zzd;
    private final Map zze;

    public zzj(Bundle bundle, String str) {
        this.zzc = str;
        this.zzd = zzn.zza(bundle, "com.google.android.gms.cast.DICTIONARY_CAST_STATUS_CODES_TO_APP_SESSION_ERROR");
        this.zze = zzn.zza(bundle, "com.google.android.gms.cast.DICTIONARY_CAST_STATUS_CODES_TO_APP_SESSION_CHANGE_REASON");
    }

    private final zzlj zzf(zzi zziVar) {
        long jLongValue;
        zzlj zzljVarZzc = zzlk.zzc();
        zzljVarZzc.zzj(zziVar.zzd);
        int i = zziVar.zze;
        zziVar.zze = i + 1;
        zzljVarZzc.zzg(i);
        String str = zziVar.zzc;
        if (str != null) {
            zzljVarZzc.zzh(str);
        }
        String str2 = zziVar.zzh;
        if (str2 != null) {
            zzljVarZzc.zzf(str2);
        }
        zzkz zzkzVarZza = zzla.zza();
        zzkzVarZza.zzb(zzb);
        zzkzVarZza.zza(this.zzc);
        zzljVarZzc.zzb((zzla) zzkzVarZza.zzp());
        zzlb zzlbVarZza = zzlc.zza();
        if (zziVar.zzb != null) {
            zzlr zzlrVarZza = zzls.zza();
            zzlrVarZza.zza(zziVar.zzb);
            zzlbVarZza.zza((zzls) zzlrVarZza.zzp());
        }
        zzlbVarZza.zzd(false);
        String str3 = zziVar.zzf;
        if (str3 != null) {
            try {
                String strReplace = str3.replace("-", "");
                jLongValue = new BigInteger(strReplace.substring(0, Math.min(16, strReplace.length())), 16).longValue();
            } catch (NumberFormatException e) {
                zza.w(e, "receiverSessionId %s is not valid for hash", str3);
                jLongValue = 0;
            }
            zzlbVarZza.zzf(jLongValue);
        }
        zzlbVarZza.zzb(zziVar.zzg);
        zzljVarZzc.zzd(zzlbVarZza);
        return zzljVarZzc;
    }

    private static void zzg(zzlj zzljVar, boolean z) {
        zzlb zzlbVarZzc = zzlc.zzc(zzljVar.zza());
        zzlbVarZzc.zzd(z);
        zzljVar.zzd(zzlbVarZzc);
    }

    public final zzlk zza(zzi zziVar) {
        return (zzlk) zzf(zziVar).zzp();
    }

    public final zzlk zzb(zzi zziVar, boolean z) {
        zzlj zzljVarZzf = zzf(zziVar);
        zzg(zzljVarZzf, z);
        return (zzlk) zzljVarZzf.zzp();
    }

    public final zzlk zzc(zzi zziVar) {
        zzlj zzljVarZzf = zzf(zziVar);
        zzlb zzlbVarZzc = zzlc.zzc(zzljVarZzf.zza());
        zzlbVarZzc.zze(10);
        zzljVarZzf.zze((zzlc) zzlbVarZzc.zzp());
        zzg(zzljVarZzf, true);
        return (zzlk) zzljVarZzf.zzp();
    }

    public final zzlk zzd(zzi zziVar) {
        zzlj zzljVarZzf = zzf(zziVar);
        if (zziVar.zzi == 1) {
            zzlb zzlbVarZzc = zzlc.zzc(zzljVarZzf.zza());
            zzlbVarZzc.zze(17);
            zzljVarZzf.zze((zzlc) zzlbVarZzc.zzp());
        }
        return (zzlk) zzljVarZzf.zzp();
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0055  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.gms.internal.cast.zzlk zze(com.google.android.gms.internal.cast.zzi r4, int r5) {
        /*
            r3 = this;
            com.google.android.gms.internal.cast.zzlj r4 = r3.zzf(r4)
            com.google.android.gms.internal.cast.zzlc r0 = r4.zza()
            com.google.android.gms.internal.cast.zzlb r0 = com.google.android.gms.internal.cast.zzlc.zzc(r0)
            java.util.Map r1 = r3.zze
            if (r1 == 0) goto L2e
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)
            boolean r1 = r1.containsKey(r2)
            if (r1 != 0) goto L1b
            goto L2e
        L1b:
            java.util.Map r1 = r3.zze
            java.lang.Object r1 = r1.get(r2)
            java.lang.Integer r1 = (java.lang.Integer) r1
            java.lang.Object r1 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r1)
            java.lang.Integer r1 = (java.lang.Integer) r1
            int r1 = r1.intValue()
            goto L30
        L2e:
            int r1 = r5 + 10000
        L30:
            r0.zze(r1)
            java.util.Map r1 = r3.zzd
            if (r1 == 0) goto L55
            java.lang.Integer r2 = java.lang.Integer.valueOf(r5)
            boolean r1 = r1.containsKey(r2)
            if (r1 != 0) goto L42
            goto L55
        L42:
            java.util.Map r5 = r3.zzd
            java.lang.Object r5 = r5.get(r2)
            java.lang.Integer r5 = (java.lang.Integer) r5
            java.lang.Object r5 = com.google.android.gms.common.internal.Preconditions.checkNotNull(r5)
            java.lang.Integer r5 = (java.lang.Integer) r5
            int r5 = r5.intValue()
            goto L57
        L55:
            int r5 = r5 + 10000
        L57:
            r0.zzc(r5)
            com.google.android.gms.internal.cast.zzpt r5 = r0.zzp()
            com.google.android.gms.internal.cast.zzlc r5 = (com.google.android.gms.internal.cast.zzlc) r5
            r4.zze(r5)
            com.google.android.gms.internal.cast.zzpt r4 = r4.zzp()
            com.google.android.gms.internal.cast.zzlk r4 = (com.google.android.gms.internal.cast.zzlk) r4
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.cast.zzj.zze(com.google.android.gms.internal.cast.zzi, int):com.google.android.gms.internal.cast.zzlk");
    }
}
