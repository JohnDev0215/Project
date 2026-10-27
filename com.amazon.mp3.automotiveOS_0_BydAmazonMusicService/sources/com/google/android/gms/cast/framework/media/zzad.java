package com.google.android.gms.cast.framework.media;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzad extends zzbk {
    final /* synthetic */ long[] zza;
    final /* synthetic */ RemoteMediaClient zzb;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    zzad(RemoteMediaClient remoteMediaClient, long[] jArr) {
        super(remoteMediaClient, false);
        this.zzb = remoteMediaClient;
        this.zza = jArr;
    }

    @Override // com.google.android.gms.cast.framework.media.zzbk
    protected final void zza() throws com.google.android.gms.cast.internal.zzan {
        this.zzb.zzd.zzD(zzb(), this.zza);
    }
}
