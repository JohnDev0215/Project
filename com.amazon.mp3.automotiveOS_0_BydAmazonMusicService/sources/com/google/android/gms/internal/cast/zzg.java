package com.google.android.gms.internal.cast;

import com.amazon.mp3.prime.ContentCatalogStatus;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzg implements SessionManagerListener {
    final /* synthetic */ zzh zza;

    /* synthetic */ zzg(zzh zzhVar, zzf zzfVar) {
        this.zza = zzhVar;
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* synthetic */ void onSessionEnded(Session session, int i) {
        zzh.zzj(this.zza, (CastSession) session, i);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionEnding(Session session) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* synthetic */ void onSessionResumeFailed(Session session, int i) {
        zzh.zzj(this.zza, (CastSession) session, i);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResumed(Session session, boolean z) {
        this.zza.zzq((CastSession) session);
        Preconditions.checkNotNull(this.zza.zzg);
        zzh zzhVar = this.zza;
        this.zza.zzb.zzb(zzhVar.zzc.zzb(zzhVar.zzg, z), 227);
        zzh zzhVar2 = this.zza;
        zzhVar2.zzg.zzc(zzhVar2.zzf);
        this.zza.zzs();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResuming(Session session, String str) {
        zzh zzhVar = this.zza;
        zzh.zzk(zzhVar, zzhVar.zzf, str);
        Preconditions.checkNotNull(this.zza.zzg);
        zzh zzhVar2 = this.zza;
        this.zza.zzb.zzb(zzhVar2.zzc.zzc(zzhVar2.zzg), 226);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* synthetic */ void onSessionStartFailed(Session session, int i) {
        zzh.zzj(this.zza, (CastSession) session, i);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStarted(Session session, String str) {
        this.zza.zzq((CastSession) session);
        zzh zzhVar = this.zza;
        zzhVar.zzg.zzf = str;
        this.zza.zzb.zzb(zzhVar.zzc.zza(zzhVar.zzg), 222);
        zzh zzhVar2 = this.zza;
        zzhVar2.zzg.zzc(zzhVar2.zzf);
        this.zza.zzs();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStarting(Session session) {
        CastSession castSession = (CastSession) session;
        if (this.zza.zzg != null) {
            zzh.zza.w("Start a session while there's already an active session. Create a new one.", new Object[0]);
        }
        this.zza.zzr(castSession);
        zzh zzhVar = this.zza;
        this.zza.zzb.zzb(zzhVar.zzc.zzd(zzhVar.zzg), 221);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionSuspended(Session session, int i) {
        this.zza.zzq((CastSession) session);
        Preconditions.checkNotNull(this.zza.zzg);
        zzh zzhVar = this.zza;
        this.zza.zzb.zzb(zzhVar.zzc.zze(zzhVar.zzg, i), ContentCatalogStatus.PRIME_OR_NOT_BOUNDARY);
        zzh zzhVar2 = this.zza;
        zzhVar2.zzg.zzc(zzhVar2.zzf);
        this.zza.zzp();
    }
}
