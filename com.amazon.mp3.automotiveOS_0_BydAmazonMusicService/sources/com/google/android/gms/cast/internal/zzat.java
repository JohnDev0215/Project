package com.google.android.gms.cast.internal;

import android.os.Handler;
import android.os.Looper;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.CastStatusCodes;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.cast.zzcv;
import java.util.Locale;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzat {

    @Nullable
    @VisibleForTesting
    zzar zzc;

    @Nullable
    @VisibleForTesting
    Runnable zzd;
    private final long zzf;
    private static final Logger zze = new Logger("RequestTracker");
    public static final Object zza = new Object();

    @VisibleForTesting
    long zzb = -1;
    private final Handler zzg = new zzcv(Looper.getMainLooper());

    public zzat(long j) {
        this.zzf = j;
    }

    public static /* synthetic */ void zza(zzat zzatVar) {
        synchronized (zza) {
            if (zzatVar.zzb == -1) {
                return;
            }
            zzatVar.zzh(15, null);
        }
    }

    private final void zzg(int i, @Nullable Object obj, String str) {
        zze.d(str, new Object[0]);
        Object obj2 = zza;
        synchronized (obj2) {
            zzar zzarVar = this.zzc;
            if (zzarVar != null) {
                zzarVar.zza(this.zzb, i, obj);
            }
            this.zzb = -1L;
            this.zzc = null;
            synchronized (obj2) {
                Runnable runnable = this.zzd;
                if (runnable != null) {
                    this.zzg.removeCallbacks(runnable);
                    this.zzd = null;
                }
            }
        }
    }

    private final boolean zzh(int i, @Nullable Object obj) {
        synchronized (zza) {
            long j = this.zzb;
            if (j == -1) {
                return false;
            }
            zzg(i, null, String.format(Locale.ROOT, "clearing request %d", Long.valueOf(j)));
            return true;
        }
    }

    public final void zzb(long j, zzar zzarVar) {
        zzar zzarVar2;
        long j2;
        Object obj = zza;
        synchronized (obj) {
            zzarVar2 = this.zzc;
            j2 = this.zzb;
            this.zzb = j;
            this.zzc = zzarVar;
        }
        if (zzarVar2 != null) {
            zzarVar2.zzb(j2);
        }
        synchronized (obj) {
            Runnable runnable = this.zzd;
            if (runnable != null) {
                this.zzg.removeCallbacks(runnable);
            }
            Runnable runnable2 = new Runnable() { // from class: com.google.android.gms.cast.internal.zzas
                @Override // java.lang.Runnable
                public final void run() {
                    zzat.zza(this.zza);
                }
            };
            this.zzd = runnable2;
            this.zzg.postDelayed(runnable2, this.zzf);
        }
    }

    public final boolean zzc(int i) {
        return zzh(CastStatusCodes.CANCELED, null);
    }

    public final boolean zzd(long j, int i, @Nullable Object obj) {
        synchronized (zza) {
            long j2 = this.zzb;
            if (j2 == -1 || j2 != j) {
                return false;
            }
            zzg(i, obj, String.format(Locale.ROOT, "request %d completed", Long.valueOf(j)));
            return true;
        }
    }

    public final boolean zze() {
        boolean z;
        synchronized (zza) {
            z = this.zzb != -1;
        }
        return z;
    }

    public final boolean zzf(long j) {
        boolean z;
        synchronized (zza) {
            long j2 = this.zzb;
            z = false;
            if (j2 != -1 && j2 == j) {
                z = true;
            }
        }
        return z;
    }
}
