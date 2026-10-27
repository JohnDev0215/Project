package com.google.android.gms.cast;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzaf implements Runnable {
    final /* synthetic */ boolean zza;
    final /* synthetic */ CastRemoteDisplayLocalService zzb;

    zzaf(CastRemoteDisplayLocalService castRemoteDisplayLocalService, boolean z) {
        this.zzb = castRemoteDisplayLocalService;
        this.zza = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.zzb.zzx(this.zza);
    }
}
