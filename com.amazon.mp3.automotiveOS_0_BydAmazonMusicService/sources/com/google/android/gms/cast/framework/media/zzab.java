package com.google.android.gms.cast.framework.media;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzab extends zzbk {
    final /* synthetic */ RemoteMediaClient zza;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzab(RemoteMediaClient remoteMediaClient) {
        super(remoteMediaClient, false);
        this.zza = remoteMediaClient;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbk
    protected final void zza() throws com.google.android.gms.cast.internal.zzan {
        this.zza.zzd.zzI(zzb());
    }
}
