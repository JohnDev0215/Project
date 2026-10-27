package com.google.android.gms.cast;

import com.google.android.gms.cast.CastRemoteDisplayLocalService;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzag implements Runnable {
    final /* synthetic */ CastRemoteDisplayLocalService.NotificationSettings zza;
    final /* synthetic */ CastRemoteDisplayLocalService zzb;

    zzag(CastRemoteDisplayLocalService castRemoteDisplayLocalService, CastRemoteDisplayLocalService.NotificationSettings notificationSettings) {
        this.zzb = castRemoteDisplayLocalService;
        this.zza = notificationSettings;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CastRemoteDisplayLocalService.zzq(this.zzb, this.zza);
    }
}
