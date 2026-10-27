package com.google.android.gms.internal.cast;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;
import androidx.work.PeriodicWorkRequest;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@MainThread
public final class zzh {
    private static final Logger zza = new Logger("ApplicationAnalytics");
    private final zzd zzb;
    private final zzj zzc;
    private final SharedPreferences zzf;

    @Nullable
    private zzi zzg;
    private final Handler zze = new zzcv(Looper.getMainLooper());
    private final Runnable zzd = new Runnable() { // from class: com.google.android.gms.internal.cast.zze
        @Override // java.lang.Runnable
        public final void run() {
            zzh.zzf(this.zza);
        }
    };

    public zzh(SharedPreferences sharedPreferences, zzd zzdVar, Bundle bundle, String str) {
        this.zzf = sharedPreferences;
        this.zzb = zzdVar;
        this.zzc = new zzj(bundle, str);
    }

    public static /* synthetic */ void zzf(zzh zzhVar) {
        zzi zziVar = zzhVar.zzg;
        if (zziVar != null) {
            zzhVar.zzb.zzb(zzhVar.zzc.zza(zziVar), 223);
        }
        zzhVar.zzs();
    }

    static /* bridge */ /* synthetic */ void zzj(zzh zzhVar, CastSession castSession, int i) {
        zzhVar.zzq(castSession);
        zzhVar.zzb.zzb(zzhVar.zzc.zze(zzhVar.zzg, i), 228);
        zzhVar.zzp();
        zzhVar.zzg = null;
    }

    static /* bridge */ /* synthetic */ void zzk(zzh zzhVar, SharedPreferences sharedPreferences, String str) {
        if (zzhVar.zzv(str)) {
            zza.d("Use the existing ApplicationAnalyticsSession if it is available and valid.", new Object[0]);
            Preconditions.checkNotNull(zzhVar.zzg);
            return;
        }
        zzhVar.zzg = zzi.zzb(sharedPreferences);
        if (zzhVar.zzv(str)) {
            zza.d("Use the restored ApplicationAnalyticsSession if it is valid.", new Object[0]);
            Preconditions.checkNotNull(zzhVar.zzg);
            zzi.zza = zzhVar.zzg.zzd + 1;
        } else {
            zza.d("The restored ApplicationAnalyticsSession is not valid, create a new one.", new Object[0]);
            zzi zziVarZza = zzi.zza();
            zzhVar.zzg = zziVarZza;
            zziVarZza.zzb = zzo();
            zzhVar.zzg.zzf = str;
        }
    }

    private static String zzo() {
        return ((CastContext) Preconditions.checkNotNull(CastContext.getSharedInstance())).getCastOptions().getReceiverApplicationId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzp() {
        this.zze.removeCallbacks(this.zzd);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzq(CastSession castSession) {
        if (!zzu()) {
            zza.w("The analyticsSession should not be null for logging. Create a dummy one.", new Object[0]);
            zzr(castSession);
            return;
        }
        CastDevice castDevice = castSession != null ? castSession.getCastDevice() : null;
        if (castDevice != null && !TextUtils.equals(this.zzg.zzc, castDevice.zzb())) {
            zzt(castDevice);
        }
        Preconditions.checkNotNull(this.zzg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzr(CastSession castSession) {
        zza.d("Create a new ApplicationAnalyticsSession based on CastSession", new Object[0]);
        zzi zziVarZza = zzi.zza();
        this.zzg = zziVarZza;
        zziVarZza.zzb = zzo();
        CastDevice castDevice = castSession == null ? null : castSession.getCastDevice();
        if (castDevice != null) {
            zzt(castDevice);
        }
        Preconditions.checkNotNull(this.zzg);
        this.zzg.zzi = castSession != null ? castSession.zzn() : 0;
        Preconditions.checkNotNull(this.zzg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzs() {
        ((Handler) Preconditions.checkNotNull(this.zze)).postDelayed((Runnable) Preconditions.checkNotNull(this.zzd), PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS);
    }

    private final void zzt(CastDevice castDevice) {
        zzi zziVar = this.zzg;
        if (zziVar == null) {
            return;
        }
        zziVar.zzc = castDevice.zzb();
        zziVar.zzg = castDevice.zza();
        zziVar.zzh = castDevice.getModelName();
    }

    private final boolean zzu() {
        String str;
        if (this.zzg == null) {
            zza.d("The analytics session is null when matching with application ID.", new Object[0]);
            return false;
        }
        String strZzo = zzo();
        if (strZzo == null || (str = this.zzg.zzb) == null || !TextUtils.equals(str, strZzo)) {
            zza.d("The analytics session doesn't match the application ID %s", strZzo);
            return false;
        }
        Preconditions.checkNotNull(this.zzg);
        return true;
    }

    private final boolean zzv(String str) {
        String str2;
        if (!zzu()) {
            return false;
        }
        Preconditions.checkNotNull(this.zzg);
        if (str != null && (str2 = this.zzg.zzf) != null && TextUtils.equals(str2, str)) {
            return true;
        }
        zza.d("The analytics session doesn't match the receiver session ID %s.", str);
        return false;
    }

    public final void zzn(SessionManager sessionManager) {
        sessionManager.addSessionManagerListener(new zzg(this, null), CastSession.class);
    }
}
