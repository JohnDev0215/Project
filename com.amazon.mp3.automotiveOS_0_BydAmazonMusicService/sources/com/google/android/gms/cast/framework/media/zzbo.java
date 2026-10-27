package com.google.android.gms.cast.framework.media;

import java.util.TimerTask;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzbo extends TimerTask {
    final /* synthetic */ RemoteMediaClient zza;
    final /* synthetic */ zzbp zzb;

    zzbo(zzbp zzbpVar, RemoteMediaClient remoteMediaClient) {
        this.zzb = zzbpVar;
        this.zza = remoteMediaClient;
    }

    @Override // java.util.TimerTask, java.lang.Runnable
    public final void run() {
        zzbp zzbpVar = this.zzb;
        zzbpVar.zza.zzr(zzbpVar.zzb);
        zzbp zzbpVar2 = this.zzb;
        zzbpVar2.zza.zzc.postDelayed(this, zzbpVar2.zzc);
    }
}
