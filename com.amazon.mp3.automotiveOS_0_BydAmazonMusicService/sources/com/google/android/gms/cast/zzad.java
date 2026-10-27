package com.google.android.gms.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzad implements Runnable {
    final /* synthetic */ CastRemoteDisplayLocalService zza;

    zzad(CastRemoteDisplayLocalService castRemoteDisplayLocalService) {
        this.zza = castRemoteDisplayLocalService;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CastRemoteDisplayLocalService castRemoteDisplayLocalService = this.zza;
        boolean z = castRemoteDisplayLocalService.zzs;
        StringBuilder sb = new StringBuilder(59);
        sb.append("onCreate after delay. The local service been started: ");
        sb.append(z);
        castRemoteDisplayLocalService.zzv(sb.toString());
        CastRemoteDisplayLocalService castRemoteDisplayLocalService2 = this.zza;
        if (castRemoteDisplayLocalService2.zzs) {
            return;
        }
        CastRemoteDisplayLocalService.zza.e("[Instance: %s] %s", castRemoteDisplayLocalService2, "The local service has not been been started, stopping it");
        this.zza.stopSelf();
    }
}
